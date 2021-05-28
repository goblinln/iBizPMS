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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.HistoryRuntime;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("WebApi-history")
@RequestMapping("")
public class HistoryResource {

    @Autowired
    public IHistoryService historyService;

    @Autowired
    public HistoryRuntime historyRuntime;

    @Autowired
    @Lazy
    public HistoryMapping historyMapping;

    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"操作历史" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchdefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/histories/{history_id}/{action}")
    public ResponseEntity<HistoryDTO> dynamicCall(@PathVariable("history_id") Long history_id , @PathVariable("action") String action , @RequestBody HistoryDTO historydto) {
        History domain = historyService.dynamicCall(history_id, action, historyMapping.toDomain(historydto));
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'READ')")
	@ApiOperation(value = "根据系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByAction(@PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取DEFAULT", tags = {"操作历史" } ,notes = "根据Bug获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取查询用户使用年", tags = {"操作历史" } ,notes = "根据Bug获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug建立操作历史", tags = {"操作历史" },  notes = "根据Bug建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/histories")
    public ResponseEntity<HistoryDTO> createByBug(@PathVariable("bug_id") Long bug_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取ProductTrends", tags = {"操作历史" } ,notes = "根据Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据Bug获取操作历史", tags = {"操作历史" },  notes = "根据Bug获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取MobType", tags = {"操作历史" } ,notes = "根据Bug获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取Type", tags = {"操作历史" } ,notes = "根据Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByBug(@PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据Bug更新操作历史", tags = {"操作历史" },  notes = "根据Bug更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug获取操作历史草稿", tags = {"操作历史" },  notes = "根据Bug获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByBug(@PathVariable("bug_id") Long bug_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取MobType", tags = {"操作历史" } ,notes = "根据产品获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Type", tags = {"操作历史" } ,notes = "根据产品获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品计划获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划建立操作历史", tags = {"操作历史" },  notes = "根据产品计划建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取操作历史", tags = {"操作历史" },  notes = "根据产品计划获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取MobType", tags = {"操作历史" } ,notes = "根据产品计划获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取Type", tags = {"操作历史" } ,notes = "根据产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划更新操作历史", tags = {"操作历史" },  notes = "根据产品计划更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品计划获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取DEFAULT", tags = {"操作历史" } ,notes = "根据待办获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取查询用户使用年", tags = {"操作历史" } ,notes = "根据待办获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办建立操作历史", tags = {"操作历史" },  notes = "根据待办建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/histories")
    public ResponseEntity<HistoryDTO> createByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取ProductTrends", tags = {"操作历史" } ,notes = "根据待办获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据待办获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
    @ApiOperation(value = "根据待办获取操作历史", tags = {"操作历史" },  notes = "根据待办获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取MobType", tags = {"操作历史" } ,notes = "根据待办获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据待办获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取Type", tags = {"操作历史" } ,notes = "根据待办获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'UPDATE')")
    @ApiOperation(value = "根据待办更新操作历史", tags = {"操作历史" },  notes = "根据待办更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办获取操作历史草稿", tags = {"操作历史" },  notes = "根据待办获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTodo(@PathVariable("todo_id") Long todo_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据待办系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"操作历史" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取查询用户使用年", tags = {"操作历史" } ,notes = "根据任务获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务建立操作历史", tags = {"操作历史" },  notes = "根据任务建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/histories")
    public ResponseEntity<HistoryDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取ProductTrends", tags = {"操作历史" } ,notes = "根据任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取操作历史", tags = {"操作历史" },  notes = "根据任务获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取MobType", tags = {"操作历史" } ,notes = "根据任务获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取Type", tags = {"操作历史" } ,notes = "根据任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByTask(@PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新操作历史", tags = {"操作历史" },  notes = "根据任务更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取操作历史草稿", tags = {"操作历史" },  notes = "根据任务获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTask(@PathVariable("task_id") Long task_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取DEFAULT", tags = {"操作历史" } ,notes = "根据发布获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取查询用户使用年", tags = {"操作历史" } ,notes = "根据发布获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布建立操作历史", tags = {"操作历史" },  notes = "根据发布建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/histories")
    public ResponseEntity<HistoryDTO> createByRelease(@PathVariable("release_id") Long release_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取ProductTrends", tags = {"操作历史" } ,notes = "根据发布获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据发布获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据发布获取操作历史", tags = {"操作历史" },  notes = "根据发布获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByRelease(@PathVariable("release_id") Long release_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取MobType", tags = {"操作历史" } ,notes = "根据发布获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据发布获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取Type", tags = {"操作历史" } ,notes = "根据发布获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByRelease(@PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据发布更新操作历史", tags = {"操作历史" },  notes = "根据发布更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByRelease(@PathVariable("release_id") Long release_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布获取操作历史草稿", tags = {"操作历史" },  notes = "根据发布获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByRelease(@PathVariable("release_id") Long release_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取DEFAULT", tags = {"操作历史" } ,notes = "根据周报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取查询用户使用年", tags = {"操作历史" } ,notes = "根据周报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报建立操作历史", tags = {"操作历史" },  notes = "根据周报建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取ProductTrends", tags = {"操作历史" } ,notes = "根据周报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据周报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
    @ApiOperation(value = "根据周报获取操作历史", tags = {"操作历史" },  notes = "根据周报获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取MobType", tags = {"操作历史" } ,notes = "根据周报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据周报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取Type", tags = {"操作历史" } ,notes = "根据周报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'UPDATE')")
    @ApiOperation(value = "根据周报更新操作历史", tags = {"操作历史" },  notes = "根据周报更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报获取操作历史草稿", tags = {"操作历史" },  notes = "根据周报获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据周报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取查询用户使用年", tags = {"操作历史" } ,notes = "根据测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告建立操作历史", tags = {"操作历史" },  notes = "根据测试报告建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取ProductTrends", tags = {"操作历史" } ,notes = "根据测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
    @ApiOperation(value = "根据测试报告获取操作历史", tags = {"操作历史" },  notes = "根据测试报告获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取MobType", tags = {"操作历史" } ,notes = "根据测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取Type", tags = {"操作历史" } ,notes = "根据测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'UPDATE')")
    @ApiOperation(value = "根据测试报告更新操作历史", tags = {"操作历史" },  notes = "根据测试报告更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试报告获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestReport(@PathVariable("testreport_id") Long testreport_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取查询用户使用年", tags = {"操作历史" } ,notes = "根据文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立操作历史", tags = {"操作历史" },  notes = "根据文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取ProductTrends", tags = {"操作历史" } ,notes = "根据文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取操作历史", tags = {"操作历史" },  notes = "根据文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取MobType", tags = {"操作历史" } ,notes = "根据文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取Type", tags = {"操作历史" } ,notes = "根据文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新操作历史", tags = {"操作历史" },  notes = "根据文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取查询用户使用年", tags = {"操作历史" } ,notes = "根据文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档建立操作历史", tags = {"操作历史" },  notes = "根据文档建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/histories")
    public ResponseEntity<HistoryDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取ProductTrends", tags = {"操作历史" } ,notes = "根据文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据文档获取操作历史", tags = {"操作历史" },  notes = "根据文档获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取MobType", tags = {"操作历史" } ,notes = "根据文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取Type", tags = {"操作历史" } ,notes = "根据文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'UPDATE')")
    @ApiOperation(value = "根据文档更新操作历史", tags = {"操作历史" },  notes = "根据文档更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取DEFAULT", tags = {"操作历史" } ,notes = "根据日报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取查询用户使用年", tags = {"操作历史" } ,notes = "根据日报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报建立操作历史", tags = {"操作历史" },  notes = "根据日报建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取ProductTrends", tags = {"操作历史" } ,notes = "根据日报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据日报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
    @ApiOperation(value = "根据日报获取操作历史", tags = {"操作历史" },  notes = "根据日报获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取MobType", tags = {"操作历史" } ,notes = "根据日报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据日报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取Type", tags = {"操作历史" } ,notes = "根据日报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'UPDATE')")
    @ApiOperation(value = "根据日报更新操作历史", tags = {"操作历史" },  notes = "根据日报更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报获取操作历史草稿", tags = {"操作历史" },  notes = "根据日报获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据日报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取DEFAULT", tags = {"操作历史" } ,notes = "根据月报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取查询用户使用年", tags = {"操作历史" } ,notes = "根据月报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报建立操作历史", tags = {"操作历史" },  notes = "根据月报建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取ProductTrends", tags = {"操作历史" } ,notes = "根据月报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据月报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
    @ApiOperation(value = "根据月报获取操作历史", tags = {"操作历史" },  notes = "根据月报获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取MobType", tags = {"操作历史" } ,notes = "根据月报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据月报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取Type", tags = {"操作历史" } ,notes = "根据月报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'UPDATE')")
    @ApiOperation(value = "根据月报更新操作历史", tags = {"操作历史" },  notes = "根据月报更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmonthlies/{ibzmonthly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报获取操作历史草稿", tags = {"操作历史" },  notes = "根据月报获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据月报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取DEFAULT", tags = {"操作历史" } ,notes = "根据汇报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取查询用户使用年", tags = {"操作历史" } ,notes = "根据汇报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报建立操作历史", tags = {"操作历史" },  notes = "根据汇报建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取ProductTrends", tags = {"操作历史" } ,notes = "根据汇报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据汇报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报获取操作历史", tags = {"操作历史" },  notes = "根据汇报获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取MobType", tags = {"操作历史" } ,notes = "根据汇报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据汇报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取Type", tags = {"操作历史" } ,notes = "根据汇报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报更新操作历史", tags = {"操作历史" },  notes = "根据汇报更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报获取操作历史草稿", tags = {"操作历史" },  notes = "根据汇报获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据汇报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本建立操作历史", tags = {"操作历史" },  notes = "根据测试版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取操作历史", tags = {"操作历史" },  notes = "根据测试版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取MobType", tags = {"操作历史" } ,notes = "根据测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取Type", tags = {"操作历史" } ,notes = "根据测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新操作历史", tags = {"操作历史" },  notes = "根据测试版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取查询用户使用年", tags = {"操作历史" } ,notes = "根据测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件建立操作历史", tags = {"操作历史" },  notes = "根据测试套件建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取ProductTrends", tags = {"操作历史" } ,notes = "根据测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件获取操作历史", tags = {"操作历史" },  notes = "根据测试套件获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取MobType", tags = {"操作历史" } ,notes = "根据测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取Type", tags = {"操作历史" } ,notes = "根据测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件更新操作历史", tags = {"操作历史" },  notes = "根据测试套件更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试套件获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取查询用户使用年", tags = {"操作历史" } ,notes = "根据测试用例获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例建立操作历史", tags = {"操作历史" },  notes = "根据测试用例建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/histories")
    public ResponseEntity<HistoryDTO> createByCase(@PathVariable("case_id") Long case_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取ProductTrends", tags = {"操作历史" } ,notes = "根据测试用例获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据测试用例获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取操作历史", tags = {"操作历史" },  notes = "根据测试用例获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取MobType", tags = {"操作历史" } ,notes = "根据测试用例获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据测试用例获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取Type", tags = {"操作历史" } ,notes = "根据测试用例获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByCase(@PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例更新操作历史", tags = {"操作历史" },  notes = "根据测试用例更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByCase(@PathVariable("case_id") Long case_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试用例获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByCase(@PathVariable("case_id") Long case_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本建立操作历史", tags = {"操作历史" },  notes = "根据版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/histories")
    public ResponseEntity<HistoryDTO> createByBuild(@PathVariable("build_id") Long build_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本获取操作历史", tags = {"操作历史" },  notes = "根据版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByBuild(@PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取MobType", tags = {"操作历史" } ,notes = "根据版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取Type", tags = {"操作历史" } ,notes = "根据版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByBuild(@PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本更新操作历史", tags = {"操作历史" },  notes = "根据版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByBuild(@PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByBuild(@PathVariable("build_id") Long build_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"操作历史" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取查询用户使用年", tags = {"操作历史" } ,notes = "根据用例库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库建立操作历史", tags = {"操作历史" },  notes = "根据用例库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取ProductTrends", tags = {"操作历史" } ,notes = "根据用例库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据用例库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
    @ApiOperation(value = "根据用例库获取操作历史", tags = {"操作历史" },  notes = "根据用例库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取MobType", tags = {"操作历史" } ,notes = "根据用例库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据用例库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取Type", tags = {"操作历史" } ,notes = "根据用例库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'UPDATE')")
    @ApiOperation(value = "根据用例库更新操作历史", tags = {"操作历史" },  notes = "根据用例库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库获取操作历史草稿", tags = {"操作历史" },  notes = "根据用例库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据用例库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"操作历史" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取查询用户使用年", tags = {"操作历史" } ,notes = "根据需求获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求建立操作历史", tags = {"操作历史" },  notes = "根据需求建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/histories")
    public ResponseEntity<HistoryDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取ProductTrends", tags = {"操作历史" } ,notes = "根据需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求获取操作历史", tags = {"操作历史" },  notes = "根据需求获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取MobType", tags = {"操作历史" } ,notes = "根据需求获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取Type", tags = {"操作历史" } ,notes = "根据需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByStory(@PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求更新操作历史", tags = {"操作历史" },  notes = "根据需求更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求获取操作历史草稿", tags = {"操作历史" },  notes = "根据需求获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByStory(@PathVariable("story_id") Long story_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取MobType", tags = {"操作历史" } ,notes = "根据项目获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取Type", tags = {"操作历史" } ,notes = "根据项目获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'COLLECT')")
    @ApiOperation(value = "根据产品收藏", tags = {"操作历史" },  notes = "根据产品收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"操作历史" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"操作历史" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbycustom")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByCustom(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"操作历史" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"操作历史" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproductnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProductNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"操作历史" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproject")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProject(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"操作历史" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyprojectnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProjectNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"操作历史" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurdoclib")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurDocLib(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"操作历史" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyfavourites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"操作历史" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrootmodulemulu")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootModuleMuLu(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立操作历史", tags = {"操作历史" },  notes = "根据产品文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取操作历史", tags = {"操作历史" },  notes = "根据产品文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取MobType", tags = {"操作历史" } ,notes = "根据产品文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取Type", tags = {"操作历史" } ,notes = "根据产品文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新操作历史", tags = {"操作历史" },  notes = "根据产品文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"操作历史" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"操作历史" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/assignto")
    public ResponseEntity<HistoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.assignTo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"操作历史" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchunlinkbug")
    public ResponseEntity<HistoryDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITES')")
    @ApiOperation(value = "根据产品Bug收藏", tags = {"操作历史" },  notes = "根据产品Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/bugfavorites")
    public ResponseEntity<HistoryDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.bugFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"操作历史" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/bugnfavorites")
    public ResponseEntity<HistoryDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.bugNFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品版本批量解除关联Bug", tags = {"操作历史" },  notes = "根据产品版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildbatchunlinkbug")
    public ResponseEntity<HistoryDTO> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildBatchUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本关联Bug", tags = {"操作历史" },  notes = "根据产品版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildlinkbug")
    public ResponseEntity<HistoryDTO> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildLinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联Bug", tags = {"操作历史" },  notes = "根据产品版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildunlinkbug")
    public ResponseEntity<HistoryDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"操作历史" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/close")
    public ResponseEntity<HistoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.close(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRM')")
    @ApiOperation(value = "根据产品确认", tags = {"操作历史" },  notes = "根据产品确认")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/confirm")
    public ResponseEntity<HistoryDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.confirm(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"操作历史" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbug")
    public ResponseEntity<HistoryDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"操作历史" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaasebatchunlinkbug")
    public ResponseEntity<HistoryDTO> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaaseBatchUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）", tags = {"操作历史" },  notes = "根据产品关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaselinkbugbybug")
    public ResponseEntity<HistoryDTO> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseLinkBugbyBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"操作历史" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaselinkbugbyleftbug")
    public ResponseEntity<HistoryDTO> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseLinkBugbyLeftBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RESOLVE')")
    @ApiOperation(value = "根据产品移除关联Bug（遗留Bug）", tags = {"操作历史" },  notes = "根据产品移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<HistoryDTO> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseUnLinkBugbyLeftBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"操作历史" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaseunlinkbug")
    public ResponseEntity<HistoryDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RESOLVE')")
    @ApiOperation(value = "根据产品解决", tags = {"操作历史" },  notes = "根据产品解决")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/resolve")
    public ResponseEntity<HistoryDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.resolve(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品行为", tags = {"操作历史" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/sendmessage")
    public ResponseEntity<HistoryDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMessage(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"操作历史" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/sendmsgpreprocess")
    public ResponseEntity<HistoryDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMsgPreProcess(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品TestScript", tags = {"操作历史" },  notes = "根据产品TestScript")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/testscript")
    public ResponseEntity<HistoryDTO> testScriptByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.testScript(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TOSTORY')")
    @ApiOperation(value = "根据产品转需求", tags = {"操作历史" },  notes = "根据产品转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/tostory")
    public ResponseEntity<HistoryDTO> toStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.toStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"操作历史" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkbug")
    public ResponseEntity<HistoryDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新需求版本", tags = {"操作历史" },  notes = "根据产品更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/updatestoryversion")
    public ResponseEntity<HistoryDTO> updateStoryVersionByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.updateStoryVersion(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug", tags = {"操作历史" } ,notes = "根据产品获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchassignedtomybug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug（PC）", tags = {"操作历史" } ,notes = "根据产品获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchassignedtomybugpc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyBugPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyBugPc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联bug(遗留的)", tags = {"操作历史" } ,notes = "根据产品获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbugsbybuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugsByBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugsByBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的已解决的Bugs集合", tags = {"操作历史" } ,notes = "根据产品获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildLinkResolvedBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildLinkResolvedBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildopenbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildOpenBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildOpenBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugmodule")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugModule(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugModule_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugModule_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建分类", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugopenedby")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugOpenedByByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugOpenedBy(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugOpenedBy_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugres")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugRESByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugRES(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugRESOLVEDBYByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugRESOLVEDBY_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决方案分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugResolution_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugResolution_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-严重程度分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugSeverity_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugSeverity_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-状态分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugStatus_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugStatus_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-类型分布(项目)", tags = {"操作历史" } ,notes = "根据产品获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildproducebugtype_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildProduceBugType_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildProduceBugType_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取当前用户解决的Bug", tags = {"操作历史" } ,notes = "根据产品获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcuruserresolve")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurUserResolveByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurUserResolve(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"操作历史" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchesbulk")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchESBulk(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我代理的Bug", tags = {"操作历史" } ,notes = "根据产品获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyagentbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyAgentBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyAgentBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的Bug数", tags = {"操作历史" } ,notes = "根据产品获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmycuropenedbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCurOpenedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCurOpenedBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"操作历史" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyfavorites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavorites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划关联bug（去除已关联）", tags = {"操作历史" } ,notes = "根据产品获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchnotcurplanlinkbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotCurPlanLinkBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotCurPlanLinkBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取遗留得Bug(项目)", tags = {"操作历史" } ,notes = "根据产品获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojectbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleasebugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleaseleftbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseLeftBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseLeftBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（遗留）", tags = {"操作历史" } ,notes = "根据产品获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleaselinkableleftbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseLinkableLeftBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseLinkableLeftBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（已解决）", tags = {"操作历史" } ,notes = "根据产品获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseLinkableResolvedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseLinkableResolvedBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（未解决）", tags = {"操作历史" } ,notes = "根据产品获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportbugs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReportBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReportBugs(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取需求来源Bug", tags = {"操作历史" } ,notes = "根据产品获取需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchstoryformbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryStoryFormBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchStoryFormBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务相关bug", tags = {"操作历史" } ,notes = "根据产品获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtaskrelatedbug")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTaskRelatedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTaskRelatedBug(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品Bug获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品Bug获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug建立操作历史", tags = {"操作历史" },  notes = "根据产品Bug建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品Bug获取操作历史", tags = {"操作历史" },  notes = "根据产品Bug获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取MobType", tags = {"操作历史" } ,notes = "根据产品Bug获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取Type", tags = {"操作历史" } ,notes = "根据产品Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品Bug更新操作历史", tags = {"操作历史" },  notes = "根据产品Bug更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品Bug获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品状态变更（激活）", tags = {"操作历史" },  notes = "根据产品状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"操作历史" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchunlinkbug")
    public ResponseEntity<HistoryDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品状态变更", tags = {"操作历史" },  notes = "根据产品状态变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/changestatus")
    public ResponseEntity<HistoryDTO> changeStatusByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.changeStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"操作历史" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbug")
    public ResponseEntity<HistoryDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）", tags = {"操作历史" },  notes = "根据产品关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbugbybug")
    public ResponseEntity<HistoryDTO> linkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBugbyBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"操作历史" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbugbyleftbug")
    public ResponseEntity<HistoryDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBugbyLeftBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"操作历史" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkstory")
    public ResponseEntity<HistoryDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移动端发布计数器", tags = {"操作历史" },  notes = "根据产品移动端发布计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/mobreleasecounter")
    public ResponseEntity<HistoryDTO> mobReleaseCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobReleaseCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品一键发布", tags = {"操作历史" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/oneclickrelease")
    public ResponseEntity<HistoryDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.oneClickRelease(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }


    @ApiOperation(value = "根据产品批量保存操作历史", tags = {"操作历史" },  notes = "根据产品批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
             
        }
        historyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TERMINATE')")
    @ApiOperation(value = "根据产品状态变更（停止维护）", tags = {"操作历史" },  notes = "根据产品状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/terminate")
    public ResponseEntity<HistoryDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.terminate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"操作历史" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkbug")
    public ResponseEntity<HistoryDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品解除关联需求", tags = {"操作历史" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkstory")
    public ResponseEntity<HistoryDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联发布", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportrelease")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReportReleaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReportRelease(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品发布获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品发布获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布建立操作历史", tags = {"操作历史" },  notes = "根据产品发布建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品发布获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品发布获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品发布获取操作历史", tags = {"操作历史" },  notes = "根据产品发布获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取MobType", tags = {"操作历史" } ,notes = "根据产品发布获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品发布获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取Type", tags = {"操作历史" } ,notes = "根据产品发布获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布更新操作历史", tags = {"操作历史" },  notes = "根据产品发布更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品发布获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品关联测试", tags = {"操作历史" },  notes = "根据产品关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkcase")
    public ResponseEntity<HistoryDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移动端测试套件计数器", tags = {"操作历史" },  notes = "根据产品移动端测试套件计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/mobtestsuitecount")
    public ResponseEntity<HistoryDTO> mobTestSuiteCountByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobTestSuiteCount(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品未关联测试", tags = {"操作历史" },  notes = "根据产品未关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkcase")
    public ResponseEntity<HistoryDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取公开套件", tags = {"操作历史" } ,notes = "根据产品获取公开套件")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchpublictestsuite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryPublicTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchPublicTestSuite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件建立操作历史", tags = {"操作历史" },  notes = "根据产品测试套件建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件获取操作历史", tags = {"操作历史" },  notes = "根据产品测试套件获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取MobType", tags = {"操作历史" } ,notes = "根据产品测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取Type", tags = {"操作历史" } ,notes = "根据产品测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件更新操作历史", tags = {"操作历史" },  notes = "根据产品测试套件更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试套件获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品激活", tags = {"操作历史" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品阻塞", tags = {"操作历史" },  notes = "根据产品阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/block")
    public ResponseEntity<HistoryDTO> blockByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.block(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品关闭", tags = {"操作历史" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/close")
    public ResponseEntity<HistoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.close(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"操作历史" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkcase")
    public ResponseEntity<HistoryDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移动端测试版本计数器", tags = {"操作历史" },  notes = "根据产品移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/mobtesttaskcounter")
    public ResponseEntity<HistoryDTO> mobTestTaskCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobTestTaskCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品开始", tags = {"操作历史" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/start")
    public ResponseEntity<HistoryDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.start(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"操作历史" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkcase")
    public ResponseEntity<HistoryDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的测试单", tags = {"操作历史" } ,notes = "根据产品获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmytesttaskpc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTestTaskPc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本建立操作历史", tags = {"操作历史" },  notes = "根据产品测试版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取操作历史", tags = {"操作历史" },  notes = "根据产品测试版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取MobType", tags = {"操作历史" } ,notes = "根据产品测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取Type", tags = {"操作历史" } ,notes = "根据产品测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新操作历史", tags = {"操作历史" },  notes = "根据产品测试版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息", tags = {"操作历史" },  notes = "根据产品根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotesttask")
    public ResponseEntity<HistoryDTO> getInfoTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTask(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据起始时间获取概况信息", tags = {"操作历史" },  notes = "根据产品根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotaskovbytime")
    public ResponseEntity<HistoryDTO> getInfoTaskOvByTimeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTaskOvByTime(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告概况信息（项目报告）", tags = {"操作历史" },  notes = "根据产品根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotesttaskovproject")
    public ResponseEntity<HistoryDTO> getInfoTestTaskOvProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskOvProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）", tags = {"操作历史" },  notes = "根据产品根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotesttaskproject")
    public ResponseEntity<HistoryDTO> getInfoTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（单测试）", tags = {"操作历史" },  notes = "根据产品根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotesttaskr")
    public ResponseEntity<HistoryDTO> getInfoTestTaskRByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskR(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（测试）", tags = {"操作历史" },  notes = "根据产品根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/getinfotesttasks")
    public ResponseEntity<HistoryDTO> getInfoTestTaskSByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskS(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息", tags = {"操作历史" },  notes = "根据产品根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/gettestreportbasicinfo")
    public ResponseEntity<HistoryDTO> getTestReportBasicInfoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTestReportBasicInfo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息（项目报告）", tags = {"操作历史" },  notes = "根据产品根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/gettestreportproject")
    public ResponseEntity<HistoryDTO> getTestReportProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTestReportProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告建立操作历史", tags = {"操作历史" },  notes = "根据产品测试报告建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试报告获取操作历史", tags = {"操作历史" },  notes = "根据产品测试报告获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取MobType", tags = {"操作历史" } ,notes = "根据产品测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取Type", tags = {"操作历史" } ,notes = "根据产品测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试报告更新操作历史", tags = {"操作历史" },  notes = "根据产品测试报告更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试报告获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品关联Bug", tags = {"操作历史" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbug")
    public ResponseEntity<HistoryDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品关联需求", tags = {"操作历史" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkstory")
    public ResponseEntity<HistoryDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移动端项目版本计数器", tags = {"操作历史" },  notes = "根据产品移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/mobprojectbuildcounter")
    public ResponseEntity<HistoryDTO> mobProjectBuildCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobProjectBuildCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品一键发布", tags = {"操作历史" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/oneclickrelease")
    public ResponseEntity<HistoryDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.oneClickRelease(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品移除Bug关联", tags = {"操作历史" },  notes = "根据产品移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkbug")
    public ResponseEntity<HistoryDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移除关联需求", tags = {"操作历史" },  notes = "根据产品移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkstory")
    public ResponseEntity<HistoryDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Bug产品版本", tags = {"操作历史" } ,notes = "根据产品获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbugproductbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugProductBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugProductBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取Bug产品或者项目版本", tags = {"操作历史" } ,notes = "根据产品获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbugproductorprojectbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugProductOrProjectBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugProductOrProjectBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"操作历史" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取测试版本", tags = {"操作历史" } ,notes = "根据产品获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtestbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTestBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTestBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试轮次", tags = {"操作历史" } ,notes = "根据产品获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtestrounds")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTestRoundsByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTestRounds(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取更新日志", tags = {"操作历史" } ,notes = "根据产品获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchupdatelog")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryUpdateLogByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchUpdateLog(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本建立操作历史", tags = {"操作历史" },  notes = "根据产品版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本获取操作历史", tags = {"操作历史" },  notes = "根据产品版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取MobType", tags = {"操作历史" } ,notes = "根据产品版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取Type", tags = {"操作历史" } ,notes = "根据产品版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本更新操作历史", tags = {"操作历史" },  notes = "根据产品版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITE')")
    @ApiOperation(value = "根据产品行为", tags = {"操作历史" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/casefavorite")
    public ResponseEntity<HistoryDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.caseFavorite(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite", tags = {"操作历史" },  notes = "根据产品CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/casenfavorite")
    public ResponseEntity<HistoryDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.caseNFavorite(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRMCHANGE')")
    @ApiOperation(value = "根据产品确认用例变更", tags = {"操作历史" },  notes = "根据产品确认用例变更")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/confirmchange")
    public ResponseEntity<HistoryDTO> confirmChangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.confirmChange(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品确认需求变更", tags = {"操作历史" },  notes = "根据产品确认需求变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/confirmstorychange")
    public ResponseEntity<HistoryDTO> confirmstorychangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.confirmstorychange(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取或者状态", tags = {"操作历史" },  notes = "根据产品根据测试单获取或者状态")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}/getbytesttask")
    public ResponseEntity<HistoryDTO> getByTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getByTestTask(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASERESULT')")
    @ApiOperation(value = "根据产品获取测试单执行结果", tags = {"操作历史" },  notes = "根据产品获取测试单执行结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/gettesttaskcntrun")
    public ResponseEntity<HistoryDTO> getTestTaskCntRunByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTestTaskCntRun(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品测试单关联测试用例", tags = {"操作历史" },  notes = "根据产品测试单关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkcase")
    public ResponseEntity<HistoryDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'STORYLINK')")
    @ApiOperation(value = "根据产品移动端关联需求", tags = {"操作历史" },  notes = "根据产品移动端关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/moblinkcase")
    public ResponseEntity<HistoryDTO> mobLinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobLinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"操作历史" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/runcase")
    public ResponseEntity<HistoryDTO> runCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.runCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品runCases", tags = {"操作历史" },  notes = "根据产品runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/runcases")
    public ResponseEntity<HistoryDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.runCases(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存操作历史", tags = {"操作历史" },  notes = "根据产品批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
             
        }
        historyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"操作历史" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/testruncase")
    public ResponseEntity<HistoryDTO> testRunCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.testRunCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品testRunCases", tags = {"操作历史" },  notes = "根据产品testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/testruncases")
    public ResponseEntity<HistoryDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.testRunCases(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品套件关联", tags = {"操作历史" },  notes = "根据产品套件关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/testsuitelinkcase")
    public ResponseEntity<HistoryDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.testsuitelinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"操作历史" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkcase")
    public ResponseEntity<HistoryDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品unlinkCases", tags = {"操作历史" },  notes = "根据产品unlinkCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkcases")
    public ResponseEntity<HistoryDTO> unlinkCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkCases(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"操作历史" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinksuitecase")
    public ResponseEntity<HistoryDTO> unlinkSuiteCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkSuiteCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品unlinkSuiteCases", tags = {"操作历史" },  notes = "根据产品unlinkSuiteCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinksuitecases")
    public ResponseEntity<HistoryDTO> unlinkSuiteCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkSuiteCases(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取批量新建用例", tags = {"操作历史" } ,notes = "根据产品获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbatchnew")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBatchNewByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBatchNew(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的用例", tags = {"操作历史" } ,notes = "根据产品获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcuropenedcase")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurOpenedCase(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"操作历史" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcursuite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurSuite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"操作历史" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurtesttask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurTestTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"操作历史" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchesbulk")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchESBulk(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmodulereportcase")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleRePortCase(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-按模块-条目", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmodulereportcaseentry")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleRePortCaseEntry(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-按模块", tags = {"操作历史" } ,notes = "根据产品获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmodulereportcase_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleRePortCase_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"操作历史" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyfavorites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavorites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"操作历史" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchnotcurtestsuite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotCurTestSuite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"操作历史" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchnotcurtesttask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotCurTestTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例（项目关联）", tags = {"操作历史" } ,notes = "根据产品获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchnotcurtesttaskproject")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotCurTestTaskProject(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportcase")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRePortCase(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例-条目", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportcaseentry")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRePortCaseEntry(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联用例-关联用例", tags = {"操作历史" } ,notes = "根据产品获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportcase_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRePortCase_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunerreportcase")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunERRePortCase(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人-条目", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunerreportcaseentry")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunERRePortCaseEntry(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行人", tags = {"操作历史" } ,notes = "根据产品获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunerreportcase_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunERRePortCase_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunreportcase")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunRePortCase(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联--执行结果条目", tags = {"操作历史" } ,notes = "根据产品获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunreportcaseentry")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunRePortCaseEntry(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行结果", tags = {"操作历史" } ,notes = "根据产品获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrunreportcase_project")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRunRePortCase_Project(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品测试用例获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例建立操作历史", tags = {"操作历史" },  notes = "根据产品测试用例建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品测试用例获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品测试用例获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取操作历史", tags = {"操作历史" },  notes = "根据产品测试用例获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取MobType", tags = {"操作历史" } ,notes = "根据产品测试用例获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品测试用例获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取Type", tags = {"操作历史" } ,notes = "根据产品测试用例获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例更新操作历史", tags = {"操作历史" },  notes = "根据产品测试用例更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试用例获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品批关联BUG", tags = {"操作历史" },  notes = "根据产品批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchlinkbug")
    public ResponseEntity<HistoryDTO> batchLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchLinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品批关联需求", tags = {"操作历史" },  notes = "根据产品批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchlinkstory")
    public ResponseEntity<HistoryDTO> batchLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchLinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"操作历史" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchunlinkbug")
    public ResponseEntity<HistoryDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchUnlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品批量解除关联需求", tags = {"操作历史" },  notes = "根据产品批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchunlinkstory")
    public ResponseEntity<HistoryDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品EE激活计划", tags = {"操作历史" },  notes = "根据产品EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eeactiveplan")
    public ResponseEntity<HistoryDTO> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeActivePlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品EE取消计划", tags = {"操作历史" },  notes = "根据产品EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eecancelplan")
    public ResponseEntity<HistoryDTO> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeCancelPlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品EE关闭计划", tags = {"操作历史" },  notes = "根据产品EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eecloseplan")
    public ResponseEntity<HistoryDTO> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeClosePlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品EE完成计划", tags = {"操作历史" },  notes = "根据产品EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eefinishplan")
    public ResponseEntity<HistoryDTO> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeFinishPlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品EE暂停计划", tags = {"操作历史" },  notes = "根据产品EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eepauseplan")
    public ResponseEntity<HistoryDTO> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eePausePlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品继续计划", tags = {"操作历史" },  notes = "根据产品继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eerestartplan")
    public ResponseEntity<HistoryDTO> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeRestartPlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品EE开始计划", tags = {"操作历史" },  notes = "根据产品EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/eestartplan")
    public ResponseEntity<HistoryDTO> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.eeStartPlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取上一个计划的名称", tags = {"操作历史" },  notes = "根据产品获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/getoldplanname")
    public ResponseEntity<HistoryDTO> getOldPlanNameByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getOldPlanName(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板", tags = {"操作历史" },  notes = "根据产品导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/importplantemplet")
    public ResponseEntity<HistoryDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.importPlanTemplet(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"操作历史" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkbug")
    public ResponseEntity<HistoryDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"操作历史" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkstory")
    public ResponseEntity<HistoryDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品关联任务", tags = {"操作历史" },  notes = "根据产品关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linktask")
    public ResponseEntity<HistoryDTO> linkTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkTask(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品移动端产品计划计数器", tags = {"操作历史" },  notes = "根据产品移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}/mobproductplancounter")
    public ResponseEntity<HistoryDTO> mobProductPlanCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobProductPlanCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品解除关联Bug", tags = {"操作历史" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkbug")
    public ResponseEntity<HistoryDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品解除关联需求", tags = {"操作历史" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkstory")
    public ResponseEntity<HistoryDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子计划", tags = {"操作历史" } ,notes = "根据产品获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchchildplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"操作历史" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurproductplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurProductPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"操作历史" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurproductplanstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurProductPlanStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取默认查询", tags = {"操作历史" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefaultparent")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultParentByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefaultParent(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"操作历史" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchplancodelist")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchPlanCodeList(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划任务（项目管理-项目计划）", tags = {"操作历史" } ,notes = "根据产品获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchplantasks")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryPlanTasksByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchPlanTasks(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"操作历史" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchproductquery")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductQuery(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目立项", tags = {"操作历史" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojectapp")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectApp(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"操作历史" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojectplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取跟计划", tags = {"操作历史" } ,notes = "根据产品获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrootplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务计划", tags = {"操作历史" } ,notes = "根据产品获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtaskplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTaskPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTaskPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品产品计划获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立操作历史", tags = {"操作历史" },  notes = "根据产品产品计划建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取操作历史", tags = {"操作历史" },  notes = "根据产品产品计划获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取MobType", tags = {"操作历史" } ,notes = "根据产品产品计划获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取Type", tags = {"操作历史" } ,notes = "根据产品产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划更新操作历史", tags = {"操作历史" },  notes = "根据产品产品计划更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品产品计划获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量建立操作历史", tags = {"操作历史" },  notes = "根据产品批量建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
            
        }
        historyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"操作历史" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品全部推送", tags = {"操作历史" },  notes = "根据产品全部推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/allpush")
    public ResponseEntity<HistoryDTO> allPushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.allPush(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"操作历史" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/assignto")
    public ResponseEntity<HistoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.assignTo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量指派", tags = {"操作历史" },  notes = "根据产品批量指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchassignto")
    public ResponseEntity<HistoryDTO> batchAssignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchAssignTo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更平台/分支", tags = {"操作历史" },  notes = "根据产品批量变更平台/分支")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchchangebranch")
    public ResponseEntity<HistoryDTO> batchChangeBranchByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchChangeBranch(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更模块", tags = {"操作历史" },  notes = "根据产品批量变更模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchchangemodule")
    public ResponseEntity<HistoryDTO> batchChangeModuleByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchChangeModule(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量关联计划", tags = {"操作历史" },  notes = "根据产品批量关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchchangeplan")
    public ResponseEntity<HistoryDTO> batchChangePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchChangePlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更阶段", tags = {"操作历史" },  notes = "根据产品批量变更阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchchangestage")
    public ResponseEntity<HistoryDTO> batchChangeStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchChangeStage(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量关闭", tags = {"操作历史" },  notes = "根据产品批量关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchclose")
    public ResponseEntity<HistoryDTO> batchCloseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchClose(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量评审", tags = {"操作历史" },  notes = "根据产品批量评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchreview")
    public ResponseEntity<HistoryDTO> batchReviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchReview(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品计划批量解除关联需求", tags = {"操作历史" },  notes = "根据产品计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/batchunlinkstory")
    public ResponseEntity<HistoryDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.batchUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品bug转需求", tags = {"操作历史" },  notes = "根据产品bug转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/bugtostory")
    public ResponseEntity<HistoryDTO> bugToStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.bugToStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本批量解除关联需求", tags = {"操作历史" },  notes = "根据产品版本批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildbatchunlinkstory")
    public ResponseEntity<HistoryDTO> buildBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildBatchUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"操作历史" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildlinkstory")
    public ResponseEntity<HistoryDTO> buildLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildLinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"操作历史" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildunlinkstory")
    public ResponseEntity<HistoryDTO> buildUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"操作历史" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/buildunlinkstorys")
    public ResponseEntity<HistoryDTO> buildUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.buildUnlinkStorys(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CHANGE')")
    @ApiOperation(value = "根据产品变更", tags = {"操作历史" },  notes = "根据产品变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/change")
    public ResponseEntity<HistoryDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.change(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"操作历史" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/close")
    public ResponseEntity<HistoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.close(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品生成任务", tags = {"操作历史" },  notes = "根据产品生成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/createtasks")
    public ResponseEntity<HistoryDTO> createTasksByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.createTasks(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"操作历史" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/getstoryspec")
    public ResponseEntity<HistoryDTO> getStorySpecByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getStorySpec(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"操作历史" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}/getstoryspecs")
    public ResponseEntity<HistoryDTO> getStorySpecsByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getStorySpecs(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目关联需求-按计划关联", tags = {"操作历史" },  notes = "根据产品项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/importplanstories")
    public ResponseEntity<HistoryDTO> importPlanStoriesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.importPlanStories(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品计划关联需求", tags = {"操作历史" },  notes = "根据产品计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/linkstory")
    public ResponseEntity<HistoryDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目批量解除关联需求", tags = {"操作历史" },  notes = "根据产品项目批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/projectbatchunlinkstory")
    public ResponseEntity<HistoryDTO> projectBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.projectBatchUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"操作历史" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/projectlinkstory")
    public ResponseEntity<HistoryDTO> projectLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.projectLinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"操作历史" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/projectunlinkstory")
    public ResponseEntity<HistoryDTO> projectUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.projectUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"操作历史" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/projectunlinkstorys")
    public ResponseEntity<HistoryDTO> projectUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.projectUnlinkStorys(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品推送", tags = {"操作历史" },  notes = "根据产品推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/push")
    public ResponseEntity<HistoryDTO> pushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.push(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发布批量解除关联需求", tags = {"操作历史" },  notes = "根据产品发布批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releasebatchunlinkstory")
    public ResponseEntity<HistoryDTO> releaseBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseBatchUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品发布关联需求", tags = {"操作历史" },  notes = "根据产品发布关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaselinkstory")
    public ResponseEntity<HistoryDTO> releaseLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseLinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品发布解除关联需求", tags = {"操作历史" },  notes = "根据产品发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/releaseunlinkstory")
    public ResponseEntity<HistoryDTO> releaseUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.releaseUnlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品重置由谁评审", tags = {"操作历史" },  notes = "根据产品重置由谁评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/resetreviewedby")
    public ResponseEntity<HistoryDTO> resetReviewedByByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.resetReviewedBy(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'REVIEW')")
    @ApiOperation(value = "根据产品评审", tags = {"操作历史" },  notes = "根据产品评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/review")
    public ResponseEntity<HistoryDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.review(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存操作历史", tags = {"操作历史" },  notes = "根据产品批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
             
        }
        historyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品行为", tags = {"操作历史" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/sendmessage")
    public ResponseEntity<HistoryDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMessage(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"操作历史" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/sendmsgpreprocess")
    public ResponseEntity<HistoryDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMsgPreProcess(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品设置需求阶段", tags = {"操作历史" },  notes = "根据产品设置需求阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/setstage")
    public ResponseEntity<HistoryDTO> setStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.setStage(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITES')")
    @ApiOperation(value = "根据产品需求收藏", tags = {"操作历史" },  notes = "根据产品需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/storyfavorites")
    public ResponseEntity<HistoryDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.storyFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"操作历史" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/storynfavorites")
    public ResponseEntity<HistoryDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.storyNFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品同步Ibz平台实体", tags = {"操作历史" },  notes = "根据产品同步Ibz平台实体")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/syncfromibiz")
    public ResponseEntity<HistoryDTO> syncFromIbizByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.syncFromIbiz(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品计划解除关联需求", tags = {"操作历史" },  notes = "根据产品计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/unlinkstory")
    public ResponseEntity<HistoryDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求", tags = {"操作历史" } ,notes = "根据产品获取指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchassignedtomystory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求（日历）", tags = {"操作历史" } ,notes = "根据产品获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchassignedtomystorycalendar")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyStoryCalendarByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyStoryCalendar(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取Bug相关需求", tags = {"操作历史" } ,notes = "根据产品获取Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbugstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联已完成的需求（选择数据源）", tags = {"操作历史" } ,notes = "根据产品获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildLinkCompletedStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildLinkCompletedStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的需求（产品内）", tags = {"操作历史" } ,notes = "根据产品获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildlinkablestories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildLinkableStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取版本相关需求", tags = {"操作历史" } ,notes = "根据产品获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbuildstories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBuildStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBuildStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"操作历史" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbymodule")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByModule(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取相关用例需求", tags = {"操作历史" } ,notes = "根据产品获取相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcasestory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCaseStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCaseStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子需求（更多）", tags = {"操作历史" } ,notes = "根据产品获取子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchchildmore")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildMoreByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildMore(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"操作历史" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchesbulk")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchESBulk(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品需求", tags = {"操作历史" } ,notes = "根据产品获取获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchgetproductstories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryGetProductStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchGetProductStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我代理的需求", tags = {"操作历史" } ,notes = "根据产品获取我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyagentstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyAgentStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyAgentStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所创建需求数和对应的优先级及状态", tags = {"操作历史" } ,notes = "根据产品获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmycuropenedstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCurOpenedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCurOpenedStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"操作历史" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyfavorites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavorites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划关联需求(去除已关联)", tags = {"操作历史" } ,notes = "根据产品获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchnotcurplanlinkstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotCurPlanLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotCurPlanLinkStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"操作历史" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchparentdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryParentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchParentDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"操作历史" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchparentdefaultq")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryParentDefaultQByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchParentDefaultQ(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目关联需求", tags = {"操作历史" } ,notes = "根据产品获取项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojectlinkstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectLinkStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目相关需求", tags = {"操作历史" } ,notes = "根据产品获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchprojectstories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的完成的需求", tags = {"操作历史" } ,notes = "根据产品获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleaselinkablestories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseLinkableStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"操作历史" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreleasestories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReleaseStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReleaseStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"操作历史" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchreportstories")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryReportStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchReportStories(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"操作历史" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchstorychild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryStoryChildByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchStoryChild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"操作历史" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchstoryrelated")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryStoryRelatedByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchStoryRelated(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取需求细分", tags = {"操作历史" } ,notes = "根据产品获取需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchsubstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistorySubStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchSubStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务相关需求", tags = {"操作历史" } ,notes = "根据产品获取任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchtaskrelatedstory")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTaskRelatedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTaskRelatedStory(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取默认（全部数据）", tags = {"操作历史" } ,notes = "根据产品获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchview")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryViewByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchView(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品需求获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求建立操作历史", tags = {"操作历史" },  notes = "根据产品需求建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求获取操作历史", tags = {"操作历史" },  notes = "根据产品需求获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取MobType", tags = {"操作历史" } ,notes = "根据产品需求获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取Type", tags = {"操作历史" } ,notes = "根据产品需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求更新操作历史", tags = {"操作历史" },  notes = "根据产品需求更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品需求获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立操作历史", tags = {"操作历史" },  notes = "根据文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新操作历史", tags = {"操作历史" },  notes = "根据文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库删除操作历史", tags = {"操作历史" },  notes = "根据文档库删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取操作历史", tags = {"操作历史" },  notes = "根据文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库根据版本更新正文信息", tags = {"操作历史" },  notes = "根据文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/histories/{history_id}/byversionupdatecontext")
    public ResponseEntity<HistoryDTO> byVersionUpdateContextByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.byVersionUpdateContext(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库检查操作历史", tags = {"操作历史" },  notes = "根据文档库检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库收藏", tags = {"操作历史" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据文档库行为", tags = {"操作历史" },  notes = "根据文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/histories/{history_id}/getdocstatus")
    public ResponseEntity<HistoryDTO> getDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getDocStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅收藏文档", tags = {"操作历史" },  notes = "根据文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/{history_id}/onlycollectdoc")
    public ResponseEntity<HistoryDTO> onlyCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅取消收藏文档", tags = {"操作历史" },  notes = "根据文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/{history_id}/onlyuncollectdoc")
    public ResponseEntity<HistoryDTO> onlyUnCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyUnCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据文档库保存操作历史", tags = {"操作历史" },  notes = "根据文档库保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"操作历史" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档（子库）", tags = {"操作历史" } ,notes = "根据文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchchilddoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdoclibanddoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibAndDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库分类文档", tags = {"操作历史" } ,notes = "根据文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdocmoduledoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocModuleDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档统计", tags = {"操作历史" } ,notes = "根据文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchdocstatus")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocStatus(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文件夹文档（子目录）", tags = {"操作历史" } ,notes = "根据文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchmoduledocchild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleDocChild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchmyfavourite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavouritesOnlyDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取子目录文档", tags = {"操作历史" } ,notes = "根据文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchnotrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取根目录文档", tags = {"操作历史" } ,notes = "根据文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/histories/fetchrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取查询用户使用年", tags = {"操作历史" } ,notes = "根据文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档建立操作历史", tags = {"操作历史" },  notes = "根据文档库文档建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取ProductTrends", tags = {"操作历史" } ,notes = "根据文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档获取操作历史", tags = {"操作历史" },  notes = "根据文档库文档获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取MobType", tags = {"操作历史" } ,notes = "根据文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取Type", tags = {"操作历史" } ,notes = "根据文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库文档更新操作历史", tags = {"操作历史" },  notes = "根据文档库文档更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档库文档获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'COLLECT')")
    @ApiOperation(value = "根据项目收藏", tags = {"操作历史" },  notes = "根据项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"操作历史" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"操作历史" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbycustom")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByCustom(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"操作历史" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"操作历史" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproductnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProductNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"操作历史" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproject")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProject(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"操作历史" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyprojectnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProjectNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"操作历史" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchcurdoclib")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurDocLib(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"操作历史" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyfavourites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"操作历史" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchrootmodulemulu")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootModuleMuLu(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立操作历史", tags = {"操作历史" },  notes = "根据项目文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取操作历史", tags = {"操作历史" },  notes = "根据项目文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取MobType", tags = {"操作历史" } ,notes = "根据项目文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取Type", tags = {"操作历史" } ,notes = "根据项目文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新操作历史", tags = {"操作历史" },  notes = "根据项目文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立操作历史", tags = {"操作历史" },  notes = "根据项目批量建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
            
        }
        historyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"操作历史" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"操作历史" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/assignto")
    public ResponseEntity<HistoryDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.assignTo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CANCEL')")
    @ApiOperation(value = "根据项目取消", tags = {"操作历史" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/cancel")
    public ResponseEntity<HistoryDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.cancel(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"操作历史" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/close")
    public ResponseEntity<HistoryDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.close(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目计算开始时间和完成时间", tags = {"操作历史" },  notes = "根据项目计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/computebeginandend")
    public ResponseEntity<HistoryDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.computeBeginAndEnd(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目更新父任务时间", tags = {"操作历史" },  notes = "根据项目更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/computehours4multiple")
    public ResponseEntity<HistoryDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.computeHours4Multiple(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目更新工作时间", tags = {"操作历史" },  notes = "根据项目更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/computeworkinghours")
    public ResponseEntity<HistoryDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.computeWorkingHours(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目需求变更确认", tags = {"操作历史" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/confirmstorychange")
    public ResponseEntity<HistoryDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.confirmStoryChange(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"操作历史" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/createbycycle")
    public ResponseEntity<HistoryDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.createByCycle(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"操作历史" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/createcycletasks")
    public ResponseEntity<HistoryDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.createCycleTasks(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目删除任务", tags = {"操作历史" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/delete")
    public ResponseEntity<HistoryDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.delete(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目删除工时", tags = {"操作历史" },  notes = "根据项目删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/deleteestimate")
    public ResponseEntity<HistoryDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.deleteEstimate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目编辑工时", tags = {"操作历史" },  notes = "根据项目编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/editestimate")
    public ResponseEntity<HistoryDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.editEstimate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'FINISH')")
    @ApiOperation(value = "根据项目完成", tags = {"操作历史" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/finish")
    public ResponseEntity<HistoryDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.finish(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)", tags = {"操作历史" },  notes = "根据项目获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getnextteamuser")
    public ResponseEntity<HistoryDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getNextTeamUser(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）", tags = {"操作历史" },  notes = "根据项目获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getteamuserleftactivity")
    public ResponseEntity<HistoryDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTeamUserLeftActivity(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）", tags = {"操作历史" },  notes = "根据项目获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getteamuserleftstart")
    public ResponseEntity<HistoryDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTeamUserLeftStart(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员", tags = {"操作历史" },  notes = "根据项目获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getusernames")
    public ResponseEntity<HistoryDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getUsernames(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目关联计划", tags = {"操作历史" },  notes = "根据项目关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/linkplan")
    public ResponseEntity<HistoryDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkPlan(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目其他更新", tags = {"操作历史" },  notes = "根据项目其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/otherupdate")
    public ResponseEntity<HistoryDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.otherUpdate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'PAUSE')")
    @ApiOperation(value = "根据项目暂停", tags = {"操作历史" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/pause")
    public ResponseEntity<HistoryDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.pause(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RECORDESTIMATE')")
    @ApiOperation(value = "根据项目工时录入", tags = {"操作历史" },  notes = "根据项目工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/recordestimate")
    public ResponseEntity<HistoryDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.recordEstimate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中", tags = {"操作历史" },  notes = "根据项目继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<HistoryDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.recordTimZeroLeftAfterContinue(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目预计剩余为0进行中", tags = {"操作历史" },  notes = "根据项目预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/recordtimatezeroleft")
    public ResponseEntity<HistoryDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.recordTimateZeroLeft(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中", tags = {"操作历史" },  notes = "根据项目开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<HistoryDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.recordTimateZeroLeftAfterStart(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RESTART')")
    @ApiOperation(value = "根据项目继续", tags = {"操作历史" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/restart")
    public ResponseEntity<HistoryDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.restart(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量保存操作历史", tags = {"操作历史" },  notes = "根据项目批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
             
        }
        historyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目行为", tags = {"操作历史" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/sendmessage")
    public ResponseEntity<HistoryDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMessage(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"操作历史" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/sendmsgpreprocess")
    public ResponseEntity<HistoryDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.sendMsgPreProcess(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "根据项目开始", tags = {"操作历史" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/start")
    public ResponseEntity<HistoryDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.start(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"操作历史" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/taskfavorites")
    public ResponseEntity<HistoryDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.taskFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目检查多人任务操作权限", tags = {"操作历史" },  notes = "根据项目检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/taskforward")
    public ResponseEntity<HistoryDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.taskForward(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"操作历史" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/tasknfavorites")
    public ResponseEntity<HistoryDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.taskNFavorites(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务状态", tags = {"操作历史" },  notes = "根据项目更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/updateparentstatus")
    public ResponseEntity<HistoryDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.updateParentStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务计划状态", tags = {"操作历史" },  notes = "根据项目更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/updaterelatedplanstatus")
    public ResponseEntity<HistoryDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.updateRelatedPlanStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"操作历史" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/updatestoryversion")
    public ResponseEntity<HistoryDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.updateStoryVersion(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"操作历史" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchassignedtomytask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"操作历史" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchassignedtomytaskpc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchAssignedToMyTaskPc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"操作历史" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbugtask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"操作历史" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbymodule")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByModule(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"操作历史" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchchilddefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"操作历史" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchchilddefaultmore")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildDefaultMore(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"操作历史" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchchildtask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"操作历史" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchchildtasktree")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildTaskTree(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"操作历史" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchcurfinishtask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurFinishTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"操作历史" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchcurprojecttaskquery")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurProjectTaskQuery(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"操作历史" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefaultrow")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefaultRow(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"操作历史" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchesbulk")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchESBulk(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"操作历史" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyagenttask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyAgentTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"操作历史" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyalltask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyAllTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"操作历史" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmycompletetask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCompleteTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"操作历史" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCompleteTaskMobDaily(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"操作历史" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCompleteTaskMobMonthly(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"操作历史" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCompleteTaskMonthlyZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"操作历史" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmycompletetaskzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyCompleteTaskZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"操作历史" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyfavorites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavorites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"操作历史" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyPlansTaskMobMonthly(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"操作历史" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmytomorrowplantask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTomorrowPlanTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"操作历史" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"操作历史" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNextWeekCompleteTaskMobZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"操作历史" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNextWeekCompleteTaskZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"操作历史" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchnextweekplancompletetask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNextWeekPlanCompleteTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"操作历史" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchplantask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchPlanTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"操作历史" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchprojectapptask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectAppTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"操作历史" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchprojecttask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"操作历史" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchroottask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"操作历史" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtasklinkplan")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTaskLinkPlan(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"操作历史" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchThisMonthCompleteTaskChoice(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"操作历史" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchthisweekcompletetask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchThisWeekCompleteTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"操作历史" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchThisWeekCompleteTaskChoice(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"操作历史" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchThisWeekCompleteTaskMobZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"操作历史" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchThisWeekCompleteTaskZS(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"操作历史" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtodolisttask")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTodoListTask(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"操作历史" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchHistoryTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<Map> domains = historyService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"操作历史" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchHistoryTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<Map> domains = historyService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目任务获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立操作历史", tags = {"操作历史" },  notes = "根据项目任务建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取操作历史", tags = {"操作历史" },  notes = "根据项目任务获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取MobType", tags = {"操作历史" } ,notes = "根据项目任务获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取Type", tags = {"操作历史" } ,notes = "根据项目任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新操作历史", tags = {"操作历史" },  notes = "根据项目任务更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目任务获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息", tags = {"操作历史" },  notes = "根据项目根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotesttask")
    public ResponseEntity<HistoryDTO> getInfoTestTaskByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTask(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据起始时间获取概况信息", tags = {"操作历史" },  notes = "根据项目根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotaskovbytime")
    public ResponseEntity<HistoryDTO> getInfoTaskOvByTimeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTaskOvByTime(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告概况信息（项目报告）", tags = {"操作历史" },  notes = "根据项目根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotesttaskovproject")
    public ResponseEntity<HistoryDTO> getInfoTestTaskOvProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskOvProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）", tags = {"操作历史" },  notes = "根据项目根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotesttaskproject")
    public ResponseEntity<HistoryDTO> getInfoTestTaskProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（单测试）", tags = {"操作历史" },  notes = "根据项目根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotesttaskr")
    public ResponseEntity<HistoryDTO> getInfoTestTaskRByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskR(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（测试）", tags = {"操作历史" },  notes = "根据项目根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/getinfotesttasks")
    public ResponseEntity<HistoryDTO> getInfoTestTaskSByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getInfoTestTaskS(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息", tags = {"操作历史" },  notes = "根据项目根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/gettestreportbasicinfo")
    public ResponseEntity<HistoryDTO> getTestReportBasicInfoByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTestReportBasicInfo(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息（项目报告）", tags = {"操作历史" },  notes = "根据项目根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/gettestreportproject")
    public ResponseEntity<HistoryDTO> getTestReportProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getTestReportProject(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告建立操作历史", tags = {"操作历史" },  notes = "根据项目测试报告建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试报告获取操作历史", tags = {"操作历史" },  notes = "根据项目测试报告获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取MobType", tags = {"操作历史" } ,notes = "根据项目测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取Type", tags = {"操作历史" } ,notes = "根据项目测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试报告更新操作历史", tags = {"操作历史" },  notes = "根据项目测试报告更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目测试报告获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目激活", tags = {"操作历史" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/activate")
    public ResponseEntity<HistoryDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.activate(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目阻塞", tags = {"操作历史" },  notes = "根据项目阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/block")
    public ResponseEntity<HistoryDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.block(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目关闭", tags = {"操作历史" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/close")
    public ResponseEntity<HistoryDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.close(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"操作历史" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/linkcase")
    public ResponseEntity<HistoryDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目移动端测试版本计数器", tags = {"操作历史" },  notes = "根据项目移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/mobtesttaskcounter")
    public ResponseEntity<HistoryDTO> mobTestTaskCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobTestTaskCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目开始", tags = {"操作历史" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/start")
    public ResponseEntity<HistoryDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.start(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"操作历史" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/unlinkcase")
    public ResponseEntity<HistoryDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkCase(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的测试单", tags = {"操作历史" } ,notes = "根据项目获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmytesttaskpc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTestTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTestTaskPc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立操作历史", tags = {"操作历史" },  notes = "根据项目测试版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取操作历史", tags = {"操作历史" },  notes = "根据项目测试版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取MobType", tags = {"操作历史" } ,notes = "根据项目测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取Type", tags = {"操作历史" } ,notes = "根据项目测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新操作历史", tags = {"操作历史" },  notes = "根据项目测试版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目测试版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目关联Bug", tags = {"操作历史" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/linkbug")
    public ResponseEntity<HistoryDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目关联需求", tags = {"操作历史" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/linkstory")
    public ResponseEntity<HistoryDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.linkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目移动端项目版本计数器", tags = {"操作历史" },  notes = "根据项目移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}/mobprojectbuildcounter")
    public ResponseEntity<HistoryDTO> mobProjectBuildCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.mobProjectBuildCounter(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目一键发布", tags = {"操作历史" },  notes = "根据项目一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/oneclickrelease")
    public ResponseEntity<HistoryDTO> oneClickReleaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.oneClickRelease(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目移除Bug关联", tags = {"操作历史" },  notes = "根据项目移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/unlinkbug")
    public ResponseEntity<HistoryDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkBug(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目移除关联需求", tags = {"操作历史" },  notes = "根据项目移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/unlinkstory")
    public ResponseEntity<HistoryDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unlinkStory(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取Bug产品版本", tags = {"操作历史" } ,notes = "根据项目获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbugproductbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugProductBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugProductBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug产品或者项目版本", tags = {"操作历史" } ,notes = "根据项目获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbugproductorprojectbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryBugProductOrProjectBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchBugProductOrProjectBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"操作历史" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchcurproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurProductByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取测试版本", tags = {"操作历史" } ,notes = "根据项目获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtestbuild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTestBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTestBuild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取测试轮次", tags = {"操作历史" } ,notes = "根据项目获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchtestrounds")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTestRoundsByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchTestRounds(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取更新日志", tags = {"操作历史" } ,notes = "根据项目获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchupdatelog")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryUpdateLogByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchUpdateLog(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本建立操作历史", tags = {"操作历史" },  notes = "根据项目版本建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本获取操作历史", tags = {"操作历史" },  notes = "根据项目版本获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取MobType", tags = {"操作历史" } ,notes = "根据项目版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取Type", tags = {"操作历史" } ,notes = "根据项目版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本更新操作历史", tags = {"操作历史" },  notes = "根据项目版本更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目版本获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立操作历史", tags = {"操作历史" },  notes = "根据产品建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories")
    public ResponseEntity<HistoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新操作历史", tags = {"操作历史" },  notes = "根据产品更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除操作历史", tags = {"操作历史" },  notes = "根据产品删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取操作历史", tags = {"操作历史" },  notes = "根据产品获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查操作历史", tags = {"操作历史" },  notes = "根据产品检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'COLLECT')")
    @ApiOperation(value = "根据产品收藏", tags = {"操作历史" },  notes = "根据产品收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品保存操作历史", tags = {"操作历史" },  notes = "根据产品保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"操作历史" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"操作历史" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbycustom")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByCustom(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"操作历史" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"操作历史" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproductnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProductNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"操作历史" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyproject")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProject(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"操作历史" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchbyprojectnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProjectNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"操作历史" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchcurdoclib")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurDocLib(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"操作历史" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchmyfavourites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"操作历史" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/histories/fetchrootmodulemulu")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootModuleMuLu(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立操作历史", tags = {"操作历史" },  notes = "根据产品文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新操作历史", tags = {"操作历史" },  notes = "根据产品文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库删除操作历史", tags = {"操作历史" },  notes = "根据产品文档库删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取操作历史", tags = {"操作历史" },  notes = "根据产品文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库根据版本更新正文信息", tags = {"操作历史" },  notes = "根据产品文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/byversionupdatecontext")
    public ResponseEntity<HistoryDTO> byVersionUpdateContextByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.byVersionUpdateContext(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库检查操作历史", tags = {"操作历史" },  notes = "根据产品文档库检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"操作历史" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品文档库行为", tags = {"操作历史" },  notes = "根据产品文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/getdocstatus")
    public ResponseEntity<HistoryDTO> getDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getDocStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅收藏文档", tags = {"操作历史" },  notes = "根据产品文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/onlycollectdoc")
    public ResponseEntity<HistoryDTO> onlyCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅取消收藏文档", tags = {"操作历史" },  notes = "根据产品文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/onlyuncollectdoc")
    public ResponseEntity<HistoryDTO> onlyUnCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyUnCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据产品文档库保存操作历史", tags = {"操作历史" },  notes = "根据产品文档库保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"操作历史" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档（子库）", tags = {"操作历史" } ,notes = "根据产品文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchchilddoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdoclibanddoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibAndDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类文档", tags = {"操作历史" } ,notes = "根据产品文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdocmoduledoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocModuleDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档统计", tags = {"操作历史" } ,notes = "根据产品文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchdocstatus")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocStatus(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文件夹文档（子目录）", tags = {"操作历史" } ,notes = "根据产品文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchmoduledocchild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleDocChild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchmyfavourite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavouritesOnlyDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取子目录文档", tags = {"操作历史" } ,notes = "根据产品文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchnotrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取根目录文档", tags = {"操作历史" } ,notes = "根据产品文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/histories/fetchrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取查询用户使用年", tags = {"操作历史" } ,notes = "根据产品文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档建立操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取ProductTrends", tags = {"操作历史" } ,notes = "根据产品文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据产品文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档获取操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取MobType", tags = {"操作历史" } ,notes = "根据产品文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据产品文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取Type", tags = {"操作历史" } ,notes = "根据产品文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库文档更新操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品文档库文档获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立操作历史", tags = {"操作历史" },  notes = "根据项目建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories")
    public ResponseEntity<HistoryDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新操作历史", tags = {"操作历史" },  notes = "根据项目更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除操作历史", tags = {"操作历史" },  notes = "根据项目删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取操作历史", tags = {"操作历史" },  notes = "根据项目获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProject(@PathVariable("project_id") Long project_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查操作历史", tags = {"操作历史" },  notes = "根据项目检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'COLLECT')")
    @ApiOperation(value = "根据项目收藏", tags = {"操作历史" },  notes = "根据项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目保存操作历史", tags = {"操作历史" },  notes = "根据项目保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"操作历史" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"操作历史" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbycustom")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByCustom(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"操作历史" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproduct")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProduct(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"操作历史" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproductnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProductNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"操作历史" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyproject")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProject(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"操作历史" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchbyprojectnotfiles")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchByProjectNotFiles(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"操作历史" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchcurdoclib")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchCurDocLib(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"操作历史" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchmyfavourites")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourites(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"操作历史" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/histories/fetchrootmodulemulu")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootModuleMuLu(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立操作历史", tags = {"操作历史" },  notes = "根据项目文档库建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新操作历史", tags = {"操作历史" },  notes = "根据项目文档库更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库删除操作历史", tags = {"操作历史" },  notes = "根据项目文档库删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取操作历史", tags = {"操作历史" },  notes = "根据项目文档库获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目文档库获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库根据版本更新正文信息", tags = {"操作历史" },  notes = "根据项目文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/byversionupdatecontext")
    public ResponseEntity<HistoryDTO> byVersionUpdateContextByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.byVersionUpdateContext(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库检查操作历史", tags = {"操作历史" },  notes = "根据项目文档库检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"操作历史" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/collect")
    public ResponseEntity<HistoryDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.collect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目文档库行为", tags = {"操作历史" },  notes = "根据项目文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/getdocstatus")
    public ResponseEntity<HistoryDTO> getDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.getDocStatus(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅收藏文档", tags = {"操作历史" },  notes = "根据项目文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/onlycollectdoc")
    public ResponseEntity<HistoryDTO> onlyCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅取消收藏文档", tags = {"操作历史" },  notes = "根据项目文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/onlyuncollectdoc")
    public ResponseEntity<HistoryDTO> onlyUnCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.onlyUnCollectDoc(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据项目文档库保存操作历史", tags = {"操作历史" },  notes = "根据项目文档库保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"操作历史" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/histories/{history_id}/uncollect")
    public ResponseEntity<HistoryDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
        domain = historyService.unCollect(domain) ;
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档（子库）", tags = {"操作历史" } ,notes = "根据项目文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchchilddoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchChildDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdoclibanddoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibAndDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"操作历史" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdoclibdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocLibDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库分类文档", tags = {"操作历史" } ,notes = "根据项目文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdocmoduledoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocModuleDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档统计", tags = {"操作历史" } ,notes = "根据项目文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchdocstatus")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDocStatus(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文件夹文档（子目录）", tags = {"操作历史" } ,notes = "根据项目文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchmoduledocchild")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchModuleDocChild(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchmyfavourite")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavourite(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"操作历史" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyFavouritesOnlyDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取子目录文档", tags = {"操作历史" } ,notes = "根据项目文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchnotrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchNotRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取根目录文档", tags = {"操作历史" } ,notes = "根据项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/histories/fetchrootdoc")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchRootDoc(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取查询用户使用年", tags = {"操作历史" } ,notes = "根据项目文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchqueryuseryear")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryQueryUserYEARByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchQueryUserYEAR(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档建立操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取ProductTrends", tags = {"操作历史" } ,notes = "根据项目文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchproducttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProductTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProductTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(我的)", tags = {"操作历史" } ,notes = "根据项目文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmytrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMyTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMyTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档获取操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取MobType", tags = {"操作历史" } ,notes = "根据项目文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchmobtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryMobTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchMobType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(项目相关所有)", tags = {"操作历史" } ,notes = "根据项目文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchprojecttrends")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryProjectTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchProjectTrends(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取Type", tags = {"操作历史" } ,notes = "根据项目文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/fetchtype")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody HistorySearchContext context) {
        
        Page<History> domains = historyService.searchType(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库文档更新操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目文档库文档获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

