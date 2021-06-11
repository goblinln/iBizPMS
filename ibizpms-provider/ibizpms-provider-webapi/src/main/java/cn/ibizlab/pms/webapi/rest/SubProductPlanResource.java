package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
@Api(tags = {"产品计划"})
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


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
    @ApiOperation(value = "根据产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DELETE')")
    @ApiOperation(value = "根据产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DELETE')")
    @ApiOperation(value = "根据产品计划批量删除产品计划", tags = {"产品计划" },  notes = "根据产品计划批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlan(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'UPDATE')")
    @ApiOperation(value = "根据产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan testget = productplanService.get(subproductplan_id);
        if (testget.getParent() != productplan_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划批关联BUG", tags = {"产品计划" },  notes = "根据产品计划批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchLinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划批关联需求", tags = {"产品计划" },  notes = "根据产品计划批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchLinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划批量解除关联Bug", tags = {"产品计划" },  notes = "根据产品计划批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划批量解除关联需求", tags = {"产品计划" },  notes = "根据产品计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE激活计划", tags = {"产品计划" },  notes = "根据产品计划EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE取消计划", tags = {"产品计划" },  notes = "根据产品计划EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE关闭计划", tags = {"产品计划" },  notes = "根据产品计划EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE完成计划", tags = {"产品计划" },  notes = "根据产品计划EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE暂停计划", tags = {"产品计划" },  notes = "根据产品计划EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划继续计划", tags = {"产品计划" },  notes = "根据产品计划继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划EE开始计划", tags = {"产品计划" },  notes = "根据产品计划EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
    @ApiOperation(value = "根据产品计划获取上一个计划的名称", tags = {"产品计划" },  notes = "根据产品计划获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品计划导入计划模板", tags = {"产品计划" },  notes = "根据产品计划导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'LINKBUG')")
    @ApiOperation(value = "根据产品计划关联Bug", tags = {"产品计划" },  notes = "根据产品计划关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'LINKSTORY')")
    @ApiOperation(value = "根据产品计划关联需求", tags = {"产品计划" },  notes = "根据产品计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划关联任务", tags = {"产品计划" },  notes = "根据产品计划关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划移动端产品计划计数器", tags = {"产品计划" },  notes = "根据产品计划移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划保存产品计划", tags = {"产品计划" },  notes = "根据产品计划保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/save")
    public ResponseEntity<SubProductPlanDTO> saveByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划解除关联Bug", tags = {"产品计划" },  notes = "根据产品计划解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品计划解除关联需求", tags = {"产品计划" },  notes = "根据产品计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品计划获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchChildPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchCurProductPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取数据集", tags = {"产品计划" } ,notes = "根据产品计划获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchcurproductplanstory")
	public ResponseEntity<List<SubProductPlanDTO>> fetchCurProductPlanStoryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取默认查询", tags = {"产品计划" } ,notes = "根据产品计划获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchdefaultparent")
	public ResponseEntity<List<SubProductPlanDTO>> fetchDefaultParentByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchPlanCodeListByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchPlanTasksByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProductQueryByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProjectAppByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProjectPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchRootPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchTaskPlanByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立产品计划", tags = {"产品计划" },  notes = "根据产品产品计划建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans")
    public ResponseEntity<SubProductPlanDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
		productplanService.create(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
    @ApiOperation(value = "根据产品产品计划获取产品计划", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
        ProductPlan domain = productplanService.get(subproductplan_id);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(subproductplan_id));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DELETE')")
    @ApiOperation(value = "根据产品产品计划批量删除产品计划", tags = {"产品计划" },  notes = "根据产品产品计划批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlan(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'UPDATE')")
    @ApiOperation(value = "根据产品产品计划更新产品计划", tags = {"产品计划" },  notes = "根据产品产品计划更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}")
    public ResponseEntity<SubProductPlanDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan testget = productplanService.get(subproductplan_id);
        if (testget.getParent() != productplan_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
		productplanService.update(domain);
        SubProductPlanDTO dto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划批关联BUG", tags = {"产品计划" },  notes = "根据产品产品计划批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchLinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划批关联需求", tags = {"产品计划" },  notes = "根据产品产品计划批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchLinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划批量解除关联Bug", tags = {"产品计划" },  notes = "根据产品产品计划批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划批量解除关联需求", tags = {"产品计划" },  notes = "根据产品产品计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory")
    public ResponseEntity<SubProductPlanDTO> batchUnlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划检查产品计划", tags = {"产品计划" },  notes = "根据产品产品计划检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(subproductplanMapping.toDomain(subproductplandto)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE激活计划", tags = {"产品计划" },  notes = "根据产品产品计划EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan")
    public ResponseEntity<SubProductPlanDTO> eeActivePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE取消计划", tags = {"产品计划" },  notes = "根据产品产品计划EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan")
    public ResponseEntity<SubProductPlanDTO> eeCancelPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE关闭计划", tags = {"产品计划" },  notes = "根据产品产品计划EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan")
    public ResponseEntity<SubProductPlanDTO> eeClosePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE完成计划", tags = {"产品计划" },  notes = "根据产品产品计划EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan")
    public ResponseEntity<SubProductPlanDTO> eeFinishPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE暂停计划", tags = {"产品计划" },  notes = "根据产品产品计划EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan")
    public ResponseEntity<SubProductPlanDTO> eePausePlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eePausePlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划继续计划", tags = {"产品计划" },  notes = "根据产品产品计划继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan")
    public ResponseEntity<SubProductPlanDTO> eeRestartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划EE开始计划", tags = {"产品计划" },  notes = "根据产品产品计划EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan")
    public ResponseEntity<SubProductPlanDTO> eeStartPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品产品计划获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/getdraft")
    public ResponseEntity<SubProductPlanDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, SubProductPlanDTO dto) {
        ProductPlan domain = subproductplanMapping.toDomain(dto);
        domain.setParent(productplan_id);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
    @ApiOperation(value = "根据产品产品计划获取上一个计划的名称", tags = {"产品计划" },  notes = "根据产品产品计划获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname")
    public ResponseEntity<SubProductPlanDTO> getOldPlanNameByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划导入计划模板", tags = {"产品计划" },  notes = "根据产品产品计划导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet")
    public ResponseEntity<SubProductPlanDTO> importPlanTempletByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'LINKBUG')")
    @ApiOperation(value = "根据产品产品计划关联Bug", tags = {"产品计划" },  notes = "根据产品产品计划关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug")
    public ResponseEntity<SubProductPlanDTO> linkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'LINKSTORY')")
    @ApiOperation(value = "根据产品产品计划关联需求", tags = {"产品计划" },  notes = "根据产品产品计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory")
    public ResponseEntity<SubProductPlanDTO> linkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划关联任务", tags = {"产品计划" },  notes = "根据产品产品计划关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask")
    public ResponseEntity<SubProductPlanDTO> linkTaskByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.linkTask(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划移动端产品计划计数器", tags = {"产品计划" },  notes = "根据产品产品计划移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter")
    public ResponseEntity<SubProductPlanDTO> mobProductPlanCounterByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划保存产品计划", tags = {"产品计划" },  notes = "根据产品产品计划保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/save")
    public ResponseEntity<SubProductPlanDTO> saveByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplanMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划解除关联Bug", tags = {"产品计划" },  notes = "根据产品产品计划解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug")
    public ResponseEntity<SubProductPlanDTO> unlinkBugByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkBug(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品产品计划解除关联需求", tags = {"产品计划" },  notes = "根据产品产品计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory")
    public ResponseEntity<SubProductPlanDTO> unlinkStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("subproductplan_id") Long subproductplan_id, @RequestBody SubProductPlanDTO subproductplandto) {
        ProductPlan domain = subproductplanMapping.toDomain(subproductplandto);
        domain.setParent(productplan_id);
        domain.setId(subproductplan_id);
        domain = productplanService.unlinkStory(domain) ;
        subproductplandto = subproductplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        subproductplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(subproductplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取子计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchchildplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchChildPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品产品计划获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchCurProductPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取数据集", tags = {"产品计划" } ,notes = "根据产品产品计划获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplanstory")
	public ResponseEntity<List<SubProductPlanDTO>> fetchCurProductPlanStoryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefault")
	public ResponseEntity<List<SubProductPlanDTO>> fetchDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefaultparent")
	public ResponseEntity<List<SubProductPlanDTO>> fetchDefaultParentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品产品计划获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplancodelist")
	public ResponseEntity<List<SubProductPlanDTO>> fetchPlanCodeListByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplantasks")
	public ResponseEntity<List<SubProductPlanDTO>> fetchPlanTasksByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品产品计划获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchproductquery")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProductQueryByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目立项", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectapp")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProjectAppByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品产品计划获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchProjectPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取跟计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchrootplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchRootPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取任务计划", tags = {"产品计划" } ,notes = "根据产品产品计划获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchtaskplan")
	public ResponseEntity<List<SubProductPlanDTO>> fetchTaskPlanByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_parent_eq(productplan_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<SubProductPlanDTO> list = subproductplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

