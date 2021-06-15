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

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "新建产品计划", tags = {"产品计划" },  notes = "新建产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans")
    @Transactional
    public ResponseEntity<ProductPlanDTO> create(@Validated @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
		productplanService.create(domain);
        if(!productplanRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'READ')")
    @ApiOperation(value = "获取产品计划", tags = {"产品计划" },  notes = "获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> get(@PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(productplan_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'DELETE')")
    @ApiOperation(value = "删除产品计划", tags = {"产品计划" },  notes = "删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productplan_id") Long productplan_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DELETE')")
    @ApiOperation(value = "批量删除产品计划", tags = {"产品计划" },  notes = "批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(productplan_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "批关联BUG", tags = {"产品计划" },  notes = "批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchlinkbug")
    public ResponseEntity<ProductPlanDTO> batchLinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "批关联需求", tags = {"产品计划" },  notes = "批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchlinkstory")
    public ResponseEntity<ProductPlanDTO> batchLinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "批量解除关联Bug", tags = {"产品计划" },  notes = "批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "批量解除关联需求", tags = {"产品计划" },  notes = "批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "检查产品计划", tags = {"产品计划" },  notes = "检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductPlanDTO productplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(productplanMapping.toDomain(productplandto)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE激活计划", tags = {"产品计划" },  notes = "EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eeactiveplan")
    public ResponseEntity<ProductPlanDTO> eeActivePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeActivePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE取消计划", tags = {"产品计划" },  notes = "EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eecancelplan")
    public ResponseEntity<ProductPlanDTO> eeCancelPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeCancelPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE关闭计划", tags = {"产品计划" },  notes = "EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eecloseplan")
    public ResponseEntity<ProductPlanDTO> eeClosePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeClosePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE完成计划", tags = {"产品计划" },  notes = "EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eefinishplan")
    public ResponseEntity<ProductPlanDTO> eeFinishPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeFinishPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE暂停计划", tags = {"产品计划" },  notes = "EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eepauseplan")
    public ResponseEntity<ProductPlanDTO> eePausePlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eePausePlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "继续计划", tags = {"产品计划" },  notes = "继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eerestartplan")
    public ResponseEntity<ProductPlanDTO> eeRestartPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeRestartPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "EE开始计划", tags = {"产品计划" },  notes = "EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/eestartplan")
    public ResponseEntity<ProductPlanDTO> eeStartPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.eeStartPlan(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'CREATE')")
    @ApiOperation(value = "获取产品计划草稿", tags = {"产品计划" },  notes = "获取产品计划草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraft(ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'READ')")
    @ApiOperation(value = "获取上一个计划的名称", tags = {"产品计划" },  notes = "获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/getoldplanname")
    public ResponseEntity<ProductPlanDTO> getOldPlanName(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.getOldPlanName(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'CREATE')")
    @ApiOperation(value = "导入计划模板", tags = {"产品计划" },  notes = "导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTemplet(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'LINKBUG')")
    @ApiOperation(value = "关联Bug", tags = {"产品计划" },  notes = "关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("test('ZT_PRODUCTPLAN', #productplan_id, 'LINKSTORY')")
    @ApiOperation(value = "关联需求", tags = {"产品计划" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "关联任务", tags = {"产品计划" },  notes = "关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/linktask")
    public ResponseEntity<ProductPlanDTO> linkTask(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.linkTask(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "移动端产品计划计数器", tags = {"产品计划" },  notes = "移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/mobproductplancounter")
    public ResponseEntity<ProductPlanDTO> mobProductPlanCounter(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "保存产品计划", tags = {"产品计划" },  notes = "保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/save")
    public ResponseEntity<ProductPlanDTO> save(@RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        productplanService.save(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "解除关联Bug", tags = {"产品计划" },  notes = "解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBug(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "解除关联需求", tags = {"产品计划" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStory(@PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain);
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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
    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'READ')")
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

    @ApiOperation(value = "生成产品计划报表", tags = {"产品计划"}, notes = "生成产品计划报表")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProductPlanSearchContext context, HttpServletResponse response) {
        try {
            productplanRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productplanRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, productplanRuntime);
        }
    }

    @ApiOperation(value = "打印产品计划", tags = {"产品计划"}, notes = "打印产品计划")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("productplan_ids") Set<Long> productplan_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = productplanRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProductPlan> domains = new ArrayList<>();
            for (Long productplan_id : productplan_ids) {
                domains.add(productplanService.get( productplan_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProductPlan[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productplanRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", productplan_ids, e.getMessage()), Errors.INTERNALERROR, productplanRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/{action}")
    public ResponseEntity<ProductPlanDTO> dynamicCall(@PathVariable("productplan_id") Long productplan_id , @PathVariable("action") String action , @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanService.dynamicCall(productplan_id, action, productplanMapping.toDomain(productplandto));
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立产品计划", tags = {"产品计划" },  notes = "根据产品建立产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans")
    public ResponseEntity<ProductPlanDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
		productplanService.create(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', #productplan_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品计划", tags = {"产品计划" },  notes = "根据产品获取产品计划")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan domain = productplanService.get(productplan_id);
        if (domain == null || !(product_id.equals(domain.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'DELETE', #productplan_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品计划", tags = {"产品计划" },  notes = "根据产品删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id) {
        ProductPlan testget = productplanService.get(productplan_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(productplanService.remove(productplan_id));
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品计划", tags = {"产品计划" },  notes = "根据产品批量删除产品计划")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productplanService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'UPDATE', #productplan_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新产品计划", tags = {"产品计划" },  notes = "根据产品更新产品计划")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}")
    public ResponseEntity<ProductPlanDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan testget = productplanService.get(productplan_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
		productplanService.update(domain);
        ProductPlanDTO dto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品批关联BUG", tags = {"产品计划" },  notes = "根据产品批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchlinkbug")
    public ResponseEntity<ProductPlanDTO> batchLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品批关联需求", tags = {"产品计划" },  notes = "根据产品批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchlinkstory")
    public ResponseEntity<ProductPlanDTO> batchLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchLinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"产品计划" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkbug")
    public ResponseEntity<ProductPlanDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品批量解除关联需求", tags = {"产品计划" },  notes = "根据产品批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/batchunlinkstory")
    public ResponseEntity<ProductPlanDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.batchUnlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查产品计划", tags = {"产品计划" },  notes = "根据产品检查产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productplanService.checkKey(productplanMapping.toDomain(productplandto)));
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE激活计划", tags = {"产品计划" },  notes = "根据产品EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eeactiveplan")
    public ResponseEntity<ProductPlanDTO> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeActivePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE取消计划", tags = {"产品计划" },  notes = "根据产品EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eecancelplan")
    public ResponseEntity<ProductPlanDTO> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeCancelPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE关闭计划", tags = {"产品计划" },  notes = "根据产品EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eecloseplan")
    public ResponseEntity<ProductPlanDTO> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeClosePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE完成计划", tags = {"产品计划" },  notes = "根据产品EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eefinishplan")
    public ResponseEntity<ProductPlanDTO> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeFinishPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE暂停计划", tags = {"产品计划" },  notes = "根据产品EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eepauseplan")
    public ResponseEntity<ProductPlanDTO> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eePausePlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品继续计划", tags = {"产品计划" },  notes = "根据产品继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eerestartplan")
    public ResponseEntity<ProductPlanDTO> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeRestartPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品EE开始计划", tags = {"产品计划" },  notes = "根据产品EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/eestartplan")
    public ResponseEntity<ProductPlanDTO> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.eeStartPlan(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取产品计划草稿", tags = {"产品计划" },  notes = "根据产品获取产品计划草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/getdraft")
    public ResponseEntity<ProductPlanDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductPlanDTO dto) {
        ProductPlan domain = productplanMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(productplanService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', #productplan_id, 'READ')")
    @ApiOperation(value = "根据产品获取上一个计划的名称", tags = {"产品计划" },  notes = "根据产品获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/getoldplanname")
    public ResponseEntity<ProductPlanDTO> getOldPlanNameByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.getOldPlanName(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'CREATE', #productplan_id, 'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板", tags = {"产品计划" },  notes = "根据产品导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/importplantemplet")
    public ResponseEntity<ProductPlanDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.importPlanTemplet(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'MANAGE', #productplan_id, 'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"产品计划" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkbug")
    public ResponseEntity<ProductPlanDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'MANAGE', #productplan_id, 'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"产品计划" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linkstory")
    public ResponseEntity<ProductPlanDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品关联任务", tags = {"产品计划" },  notes = "根据产品关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/linktask")
    public ResponseEntity<ProductPlanDTO> linkTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.linkTask(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品移动端产品计划计数器", tags = {"产品计划" },  notes = "根据产品移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/mobproductplancounter")
    public ResponseEntity<ProductPlanDTO> mobProductPlanCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.mobProductPlanCounter(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品保存产品计划", tags = {"产品计划" },  notes = "根据产品保存产品计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/save")
    public ResponseEntity<ProductPlanDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        productplanService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplanMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"产品计划" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkbug")
    public ResponseEntity<ProductPlanDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkBug(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("quickTest('ZT_PRODUCTPLAN', 'DENY')")
    @ApiOperation(value = "根据产品解除关联需求", tags = {"产品计划" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/unlinkstory")
    public ResponseEntity<ProductPlanDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanMapping.toDomain(productplandto);
        domain.setProduct(product_id);
        domain.setId(productplan_id);
        domain = productplanService.unlinkStory(domain) ;
        productplandto = productplanMapping.toDto(domain);
        Map<String, Integer> opprivs = productplanRuntime.getOPPrivs(domain.getId());    
        productplandto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }

    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取子计划", tags = {"产品计划" } ,notes = "根据产品获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchchildplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchChildPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchChildPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"产品计划" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品计划" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchcurproductplanstory")
	public ResponseEntity<List<ProductPlanDTO>> fetchCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchCurProductPlanStory(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品计划" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchdefault")
	public ResponseEntity<List<ProductPlanDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefault(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取默认查询", tags = {"产品计划" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchdefaultparent")
	public ResponseEntity<List<ProductPlanDTO>> fetchDefaultParentByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchDefaultParent(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"产品计划" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchplancodelist")
	public ResponseEntity<List<ProductPlanDTO>> fetchPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanCodeList(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目计划任务（项目管理-项目计划）", tags = {"产品计划" } ,notes = "根据产品获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchplantasks")
	public ResponseEntity<List<ProductPlanDTO>> fetchPlanTasksByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchPlanTasks(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"产品计划" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchproductquery")
	public ResponseEntity<List<ProductPlanDTO>> fetchProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProductQuery(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目立项", tags = {"产品计划" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectapp")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectApp(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"产品计划" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchprojectplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchProjectPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取跟计划", tags = {"产品计划" } ,notes = "根据产品获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchrootplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchRootPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchRootPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PRODUCTPLAN', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取任务计划", tags = {"产品计划" } ,notes = "根据产品获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/fetchtaskplan")
	public ResponseEntity<List<ProductPlanDTO>> fetchTaskPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductPlanSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProductPlan> domains = productplanService.searchTaskPlan(context) ;
        List<ProductPlanDTO> list = productplanMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

