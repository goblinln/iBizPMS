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
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ActionRuntime;

@Slf4j
@Api(tags = {"系统日志" })
@RestController("WebApi-action")
@RequestMapping("")
public class ActionResource {

    @Autowired
    public IActionService actionService;

    @Autowired
    public ActionRuntime actionRuntime;

    @Autowired
    @Lazy
    public ActionMapping actionMapping;

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"系统日志" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchdefault(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取查询用户使用年", tags = {"系统日志" } ,notes = "获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchqueryuseryear(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建系统日志", tags = {"系统日志" },  notes = "新建系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions")
    @Transactional
    public ResponseEntity<ActionDTO> create(@Validated @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
		actionService.create(domain);
        if(!actionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ActionDTO dto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ProductTrends", tags = {"系统日志" } ,notes = "获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchproducttrends(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目动态(我的)", tags = {"系统日志" } ,notes = "获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchmytrends(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.test(#action_id,'READ')")
    @ApiOperation(value = "获取系统日志", tags = {"系统日志" },  notes = "获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/{action_id}")
    public ResponseEntity<ActionDTO> get(@PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(action_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取MobType", tags = {"系统日志" } ,notes = "获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchmobtype(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchprojecttrends(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Type", tags = {"系统日志" } ,notes = "获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchtype(@RequestBody ActionSearchContext context) {
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ActionRuntime.test(#action_id,'UPDATE')")
    @ApiOperation(value = "更新系统日志", tags = {"系统日志" },  notes = "更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/actions/{action_id}")
    @Transactional
    public ResponseEntity<ActionDTO> update(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
		Action domain  = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
		actionService.update(domain );
        if(!actionRuntime.test(action_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ActionDTO dto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(action_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取系统日志草稿", tags = {"系统日志" },  notes = "获取系统日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraft(ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/{action}")
    public ResponseEntity<ActionDTO> dynamicCall(@PathVariable("action_id") Long action_id , @PathVariable("action") String action , @RequestBody ActionDTO actiondto) {
        Action domain = actionService.dynamicCall(action_id, action, actionMapping.toDomain(actiondto));
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取DEFAULT", tags = {"系统日志" } ,notes = "根据Bug获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取查询用户使用年", tags = {"系统日志" } ,notes = "根据Bug获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug建立系统日志", tags = {"系统日志" },  notes = "根据Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据Bug获取系统日志", tags = {"系统日志" },  notes = "根据Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取MobType", tags = {"系统日志" } ,notes = "根据Bug获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取Type", tags = {"系统日志" } ,notes = "根据Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByBug(@PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据Bug更新系统日志", tags = {"系统日志" },  notes = "根据Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByBug(@PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取MobType", tags = {"系统日志" } ,notes = "根据产品获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Type", tags = {"系统日志" } ,notes = "根据产品获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品计划获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划建立系统日志", tags = {"系统日志" },  notes = "根据产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取MobType", tags = {"系统日志" } ,notes = "根据产品计划获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划获取Type", tags = {"系统日志" } ,notes = "根据产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划更新系统日志", tags = {"系统日志" },  notes = "根据产品计划更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取DEFAULT", tags = {"系统日志" } ,notes = "根据待办获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取查询用户使用年", tags = {"系统日志" } ,notes = "根据待办获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办建立系统日志", tags = {"系统日志" },  notes = "根据待办建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions")
    public ResponseEntity<ActionDTO> createByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取ProductTrends", tags = {"系统日志" } ,notes = "根据待办获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据待办获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
    @ApiOperation(value = "根据待办获取系统日志", tags = {"系统日志" },  notes = "根据待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取MobType", tags = {"系统日志" } ,notes = "根据待办获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据待办获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办获取Type", tags = {"系统日志" } ,notes = "根据待办获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TodoRuntime.test(#todo_id,'UPDATE')")
    @ApiOperation(value = "根据待办更新系统日志", tags = {"系统日志" },  notes = "根据待办更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办获取系统日志草稿", tags = {"系统日志" },  notes = "根据待办获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTodo(@PathVariable("todo_id") Long todo_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取DEFAULT", tags = {"系统日志" } ,notes = "根据任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取查询用户使用年", tags = {"系统日志" } ,notes = "根据任务获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务建立系统日志", tags = {"系统日志" },  notes = "根据任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取ProductTrends", tags = {"系统日志" } ,notes = "根据任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取系统日志", tags = {"系统日志" },  notes = "根据任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取MobType", tags = {"系统日志" } ,notes = "根据任务获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取Type", tags = {"系统日志" } ,notes = "根据任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTask(@PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新系统日志", tags = {"系统日志" },  notes = "根据任务更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTask(@PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取DEFAULT", tags = {"系统日志" } ,notes = "根据发布获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取查询用户使用年", tags = {"系统日志" } ,notes = "根据发布获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布建立系统日志", tags = {"系统日志" },  notes = "根据发布建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions")
    public ResponseEntity<ActionDTO> createByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取ProductTrends", tags = {"系统日志" } ,notes = "根据发布获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据发布获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据发布获取系统日志", tags = {"系统日志" },  notes = "根据发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取MobType", tags = {"系统日志" } ,notes = "根据发布获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据发布获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布获取Type", tags = {"系统日志" } ,notes = "根据发布获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByRelease(@PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据发布更新系统日志", tags = {"系统日志" },  notes = "根据发布更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布获取系统日志草稿", tags = {"系统日志" },  notes = "根据发布获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByRelease(@PathVariable("release_id") Long release_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取DEFAULT", tags = {"系统日志" } ,notes = "根据周报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取查询用户使用年", tags = {"系统日志" } ,notes = "根据周报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报建立系统日志", tags = {"系统日志" },  notes = "根据周报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取ProductTrends", tags = {"系统日志" } ,notes = "根据周报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据周报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
    @ApiOperation(value = "根据周报获取系统日志", tags = {"系统日志" },  notes = "根据周报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取MobType", tags = {"系统日志" } ,notes = "根据周报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据周报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取Type", tags = {"系统日志" } ,notes = "根据周报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'UPDATE')")
    @ApiOperation(value = "根据周报更新系统日志", tags = {"系统日志" },  notes = "根据周报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报获取系统日志草稿", tags = {"系统日志" },  notes = "根据周报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取DEFAULT", tags = {"系统日志" } ,notes = "根据测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取查询用户使用年", tags = {"系统日志" } ,notes = "根据测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告建立系统日志", tags = {"系统日志" },  notes = "根据测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
    @ApiOperation(value = "根据测试报告获取系统日志", tags = {"系统日志" },  notes = "根据测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取MobType", tags = {"系统日志" } ,notes = "根据测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取Type", tags = {"系统日志" } ,notes = "根据测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'UPDATE')")
    @ApiOperation(value = "根据测试报告更新系统日志", tags = {"系统日志" },  notes = "根据测试报告更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestReport(@PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取查询用户使用年", tags = {"系统日志" } ,notes = "根据文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立系统日志", tags = {"系统日志" },  notes = "根据文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取系统日志", tags = {"系统日志" },  notes = "根据文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取MobType", tags = {"系统日志" } ,notes = "根据文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取Type", tags = {"系统日志" } ,notes = "根据文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新系统日志", tags = {"系统日志" },  notes = "根据文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取DEFAULT", tags = {"系统日志" } ,notes = "根据文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取查询用户使用年", tags = {"系统日志" } ,notes = "根据文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档建立系统日志", tags = {"系统日志" },  notes = "根据文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据文档获取系统日志", tags = {"系统日志" },  notes = "根据文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取MobType", tags = {"系统日志" } ,notes = "根据文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取Type", tags = {"系统日志" } ,notes = "根据文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@DocRuntime.test(#doc_id,'UPDATE')")
    @ApiOperation(value = "根据文档更新系统日志", tags = {"系统日志" },  notes = "根据文档更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取DEFAULT", tags = {"系统日志" } ,notes = "根据日报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取查询用户使用年", tags = {"系统日志" } ,notes = "根据日报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报建立系统日志", tags = {"系统日志" },  notes = "根据日报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取ProductTrends", tags = {"系统日志" } ,notes = "根据日报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据日报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
    @ApiOperation(value = "根据日报获取系统日志", tags = {"系统日志" },  notes = "根据日报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取MobType", tags = {"系统日志" } ,notes = "根据日报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据日报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报获取Type", tags = {"系统日志" } ,notes = "根据日报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'UPDATE')")
    @ApiOperation(value = "根据日报更新系统日志", tags = {"系统日志" },  notes = "根据日报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报获取系统日志草稿", tags = {"系统日志" },  notes = "根据日报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取DEFAULT", tags = {"系统日志" } ,notes = "根据月报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取查询用户使用年", tags = {"系统日志" } ,notes = "根据月报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报建立系统日志", tags = {"系统日志" },  notes = "根据月报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取ProductTrends", tags = {"系统日志" } ,notes = "根据月报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据月报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
    @ApiOperation(value = "根据月报获取系统日志", tags = {"系统日志" },  notes = "根据月报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取MobType", tags = {"系统日志" } ,notes = "根据月报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据月报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报获取Type", tags = {"系统日志" } ,notes = "根据月报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'UPDATE')")
    @ApiOperation(value = "根据月报更新系统日志", tags = {"系统日志" },  notes = "根据月报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报获取系统日志草稿", tags = {"系统日志" },  notes = "根据月报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取DEFAULT", tags = {"系统日志" } ,notes = "根据汇报获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取查询用户使用年", tags = {"系统日志" } ,notes = "根据汇报获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报建立系统日志", tags = {"系统日志" },  notes = "根据汇报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取ProductTrends", tags = {"系统日志" } ,notes = "根据汇报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据汇报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报获取系统日志", tags = {"系统日志" },  notes = "根据汇报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取MobType", tags = {"系统日志" } ,notes = "根据汇报获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据汇报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取Type", tags = {"系统日志" } ,notes = "根据汇报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报更新系统日志", tags = {"系统日志" },  notes = "根据汇报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报获取系统日志草稿", tags = {"系统日志" },  notes = "根据汇报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本建立系统日志", tags = {"系统日志" },  notes = "根据测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取系统日志", tags = {"系统日志" },  notes = "根据测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取MobType", tags = {"系统日志" } ,notes = "根据测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取Type", tags = {"系统日志" } ,notes = "根据测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新系统日志", tags = {"系统日志" },  notes = "根据测试版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取DEFAULT", tags = {"系统日志" } ,notes = "根据测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取查询用户使用年", tags = {"系统日志" } ,notes = "根据测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件建立系统日志", tags = {"系统日志" },  notes = "根据测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取MobType", tags = {"系统日志" } ,notes = "根据测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取Type", tags = {"系统日志" } ,notes = "根据测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件更新系统日志", tags = {"系统日志" },  notes = "根据测试套件更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"系统日志" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取查询用户使用年", tags = {"系统日志" } ,notes = "根据测试用例获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例建立系统日志", tags = {"系统日志" },  notes = "根据测试用例建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions")
    public ResponseEntity<ActionDTO> createByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试用例获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试用例获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取系统日志", tags = {"系统日志" },  notes = "根据测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取MobType", tags = {"系统日志" } ,notes = "根据测试用例获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试用例获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取Type", tags = {"系统日志" } ,notes = "根据测试用例获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByCase(@PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例更新系统日志", tags = {"系统日志" },  notes = "根据测试用例更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试用例获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByCase(@PathVariable("case_id") Long case_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本建立系统日志", tags = {"系统日志" },  notes = "根据版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本获取系统日志", tags = {"系统日志" },  notes = "根据版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取MobType", tags = {"系统日志" } ,notes = "根据版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取Type", tags = {"系统日志" } ,notes = "根据版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByBuild(@PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本更新系统日志", tags = {"系统日志" },  notes = "根据版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByBuild(@PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取DEFAULT", tags = {"系统日志" } ,notes = "根据用例库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取查询用户使用年", tags = {"系统日志" } ,notes = "根据用例库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库建立系统日志", tags = {"系统日志" },  notes = "根据用例库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取ProductTrends", tags = {"系统日志" } ,notes = "根据用例库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据用例库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
    @ApiOperation(value = "根据用例库获取系统日志", tags = {"系统日志" },  notes = "根据用例库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取MobType", tags = {"系统日志" } ,notes = "根据用例库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据用例库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库获取Type", tags = {"系统日志" } ,notes = "根据用例库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'UPDATE')")
    @ApiOperation(value = "根据用例库更新系统日志", tags = {"系统日志" },  notes = "根据用例库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库获取系统日志草稿", tags = {"系统日志" },  notes = "根据用例库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取DEFAULT", tags = {"系统日志" } ,notes = "根据需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取查询用户使用年", tags = {"系统日志" } ,notes = "根据需求获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求建立系统日志", tags = {"系统日志" },  notes = "根据需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求获取系统日志", tags = {"系统日志" },  notes = "根据需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取MobType", tags = {"系统日志" } ,notes = "根据需求获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求获取Type", tags = {"系统日志" } ,notes = "根据需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByStory(@PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求更新系统日志", tags = {"系统日志" },  notes = "根据需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByStory(@PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取MobType", tags = {"系统日志" } ,notes = "根据项目获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取Type", tags = {"系统日志" } ,notes = "根据项目获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'COLLECT')")
    @ApiOperation(value = "根据产品收藏", tags = {"系统日志" },  notes = "根据产品收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"系统日志" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"系统日志" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbycustom")
	public ResponseEntity<List<ActionDTO>> fetchActionByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByCustom(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"系统日志" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"系统日志" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproductnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProductNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"系统日志" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproject")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProject(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"系统日志" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyprojectnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProjectNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"系统日志" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurdoclib")
	public ResponseEntity<List<ActionDTO>> fetchActionCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurDocLib(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"系统日志" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyfavourites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"系统日志" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrootmodulemulu")
	public ResponseEntity<List<ActionDTO>> fetchActionRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootModuleMuLu(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立系统日志", tags = {"系统日志" },  notes = "根据产品文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取MobType", tags = {"系统日志" } ,notes = "根据产品文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取Type", tags = {"系统日志" } ,notes = "根据产品文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新系统日志", tags = {"系统日志" },  notes = "根据产品文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"系统日志" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"系统日志" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/assignto")
    public ResponseEntity<ActionDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.assignTo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"系统日志" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchunlinkbug")
    public ResponseEntity<ActionDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITES')")
    @ApiOperation(value = "根据产品Bug收藏", tags = {"系统日志" },  notes = "根据产品Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/bugfavorites")
    public ResponseEntity<ActionDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.bugFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"系统日志" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/bugnfavorites")
    public ResponseEntity<ActionDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.bugNFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品版本批量解除关联Bug", tags = {"系统日志" },  notes = "根据产品版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildbatchunlinkbug")
    public ResponseEntity<ActionDTO> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildBatchUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本关联Bug", tags = {"系统日志" },  notes = "根据产品版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildlinkbug")
    public ResponseEntity<ActionDTO> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildLinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联Bug", tags = {"系统日志" },  notes = "根据产品版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildunlinkbug")
    public ResponseEntity<ActionDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"系统日志" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/close")
    public ResponseEntity<ActionDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.close(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRM')")
    @ApiOperation(value = "根据产品确认", tags = {"系统日志" },  notes = "根据产品确认")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/confirm")
    public ResponseEntity<ActionDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.confirm(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"系统日志" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbug")
    public ResponseEntity<ActionDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"系统日志" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaasebatchunlinkbug")
    public ResponseEntity<ActionDTO> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaaseBatchUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）", tags = {"系统日志" },  notes = "根据产品关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaselinkbugbybug")
    public ResponseEntity<ActionDTO> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseLinkBugbyBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"系统日志" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaselinkbugbyleftbug")
    public ResponseEntity<ActionDTO> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseLinkBugbyLeftBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RESOLVE')")
    @ApiOperation(value = "根据产品移除关联Bug（遗留Bug）", tags = {"系统日志" },  notes = "根据产品移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<ActionDTO> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseUnLinkBugbyLeftBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"系统日志" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaseunlinkbug")
    public ResponseEntity<ActionDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RESOLVE')")
    @ApiOperation(value = "根据产品解决", tags = {"系统日志" },  notes = "根据产品解决")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/resolve")
    public ResponseEntity<ActionDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.resolve(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品行为", tags = {"系统日志" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendmessage")
    public ResponseEntity<ActionDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMessage(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"系统日志" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendmsgpreprocess")
    public ResponseEntity<ActionDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMsgPreProcess(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品TestScript", tags = {"系统日志" },  notes = "根据产品TestScript")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/testscript")
    public ResponseEntity<ActionDTO> testScriptByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.testScript(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TOSTORY')")
    @ApiOperation(value = "根据产品转需求", tags = {"系统日志" },  notes = "根据产品转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/tostory")
    public ResponseEntity<ActionDTO> toStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.toStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"系统日志" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkbug")
    public ResponseEntity<ActionDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新需求版本", tags = {"系统日志" },  notes = "根据产品更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/updatestoryversion")
    public ResponseEntity<ActionDTO> updateStoryVersionByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.updateStoryVersion(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug", tags = {"系统日志" } ,notes = "根据产品获取指派给我Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchassignedtomybug")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我Bug（PC）", tags = {"系统日志" } ,notes = "根据产品获取指派给我Bug（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchassignedtomybugpc")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyBugPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyBugPc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联bug(遗留的)", tags = {"系统日志" } ,notes = "根据产品获取版本关联bug(遗留的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbugsbybuild")
	public ResponseEntity<List<ActionDTO>> fetchActionBugsByBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugsByBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的已解决的Bugs集合", tags = {"系统日志" } ,notes = "根据产品获取版本可关联的已解决的Bugs集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildlinkresolvedbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildLinkResolvedBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildLinkResolvedBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联Bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取版本关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildopenbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildOpenBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildOpenBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebug")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugmodule")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugModule(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugmodule_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugModule_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugModule_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建分类", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-创建分类")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugopenedby")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugOpenedByByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugOpenedBy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-创建者分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-创建者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugopenedby_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugOpenedBy_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugOpenedBy_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugres")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugRESByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugRES(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-解决者分布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugresolvedby")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugRESOLVEDBYByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugRESOLVEDBY(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决者分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-解决者分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugresolvedby_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugRESOLVEDBY_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugRESOLVEDBY_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-解决方案分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-解决方案分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugresolution_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugResolution_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugResolution_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-严重程度分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-严重程度分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugseverity_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugSeverity_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugSeverity_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-状态分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-状态分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugstatus_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugStatus_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugStatus_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Build产生的Bug-类型分布(项目)", tags = {"系统日志" } ,notes = "根据产品获取Build产生的Bug-类型分布(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildproducebugtype_project")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildProduceBugType_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildProduceBugType_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取当前用户解决的Bug", tags = {"系统日志" } ,notes = "根据产品获取当前用户解决的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcuruserresolve")
	public ResponseEntity<List<ActionDTO>> fetchActionCurUserResolveByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurUserResolve(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"系统日志" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchesbulk")
	public ResponseEntity<List<ActionDTO>> fetchActionESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchESBulk(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我代理的Bug", tags = {"系统日志" } ,notes = "根据产品获取我代理的Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyagentbug")
	public ResponseEntity<List<ActionDTO>> fetchActionMyAgentBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyAgentBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的Bug数", tags = {"系统日志" } ,notes = "根据产品获取累计创建的Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmycuropenedbug")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCurOpenedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCurOpenedBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"系统日志" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyfavorites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavorites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划关联bug（去除已关联）", tags = {"系统日志" } ,notes = "根据产品获取计划关联bug（去除已关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchnotcurplanlinkbug")
	public ResponseEntity<List<ActionDTO>> fetchActionNotCurPlanLinkBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotCurPlanLinkBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取遗留得Bug(项目)", tags = {"系统日志" } ,notes = "根据产品获取遗留得Bug(项目)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojectbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleasebugs")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取发布关联Bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleaseleftbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseLeftBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseLeftBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（遗留）", tags = {"系统日志" } ,notes = "根据产品获取发布可关联的bug（遗留）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleaselinkableleftbug")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseLinkableLeftBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseLinkableLeftBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布可关联的bug（已解决）", tags = {"系统日志" } ,notes = "根据产品获取发布可关联的bug（已解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleaselinkableresolvedbug")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseLinkableResolvedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseLinkableResolvedBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取发布关联Bug（未解决）", tags = {"系统日志" } ,notes = "根据产品获取发布关联Bug（未解决）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportbugs")
	public ResponseEntity<List<ActionDTO>> fetchActionReportBugsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReportBugs(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取需求来源Bug", tags = {"系统日志" } ,notes = "根据产品获取需求来源Bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchstoryformbug")
	public ResponseEntity<List<ActionDTO>> fetchActionStoryFormBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchStoryFormBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务相关bug", tags = {"系统日志" } ,notes = "根据产品获取任务相关bug")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtaskrelatedbug")
	public ResponseEntity<List<ActionDTO>> fetchActionTaskRelatedBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTaskRelatedBug(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品Bug获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品Bug获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug建立系统日志", tags = {"系统日志" },  notes = "根据产品Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取MobType", tags = {"系统日志" } ,notes = "根据产品Bug获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取Type", tags = {"系统日志" } ,notes = "根据产品Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品Bug更新系统日志", tags = {"系统日志" },  notes = "根据产品Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品状态变更（激活）", tags = {"系统日志" },  notes = "根据产品状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"系统日志" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchunlinkbug")
    public ResponseEntity<ActionDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品状态变更", tags = {"系统日志" },  notes = "根据产品状态变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/changestatus")
    public ResponseEntity<ActionDTO> changeStatusByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.changeStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"系统日志" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbug")
    public ResponseEntity<ActionDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）", tags = {"系统日志" },  notes = "根据产品关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbugbybug")
    public ResponseEntity<ActionDTO> linkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBugbyBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"系统日志" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbugbyleftbug")
    public ResponseEntity<ActionDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBugbyLeftBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"系统日志" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkstory")
    public ResponseEntity<ActionDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移动端发布计数器", tags = {"系统日志" },  notes = "根据产品移动端发布计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/mobreleasecounter")
    public ResponseEntity<ActionDTO> mobReleaseCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobReleaseCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品一键发布", tags = {"系统日志" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/oneclickrelease")
    public ResponseEntity<ActionDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.oneClickRelease(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }


    @ApiOperation(value = "根据产品批量保存系统日志", tags = {"系统日志" },  notes = "根据产品批量保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
             
        }
        actionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TERMINATE')")
    @ApiOperation(value = "根据产品状态变更（停止维护）", tags = {"系统日志" },  notes = "根据产品状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/terminate")
    public ResponseEntity<ActionDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.terminate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"系统日志" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkbug")
    public ResponseEntity<ActionDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品解除关联需求", tags = {"系统日志" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkstory")
    public ResponseEntity<ActionDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联发布", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联发布")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportrelease")
	public ResponseEntity<List<ActionDTO>> fetchActionReportReleaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReportRelease(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品发布获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品发布获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布建立系统日志", tags = {"系统日志" },  notes = "根据产品发布建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions")
    public ResponseEntity<ActionDTO> createByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品发布获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品发布获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品发布获取系统日志", tags = {"系统日志" },  notes = "根据产品发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取MobType", tags = {"系统日志" } ,notes = "根据产品发布获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品发布获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取Type", tags = {"系统日志" } ,notes = "根据产品发布获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布更新系统日志", tags = {"系统日志" },  notes = "根据产品发布更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品发布获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据产品关联测试", tags = {"系统日志" },  notes = "根据产品关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkcase")
    public ResponseEntity<ActionDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移动端测试套件计数器", tags = {"系统日志" },  notes = "根据产品移动端测试套件计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/mobtestsuitecount")
    public ResponseEntity<ActionDTO> mobTestSuiteCountByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobTestSuiteCount(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品未关联测试", tags = {"系统日志" },  notes = "根据产品未关联测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkcase")
    public ResponseEntity<ActionDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取公开套件", tags = {"系统日志" } ,notes = "根据产品获取公开套件")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchpublictestsuite")
	public ResponseEntity<List<ActionDTO>> fetchActionPublicTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchPublicTestSuite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件建立系统日志", tags = {"系统日志" },  notes = "根据产品测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件获取系统日志", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取MobType", tags = {"系统日志" } ,notes = "根据产品测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取Type", tags = {"系统日志" } ,notes = "根据产品测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件更新系统日志", tags = {"系统日志" },  notes = "根据产品测试套件更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品激活", tags = {"系统日志" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品阻塞", tags = {"系统日志" },  notes = "根据产品阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/block")
    public ResponseEntity<ActionDTO> blockByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.block(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据产品关闭", tags = {"系统日志" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/close")
    public ResponseEntity<ActionDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.close(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"系统日志" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkcase")
    public ResponseEntity<ActionDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移动端测试版本计数器", tags = {"系统日志" },  notes = "根据产品移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/mobtesttaskcounter")
    public ResponseEntity<ActionDTO> mobTestTaskCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobTestTaskCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品开始", tags = {"系统日志" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/start")
    public ResponseEntity<ActionDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.start(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品关联测试用例", tags = {"系统日志" },  notes = "根据产品关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkcase")
    public ResponseEntity<ActionDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的测试单", tags = {"系统日志" } ,notes = "根据产品获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmytesttaskpc")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTestTaskPcByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyTestTaskPc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本建立系统日志", tags = {"系统日志" },  notes = "根据产品测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取系统日志", tags = {"系统日志" },  notes = "根据产品测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取MobType", tags = {"系统日志" } ,notes = "根据产品测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取Type", tags = {"系统日志" } ,notes = "根据产品测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新系统日志", tags = {"系统日志" },  notes = "根据产品测试版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息", tags = {"系统日志" },  notes = "根据产品根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotesttask")
    public ResponseEntity<ActionDTO> getInfoTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTask(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据起始时间获取概况信息", tags = {"系统日志" },  notes = "根据产品根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotaskovbytime")
    public ResponseEntity<ActionDTO> getInfoTaskOvByTimeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTaskOvByTime(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告概况信息（项目报告）", tags = {"系统日志" },  notes = "根据产品根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotesttaskovproject")
    public ResponseEntity<ActionDTO> getInfoTestTaskOvProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskOvProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）", tags = {"系统日志" },  notes = "根据产品根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotesttaskproject")
    public ResponseEntity<ActionDTO> getInfoTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（单测试）", tags = {"系统日志" },  notes = "根据产品根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotesttaskr")
    public ResponseEntity<ActionDTO> getInfoTestTaskRByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskR(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取相应信息（项目报告）（测试）", tags = {"系统日志" },  notes = "根据产品根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/getinfotesttasks")
    public ResponseEntity<ActionDTO> getInfoTestTaskSByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskS(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息", tags = {"系统日志" },  notes = "根据产品根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/gettestreportbasicinfo")
    public ResponseEntity<ActionDTO> getTestReportBasicInfoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTestReportBasicInfo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试报告获取基本信息（项目报告）", tags = {"系统日志" },  notes = "根据产品根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/gettestreportproject")
    public ResponseEntity<ActionDTO> getTestReportProjectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTestReportProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告建立系统日志", tags = {"系统日志" },  notes = "根据产品测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取MobType", tags = {"系统日志" } ,notes = "根据产品测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取Type", tags = {"系统日志" } ,notes = "根据产品测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试报告更新系统日志", tags = {"系统日志" },  notes = "根据产品测试报告更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据产品关联Bug", tags = {"系统日志" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbug")
    public ResponseEntity<ActionDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品关联需求", tags = {"系统日志" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkstory")
    public ResponseEntity<ActionDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移动端项目版本计数器", tags = {"系统日志" },  notes = "根据产品移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/mobprojectbuildcounter")
    public ResponseEntity<ActionDTO> mobProjectBuildCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobProjectBuildCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品一键发布", tags = {"系统日志" },  notes = "根据产品一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/oneclickrelease")
    public ResponseEntity<ActionDTO> oneClickReleaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.oneClickRelease(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品移除Bug关联", tags = {"系统日志" },  notes = "根据产品移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkbug")
    public ResponseEntity<ActionDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移除关联需求", tags = {"系统日志" },  notes = "根据产品移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkstory")
    public ResponseEntity<ActionDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取Bug产品版本", tags = {"系统日志" } ,notes = "根据产品获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbugproductbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionBugProductBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugProductBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取Bug产品或者项目版本", tags = {"系统日志" } ,notes = "根据产品获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbugproductorprojectbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionBugProductOrProjectBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugProductOrProjectBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"系统日志" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取测试版本", tags = {"系统日志" } ,notes = "根据产品获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtestbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionTestBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTestBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试轮次", tags = {"系统日志" } ,notes = "根据产品获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtestrounds")
	public ResponseEntity<List<ActionDTO>> fetchActionTestRoundsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTestRounds(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取更新日志", tags = {"系统日志" } ,notes = "根据产品获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchupdatelog")
	public ResponseEntity<List<ActionDTO>> fetchActionUpdateLogByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchUpdateLog(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本建立系统日志", tags = {"系统日志" },  notes = "根据产品版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取MobType", tags = {"系统日志" } ,notes = "根据产品版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取Type", tags = {"系统日志" } ,notes = "根据产品版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本更新系统日志", tags = {"系统日志" },  notes = "根据产品版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITE')")
    @ApiOperation(value = "根据产品行为", tags = {"系统日志" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/casefavorite")
    public ResponseEntity<ActionDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.caseFavorite(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite", tags = {"系统日志" },  notes = "根据产品CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/casenfavorite")
    public ResponseEntity<ActionDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.caseNFavorite(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRMCHANGE')")
    @ApiOperation(value = "根据产品确认用例变更", tags = {"系统日志" },  notes = "根据产品确认用例变更")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/confirmchange")
    public ResponseEntity<ActionDTO> confirmChangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.confirmChange(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品确认需求变更", tags = {"系统日志" },  notes = "根据产品确认需求变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/confirmstorychange")
    public ResponseEntity<ActionDTO> confirmstorychangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.confirmstorychange(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取或者状态", tags = {"系统日志" },  notes = "根据产品根据测试单获取或者状态")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/getbytesttask")
    public ResponseEntity<ActionDTO> getByTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getByTestTask(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASERESULT')")
    @ApiOperation(value = "根据产品获取测试单执行结果", tags = {"系统日志" },  notes = "根据产品获取测试单执行结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/gettesttaskcntrun")
    public ResponseEntity<ActionDTO> getTestTaskCntRunByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTestTaskCntRun(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试单关联测试用例", tags = {"系统日志" },  notes = "根据产品测试单关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkcase")
    public ResponseEntity<ActionDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'STORYLINK')")
    @ApiOperation(value = "根据产品移动端关联需求", tags = {"系统日志" },  notes = "根据产品移动端关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/moblinkcase")
    public ResponseEntity<ActionDTO> mobLinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobLinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"系统日志" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/runcase")
    public ResponseEntity<ActionDTO> runCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.runCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品runCases", tags = {"系统日志" },  notes = "根据产品runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/runcases")
    public ResponseEntity<ActionDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.runCases(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存系统日志", tags = {"系统日志" },  notes = "根据产品批量保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
             
        }
        actionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品执行测试", tags = {"系统日志" },  notes = "根据产品执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/testruncase")
    public ResponseEntity<ActionDTO> testRunCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.testRunCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品testRunCases", tags = {"系统日志" },  notes = "根据产品testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/testruncases")
    public ResponseEntity<ActionDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.testRunCases(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品套件关联", tags = {"系统日志" },  notes = "根据产品套件关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/testsuitelinkcase")
    public ResponseEntity<ActionDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.testsuitelinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"系统日志" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkcase")
    public ResponseEntity<ActionDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品unlinkCases", tags = {"系统日志" },  notes = "根据产品unlinkCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkcases")
    public ResponseEntity<ActionDTO> unlinkCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkCases(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品移除用例", tags = {"系统日志" },  notes = "根据产品移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinksuitecase")
    public ResponseEntity<ActionDTO> unlinkSuiteCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkSuiteCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品unlinkSuiteCases", tags = {"系统日志" },  notes = "根据产品unlinkSuiteCases")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinksuitecases")
    public ResponseEntity<ActionDTO> unlinkSuiteCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkSuiteCases(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取批量新建用例", tags = {"系统日志" } ,notes = "根据产品获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbatchnew")
	public ResponseEntity<List<ActionDTO>> fetchActionBatchNewByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBatchNew(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的用例", tags = {"系统日志" } ,notes = "根据产品获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcuropenedcase")
	public ResponseEntity<List<ActionDTO>> fetchActionCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurOpenedCase(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"系统日志" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcursuite")
	public ResponseEntity<List<ActionDTO>> fetchActionCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurSuite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"系统日志" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurtesttask")
	public ResponseEntity<List<ActionDTO>> fetchActionCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurTestTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"系统日志" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchesbulk")
	public ResponseEntity<List<ActionDTO>> fetchActionESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchESBulk(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmodulereportcase")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleRePortCase(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-按模块-条目", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmodulereportcaseentry")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleRePortCaseEntry(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-按模块", tags = {"系统日志" } ,notes = "根据产品获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmodulereportcase_project")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleRePortCase_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"系统日志" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyfavorites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavorites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"系统日志" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchnotcurtestsuite")
	public ResponseEntity<List<ActionDTO>> fetchActionNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotCurTestSuite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"系统日志" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchnotcurtesttask")
	public ResponseEntity<List<ActionDTO>> fetchActionNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotCurTestTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例（项目关联）", tags = {"系统日志" } ,notes = "根据产品获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchnotcurtesttaskproject")
	public ResponseEntity<List<ActionDTO>> fetchActionNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotCurTestTaskProject(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportcase")
	public ResponseEntity<List<ActionDTO>> fetchActionRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRePortCase(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例-条目", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportcaseentry")
	public ResponseEntity<List<ActionDTO>> fetchActionRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRePortCaseEntry(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联用例-关联用例", tags = {"系统日志" } ,notes = "根据产品获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportcase_project")
	public ResponseEntity<List<ActionDTO>> fetchActionRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRePortCase_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunerreportcase")
	public ResponseEntity<List<ActionDTO>> fetchActionRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunERRePortCase(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人-条目", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunerreportcaseentry")
	public ResponseEntity<List<ActionDTO>> fetchActionRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunERRePortCaseEntry(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行人", tags = {"系统日志" } ,notes = "根据产品获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunerreportcase_project")
	public ResponseEntity<List<ActionDTO>> fetchActionRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunERRePortCase_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunreportcase")
	public ResponseEntity<List<ActionDTO>> fetchActionRunRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunRePortCase(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联--执行结果条目", tags = {"系统日志" } ,notes = "根据产品获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunreportcaseentry")
	public ResponseEntity<List<ActionDTO>> fetchActionRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunRePortCaseEntry(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行结果", tags = {"系统日志" } ,notes = "根据产品获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrunreportcase_project")
	public ResponseEntity<List<ActionDTO>> fetchActionRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRunRePortCase_Project(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试用例获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例建立系统日志", tags = {"系统日志" },  notes = "根据产品测试用例建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions")
    public ResponseEntity<ActionDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试用例获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试用例获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取系统日志", tags = {"系统日志" },  notes = "根据产品测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取MobType", tags = {"系统日志" } ,notes = "根据产品测试用例获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试用例获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取Type", tags = {"系统日志" } ,notes = "根据产品测试用例获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例更新系统日志", tags = {"系统日志" },  notes = "根据产品测试用例更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试用例获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品批关联BUG", tags = {"系统日志" },  notes = "根据产品批关联BUG")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchlinkbug")
    public ResponseEntity<ActionDTO> batchLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchLinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品批关联需求", tags = {"系统日志" },  notes = "根据产品批关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchlinkstory")
    public ResponseEntity<ActionDTO> batchLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchLinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"系统日志" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchunlinkbug")
    public ResponseEntity<ActionDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchUnlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品批量解除关联需求", tags = {"系统日志" },  notes = "根据产品批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchunlinkstory")
    public ResponseEntity<ActionDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据产品EE激活计划", tags = {"系统日志" },  notes = "根据产品EE激活计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eeactiveplan")
    public ResponseEntity<ActionDTO> eeActivePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeActivePlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品EE取消计划", tags = {"系统日志" },  notes = "根据产品EE取消计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eecancelplan")
    public ResponseEntity<ActionDTO> eeCancelPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeCancelPlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品EE关闭计划", tags = {"系统日志" },  notes = "根据产品EE关闭计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eecloseplan")
    public ResponseEntity<ActionDTO> eeClosePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeClosePlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品EE完成计划", tags = {"系统日志" },  notes = "根据产品EE完成计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eefinishplan")
    public ResponseEntity<ActionDTO> eeFinishPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeFinishPlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品EE暂停计划", tags = {"系统日志" },  notes = "根据产品EE暂停计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eepauseplan")
    public ResponseEntity<ActionDTO> eePausePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eePausePlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品继续计划", tags = {"系统日志" },  notes = "根据产品继续计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eerestartplan")
    public ResponseEntity<ActionDTO> eeRestartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeRestartPlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品EE开始计划", tags = {"系统日志" },  notes = "根据产品EE开始计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/eestartplan")
    public ResponseEntity<ActionDTO> eeStartPlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.eeStartPlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取上一个计划的名称", tags = {"系统日志" },  notes = "根据产品获取上一个计划的名称")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/getoldplanname")
    public ResponseEntity<ActionDTO> getOldPlanNameByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getOldPlanName(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品导入计划模板", tags = {"系统日志" },  notes = "根据产品导入计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/importplantemplet")
    public ResponseEntity<ActionDTO> importPlanTempletByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.importPlanTemplet(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"系统日志" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkbug")
    public ResponseEntity<ActionDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"系统日志" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkstory")
    public ResponseEntity<ActionDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品关联任务", tags = {"系统日志" },  notes = "根据产品关联任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linktask")
    public ResponseEntity<ActionDTO> linkTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkTask(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品移动端产品计划计数器", tags = {"系统日志" },  notes = "根据产品移动端产品计划计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/mobproductplancounter")
    public ResponseEntity<ActionDTO> mobProductPlanCounterByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobProductPlanCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品解除关联Bug", tags = {"系统日志" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkbug")
    public ResponseEntity<ActionDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品解除关联需求", tags = {"系统日志" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkstory")
    public ResponseEntity<ActionDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子计划", tags = {"系统日志" } ,notes = "根据产品获取子计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchchildplan")
	public ResponseEntity<List<ActionDTO>> fetchActionChildPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取CurProductPlan", tags = {"系统日志" } ,notes = "根据产品获取CurProductPlan")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurproductplan")
	public ResponseEntity<List<ActionDTO>> fetchActionCurProductPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurProductPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"系统日志" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurproductplanstory")
	public ResponseEntity<List<ActionDTO>> fetchActionCurProductPlanStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurProductPlanStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取默认查询", tags = {"系统日志" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefaultparent")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultParentByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefaultParent(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划（代码表）", tags = {"系统日志" } ,notes = "根据产品获取计划（代码表）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchplancodelist")
	public ResponseEntity<List<ActionDTO>> fetchActionPlanCodeListByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchPlanCodeList(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划任务（项目管理-项目计划）", tags = {"系统日志" } ,notes = "根据产品获取项目计划任务（项目管理-项目计划）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchplantasks")
	public ResponseEntity<List<ActionDTO>> fetchActionPlanTasksByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchPlanTasks(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品默认查询", tags = {"系统日志" } ,notes = "根据产品获取产品默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchproductquery")
	public ResponseEntity<List<ActionDTO>> fetchActionProductQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProductQuery(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目立项", tags = {"系统日志" } ,notes = "根据产品获取项目立项")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojectapp")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectAppByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectApp(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目计划列表", tags = {"系统日志" } ,notes = "根据产品获取项目计划列表")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojectplan")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取跟计划", tags = {"系统日志" } ,notes = "根据产品获取跟计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrootplan")
	public ResponseEntity<List<ActionDTO>> fetchActionRootPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务计划", tags = {"系统日志" } ,notes = "根据产品获取任务计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtaskplan")
	public ResponseEntity<List<ActionDTO>> fetchActionTaskPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTaskPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品产品计划获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品产品计划获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立系统日志", tags = {"系统日志" },  notes = "根据产品产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取MobType", tags = {"系统日志" } ,notes = "根据产品产品计划获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划获取Type", tags = {"系统日志" } ,notes = "根据产品产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划更新系统日志", tags = {"系统日志" },  notes = "根据产品产品计划更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量建立系统日志", tags = {"系统日志" },  notes = "根据产品批量建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
            
        }
        actionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"系统日志" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品全部推送", tags = {"系统日志" },  notes = "根据产品全部推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/allpush")
    public ResponseEntity<ActionDTO> allPushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.allPush(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"系统日志" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/assignto")
    public ResponseEntity<ActionDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.assignTo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量指派", tags = {"系统日志" },  notes = "根据产品批量指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchassignto")
    public ResponseEntity<ActionDTO> batchAssignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchAssignTo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更平台/分支", tags = {"系统日志" },  notes = "根据产品批量变更平台/分支")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchchangebranch")
    public ResponseEntity<ActionDTO> batchChangeBranchByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchChangeBranch(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更模块", tags = {"系统日志" },  notes = "根据产品批量变更模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchchangemodule")
    public ResponseEntity<ActionDTO> batchChangeModuleByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchChangeModule(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量关联计划", tags = {"系统日志" },  notes = "根据产品批量关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchchangeplan")
    public ResponseEntity<ActionDTO> batchChangePlanByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchChangePlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量变更阶段", tags = {"系统日志" },  notes = "根据产品批量变更阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchchangestage")
    public ResponseEntity<ActionDTO> batchChangeStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchChangeStage(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量关闭", tags = {"系统日志" },  notes = "根据产品批量关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchclose")
    public ResponseEntity<ActionDTO> batchCloseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchClose(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品批量评审", tags = {"系统日志" },  notes = "根据产品批量评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchreview")
    public ResponseEntity<ActionDTO> batchReviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchReview(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品计划批量解除关联需求", tags = {"系统日志" },  notes = "根据产品计划批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/batchunlinkstory")
    public ResponseEntity<ActionDTO> batchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.batchUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品bug转需求", tags = {"系统日志" },  notes = "根据产品bug转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/bugtostory")
    public ResponseEntity<ActionDTO> bugToStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.bugToStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本批量解除关联需求", tags = {"系统日志" },  notes = "根据产品版本批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildbatchunlinkstory")
    public ResponseEntity<ActionDTO> buildBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildBatchUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"系统日志" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildlinkstory")
    public ResponseEntity<ActionDTO> buildLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildLinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"系统日志" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildunlinkstory")
    public ResponseEntity<ActionDTO> buildUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本解除关联需求", tags = {"系统日志" },  notes = "根据产品版本解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/buildunlinkstorys")
    public ResponseEntity<ActionDTO> buildUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.buildUnlinkStorys(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CHANGE')")
    @ApiOperation(value = "根据产品变更", tags = {"系统日志" },  notes = "根据产品变更")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/change")
    public ResponseEntity<ActionDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.change(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"系统日志" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/close")
    public ResponseEntity<ActionDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.close(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品生成任务", tags = {"系统日志" },  notes = "根据产品生成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/createtasks")
    public ResponseEntity<ActionDTO> createTasksByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.createTasks(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"系统日志" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/getstoryspec")
    public ResponseEntity<ActionDTO> getStorySpecByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getStorySpec(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求描述", tags = {"系统日志" },  notes = "根据产品获取需求描述")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/getstoryspecs")
    public ResponseEntity<ActionDTO> getStorySpecsByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getStorySpecs(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目关联需求-按计划关联", tags = {"系统日志" },  notes = "根据产品项目关联需求-按计划关联")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/importplanstories")
    public ResponseEntity<ActionDTO> importPlanStoriesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.importPlanStories(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品计划关联需求", tags = {"系统日志" },  notes = "根据产品计划关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/linkstory")
    public ResponseEntity<ActionDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目批量解除关联需求", tags = {"系统日志" },  notes = "根据产品项目批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/projectbatchunlinkstory")
    public ResponseEntity<ActionDTO> projectBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.projectBatchUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"系统日志" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/projectlinkstory")
    public ResponseEntity<ActionDTO> projectLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.projectLinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PROJECTLINK')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"系统日志" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/projectunlinkstory")
    public ResponseEntity<ActionDTO> projectUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.projectUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品项目解除关联需求", tags = {"系统日志" },  notes = "根据产品项目解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/projectunlinkstorys")
    public ResponseEntity<ActionDTO> projectUnlinkStorysByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.projectUnlinkStorys(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品推送", tags = {"系统日志" },  notes = "根据产品推送")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/push")
    public ResponseEntity<ActionDTO> pushByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.push(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发布批量解除关联需求", tags = {"系统日志" },  notes = "根据产品发布批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releasebatchunlinkstory")
    public ResponseEntity<ActionDTO> releaseBatchUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseBatchUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品发布关联需求", tags = {"系统日志" },  notes = "根据产品发布关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaselinkstory")
    public ResponseEntity<ActionDTO> releaseLinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseLinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RELEASELINK')")
    @ApiOperation(value = "根据产品发布解除关联需求", tags = {"系统日志" },  notes = "根据产品发布解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/releaseunlinkstory")
    public ResponseEntity<ActionDTO> releaseUnlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.releaseUnlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品重置由谁评审", tags = {"系统日志" },  notes = "根据产品重置由谁评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/resetreviewedby")
    public ResponseEntity<ActionDTO> resetReviewedByByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.resetReviewedBy(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'REVIEW')")
    @ApiOperation(value = "根据产品评审", tags = {"系统日志" },  notes = "根据产品评审")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/review")
    public ResponseEntity<ActionDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.review(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存系统日志", tags = {"系统日志" },  notes = "根据产品批量保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
             
        }
        actionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品行为", tags = {"系统日志" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendmessage")
    public ResponseEntity<ActionDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMessage(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"系统日志" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendmsgpreprocess")
    public ResponseEntity<ActionDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMsgPreProcess(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品设置需求阶段", tags = {"系统日志" },  notes = "根据产品设置需求阶段")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/setstage")
    public ResponseEntity<ActionDTO> setStageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.setStage(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITES')")
    @ApiOperation(value = "根据产品需求收藏", tags = {"系统日志" },  notes = "根据产品需求收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/storyfavorites")
    public ResponseEntity<ActionDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.storyFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"系统日志" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/storynfavorites")
    public ResponseEntity<ActionDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.storyNFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品同步Ibz平台实体", tags = {"系统日志" },  notes = "根据产品同步Ibz平台实体")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/syncfromibiz")
    public ResponseEntity<ActionDTO> syncFromIbizByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.syncFromIbiz(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'PLANLINK')")
    @ApiOperation(value = "根据产品计划解除关联需求", tags = {"系统日志" },  notes = "根据产品计划解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/unlinkstory")
    public ResponseEntity<ActionDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求", tags = {"系统日志" } ,notes = "根据产品获取指派给我的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchassignedtomystory")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指派给我的需求（日历）", tags = {"系统日志" } ,notes = "根据产品获取指派给我的需求（日历）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchassignedtomystorycalendar")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyStoryCalendarByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyStoryCalendar(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据产品获取Bug相关需求", tags = {"系统日志" } ,notes = "根据产品获取Bug相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbugstory")
	public ResponseEntity<List<ActionDTO>> fetchActionBugStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本关联已完成的需求（选择数据源）", tags = {"系统日志" } ,notes = "根据产品获取版本关联已完成的需求（选择数据源）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildlinkcompletedstories")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildLinkCompletedStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildLinkCompletedStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的需求（产品内）", tags = {"系统日志" } ,notes = "根据产品获取版本可关联的需求（产品内）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildlinkablestories")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildLinkableStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取版本相关需求", tags = {"系统日志" } ,notes = "根据产品获取获取版本相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbuildstories")
	public ResponseEntity<List<ActionDTO>> fetchActionBuildStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBuildStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"系统日志" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbymodule")
	public ResponseEntity<List<ActionDTO>> fetchActionByModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByModule(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取相关用例需求", tags = {"系统日志" } ,notes = "根据产品获取相关用例需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcasestory")
	public ResponseEntity<List<ActionDTO>> fetchActionCaseStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCaseStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取子需求（更多）", tags = {"系统日志" } ,notes = "根据产品获取子需求（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchchildmore")
	public ResponseEntity<List<ActionDTO>> fetchActionChildMoreByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildMore(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"系统日志" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchesbulk")
	public ResponseEntity<List<ActionDTO>> fetchActionESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchESBulk(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品需求", tags = {"系统日志" } ,notes = "根据产品获取获取产品需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchgetproductstories")
	public ResponseEntity<List<ActionDTO>> fetchActionGetProductStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchGetProductStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我代理的需求", tags = {"系统日志" } ,notes = "根据产品获取我代理的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyagentstory")
	public ResponseEntity<List<ActionDTO>> fetchActionMyAgentStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyAgentStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所创建需求数和对应的优先级及状态", tags = {"系统日志" } ,notes = "根据产品获取所创建需求数和对应的优先级及状态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmycuropenedstory")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCurOpenedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCurOpenedStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"系统日志" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyfavorites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavorites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取计划关联需求(去除已关联)", tags = {"系统日志" } ,notes = "根据产品获取计划关联需求(去除已关联)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchnotcurplanlinkstory")
	public ResponseEntity<List<ActionDTO>> fetchActionNotCurPlanLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotCurPlanLinkStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"系统日志" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchparentdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionParentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchParentDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"系统日志" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchparentdefaultq")
	public ResponseEntity<List<ActionDTO>> fetchActionParentDefaultQByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchParentDefaultQ(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目关联需求", tags = {"系统日志" } ,notes = "根据产品获取项目关联需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojectlinkstory")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectLinkStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectLinkStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目相关需求", tags = {"系统日志" } ,notes = "根据产品获取项目相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchprojectstories")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取版本可关联的完成的需求", tags = {"系统日志" } ,notes = "根据产品获取版本可关联的完成的需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleaselinkablestories")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseLinkableStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseLinkableStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"系统日志" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreleasestories")
	public ResponseEntity<List<ActionDTO>> fetchActionReleaseStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReleaseStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取通过模块查询", tags = {"系统日志" } ,notes = "根据产品获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchreportstories")
	public ResponseEntity<List<ActionDTO>> fetchActionReportStoriesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchReportStories(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"系统日志" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchstorychild")
	public ResponseEntity<List<ActionDTO>> fetchActionStoryChildByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchStoryChild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取获取产品发布相关需求", tags = {"系统日志" } ,notes = "根据产品获取获取产品发布相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchstoryrelated")
	public ResponseEntity<List<ActionDTO>> fetchActionStoryRelatedByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchStoryRelated(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取需求细分", tags = {"系统日志" } ,notes = "根据产品获取需求细分")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchsubstory")
	public ResponseEntity<List<ActionDTO>> fetchActionSubStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchSubStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取任务相关需求", tags = {"系统日志" } ,notes = "根据产品获取任务相关需求")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchtaskrelatedstory")
	public ResponseEntity<List<ActionDTO>> fetchActionTaskRelatedStoryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTaskRelatedStory(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取默认（全部数据）", tags = {"系统日志" } ,notes = "根据产品获取默认（全部数据）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchview")
	public ResponseEntity<List<ActionDTO>> fetchActionViewByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchView(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品需求获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品需求获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求建立系统日志", tags = {"系统日志" },  notes = "根据产品需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取MobType", tags = {"系统日志" } ,notes = "根据产品需求获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求获取Type", tags = {"系统日志" } ,notes = "根据产品需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求更新系统日志", tags = {"系统日志" },  notes = "根据产品需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立系统日志", tags = {"系统日志" },  notes = "根据文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新系统日志", tags = {"系统日志" },  notes = "根据文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库删除系统日志", tags = {"系统日志" },  notes = "根据文档库删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取系统日志", tags = {"系统日志" },  notes = "根据文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库根据版本更新正文信息", tags = {"系统日志" },  notes = "根据文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/actions/{action_id}/byversionupdatecontext")
    public ResponseEntity<ActionDTO> byVersionUpdateContextByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.byVersionUpdateContext(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库检查系统日志", tags = {"系统日志" },  notes = "根据文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库收藏", tags = {"系统日志" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库行为", tags = {"系统日志" },  notes = "根据文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/actions/{action_id}/getdocstatus")
    public ResponseEntity<ActionDTO> getDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getDocStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅收藏文档", tags = {"系统日志" },  notes = "根据文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/onlycollectdoc")
    public ResponseEntity<ActionDTO> onlyCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅取消收藏文档", tags = {"系统日志" },  notes = "根据文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/onlyuncollectdoc")
    public ResponseEntity<ActionDTO> onlyUnCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyUnCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库保存系统日志", tags = {"系统日志" },  notes = "根据文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"系统日志" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档（子库）", tags = {"系统日志" } ,notes = "根据文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchchilddoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdoclibanddoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibAndDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库分类文档", tags = {"系统日志" } ,notes = "根据文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdocmoduledoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocModuleDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档统计", tags = {"系统日志" } ,notes = "根据文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchdocstatus")
	public ResponseEntity<List<ActionDTO>> fetchActionDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocStatus(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文件夹文档（子目录）", tags = {"系统日志" } ,notes = "根据文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmoduledocchild")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleDocChild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmyfavourite")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavouritesOnlyDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取子目录文档", tags = {"系统日志" } ,notes = "根据文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchnotrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取根目录文档", tags = {"系统日志" } ,notes = "根据文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取DEFAULT", tags = {"系统日志" } ,notes = "根据文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取查询用户使用年", tags = {"系统日志" } ,notes = "根据文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取MobType", tags = {"系统日志" } ,notes = "根据文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取Type", tags = {"系统日志" } ,notes = "根据文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库文档更新系统日志", tags = {"系统日志" },  notes = "根据文档库文档更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'COLLECT')")
    @ApiOperation(value = "根据项目收藏", tags = {"系统日志" },  notes = "根据项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"系统日志" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"系统日志" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbycustom")
	public ResponseEntity<List<ActionDTO>> fetchActionByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByCustom(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"系统日志" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"系统日志" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproductnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProductNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"系统日志" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproject")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProject(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"系统日志" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyprojectnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProjectNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"系统日志" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchcurdoclib")
	public ResponseEntity<List<ActionDTO>> fetchActionCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurDocLib(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"系统日志" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyfavourites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"系统日志" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchrootmodulemulu")
	public ResponseEntity<List<ActionDTO>> fetchActionRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootModuleMuLu(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目文档库获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立系统日志", tags = {"系统日志" },  notes = "根据项目文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取MobType", tags = {"系统日志" } ,notes = "根据项目文档库获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取Type", tags = {"系统日志" } ,notes = "根据项目文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新系统日志", tags = {"系统日志" },  notes = "根据项目文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立系统日志", tags = {"系统日志" },  notes = "根据项目批量建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
            
        }
        actionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"系统日志" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派/转交", tags = {"系统日志" },  notes = "根据项目指派/转交")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/assignto")
    public ResponseEntity<ActionDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.assignTo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CANCEL')")
    @ApiOperation(value = "根据项目取消", tags = {"系统日志" },  notes = "根据项目取消")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/cancel")
    public ResponseEntity<ActionDTO> cancelByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.cancel(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CLOSE')")
    @ApiOperation(value = "根据项目关闭", tags = {"系统日志" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/close")
    public ResponseEntity<ActionDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.close(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目计算开始时间和完成时间", tags = {"系统日志" },  notes = "根据项目计算开始时间和完成时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/computebeginandend")
    public ResponseEntity<ActionDTO> computeBeginAndEndByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.computeBeginAndEnd(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目更新父任务时间", tags = {"系统日志" },  notes = "根据项目更新父任务时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/computehours4multiple")
    public ResponseEntity<ActionDTO> computeHours4MultipleByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.computeHours4Multiple(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目更新工作时间", tags = {"系统日志" },  notes = "根据项目更新工作时间")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/computeworkinghours")
    public ResponseEntity<ActionDTO> computeWorkingHoursByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.computeWorkingHours(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目需求变更确认", tags = {"系统日志" },  notes = "根据项目需求变更确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/confirmstorychange")
    public ResponseEntity<ActionDTO> confirmStoryChangeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.confirmStoryChange(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"系统日志" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/createbycycle")
    public ResponseEntity<ActionDTO> createByCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.createByCycle(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建周期任务", tags = {"系统日志" },  notes = "根据项目创建周期任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/createcycletasks")
    public ResponseEntity<ActionDTO> createCycleTasksByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.createCycleTasks(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目删除任务", tags = {"系统日志" },  notes = "根据项目删除任务")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/delete")
    public ResponseEntity<ActionDTO> deleteByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.delete(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目删除工时", tags = {"系统日志" },  notes = "根据项目删除工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/deleteestimate")
    public ResponseEntity<ActionDTO> deleteEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.deleteEstimate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目编辑工时", tags = {"系统日志" },  notes = "根据项目编辑工时")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/editestimate")
    public ResponseEntity<ActionDTO> editEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.editEstimate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'FINISH')")
    @ApiOperation(value = "根据项目完成", tags = {"系统日志" },  notes = "根据项目完成")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/finish")
    public ResponseEntity<ActionDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.finish(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取下一个团队成员(完成)", tags = {"系统日志" },  notes = "根据项目获取下一个团队成员(完成)")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getnextteamuser")
    public ResponseEntity<ActionDTO> getNextTeamUserByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getNextTeamUser(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（激活）", tags = {"系统日志" },  notes = "根据项目获取团队成员剩余工时（激活）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getteamuserleftactivity")
    public ResponseEntity<ActionDTO> getTeamUserLeftActivityByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTeamUserLeftActivity(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员剩余工时（开始或继续）", tags = {"系统日志" },  notes = "根据项目获取团队成员剩余工时（开始或继续）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getteamuserleftstart")
    public ResponseEntity<ActionDTO> getTeamUserLeftStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTeamUserLeftStart(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取团队成员", tags = {"系统日志" },  notes = "根据项目获取团队成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getusernames")
    public ResponseEntity<ActionDTO> getUsernamesByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getUsernames(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目关联计划", tags = {"系统日志" },  notes = "根据项目关联计划")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/linkplan")
    public ResponseEntity<ActionDTO> linkPlanByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkPlan(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目其他更新", tags = {"系统日志" },  notes = "根据项目其他更新")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/otherupdate")
    public ResponseEntity<ActionDTO> otherUpdateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.otherUpdate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'PAUSE')")
    @ApiOperation(value = "根据项目暂停", tags = {"系统日志" },  notes = "根据项目暂停")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/pause")
    public ResponseEntity<ActionDTO> pauseByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.pause(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RECORDESTIMATE')")
    @ApiOperation(value = "根据项目工时录入", tags = {"系统日志" },  notes = "根据项目工时录入")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/recordestimate")
    public ResponseEntity<ActionDTO> recordEstimateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.recordEstimate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目继续任务时填入预计剩余为0设置为进行中", tags = {"系统日志" },  notes = "根据项目继续任务时填入预计剩余为0设置为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/recordtimzeroleftaftercontinue")
    public ResponseEntity<ActionDTO> recordTimZeroLeftAfterContinueByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.recordTimZeroLeftAfterContinue(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目预计剩余为0进行中", tags = {"系统日志" },  notes = "根据项目预计剩余为0进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/recordtimatezeroleft")
    public ResponseEntity<ActionDTO> recordTimateZeroLeftByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.recordTimateZeroLeft(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目开始任务时填入预计剩余为0设为进行中", tags = {"系统日志" },  notes = "根据项目开始任务时填入预计剩余为0设为进行中")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/recordtimatezeroleftafterstart")
    public ResponseEntity<ActionDTO> recordTimateZeroLeftAfterStartByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.recordTimateZeroLeftAfterStart(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'RESTART')")
    @ApiOperation(value = "根据项目继续", tags = {"系统日志" },  notes = "根据项目继续")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/restart")
    public ResponseEntity<ActionDTO> restartByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.restart(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量保存系统日志", tags = {"系统日志" },  notes = "根据项目批量保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ActionDTO> actiondtos) {
        List<Action> domainlist=actionMapping.toDomain(actiondtos);
        for(Action domain:domainlist){
             
        }
        actionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目行为", tags = {"系统日志" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/sendmessage")
    public ResponseEntity<ActionDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMessage(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"系统日志" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/sendmsgpreprocess")
    public ResponseEntity<ActionDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.sendMsgPreProcess(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'START')")
    @ApiOperation(value = "根据项目开始", tags = {"系统日志" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/start")
    public ResponseEntity<ActionDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.start(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"系统日志" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/taskfavorites")
    public ResponseEntity<ActionDTO> taskFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.taskFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目检查多人任务操作权限", tags = {"系统日志" },  notes = "根据项目检查多人任务操作权限")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/taskforward")
    public ResponseEntity<ActionDTO> taskForwardByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.taskForward(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务收藏", tags = {"系统日志" },  notes = "根据项目任务收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/tasknfavorites")
    public ResponseEntity<ActionDTO> taskNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.taskNFavorites(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务状态", tags = {"系统日志" },  notes = "根据项目更新父任务状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/updateparentstatus")
    public ResponseEntity<ActionDTO> updateParentStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.updateParentStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新父任务计划状态", tags = {"系统日志" },  notes = "根据项目更新父任务计划状态")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/updaterelatedplanstatus")
    public ResponseEntity<ActionDTO> updateRelatedPlanStatusByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.updateRelatedPlanStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"系统日志" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/updatestoryversion")
    public ResponseEntity<ActionDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.updateStoryVersion(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务", tags = {"系统日志" } ,notes = "根据项目获取指派给我任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchassignedtomytask")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取指派给我任务（PC）", tags = {"系统日志" } ,notes = "根据项目获取指派给我任务（PC）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchassignedtomytaskpc")
	public ResponseEntity<List<ActionDTO>> fetchActionAssignedToMyTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAssignedToMyTaskPc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug相关任务", tags = {"系统日志" } ,notes = "根据项目获取Bug相关任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbugtask")
	public ResponseEntity<List<ActionDTO>> fetchActionBugTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取通过模块查询", tags = {"系统日志" } ,notes = "根据项目获取通过模块查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbymodule")
	public ResponseEntity<List<ActionDTO>> fetchActionByModuleByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByModule(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据查询（子任务）", tags = {"系统日志" } ,notes = "根据项目获取数据查询（子任务）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchchilddefault")
	public ResponseEntity<List<ActionDTO>> fetchActionChildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（更多）", tags = {"系统日志" } ,notes = "根据项目获取子任务（更多）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchchilddefaultmore")
	public ResponseEntity<List<ActionDTO>> fetchActionChildDefaultMoreByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildDefaultMore(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务", tags = {"系统日志" } ,notes = "根据项目获取子任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchchildtask")
	public ResponseEntity<List<ActionDTO>> fetchActionChildTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取子任务（树）", tags = {"系统日志" } ,notes = "根据项目获取子任务（树）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchchildtasktree")
	public ResponseEntity<List<ActionDTO>> fetchActionChildTaskTreeByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildTaskTree(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取用户年度完成任务", tags = {"系统日志" } ,notes = "根据项目获取用户年度完成任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchcurfinishtask")
	public ResponseEntity<List<ActionDTO>> fetchActionCurFinishTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurFinishTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取当前项目任务", tags = {"系统日志" } ,notes = "根据项目获取当前项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchcurprojecttaskquery")
	public ResponseEntity<List<ActionDTO>> fetchActionCurProjectTaskQueryByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurProjectTaskQuery(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DefaultRow", tags = {"系统日志" } ,notes = "根据项目获取DefaultRow")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefaultrow")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultRowByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefaultRow(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取ES批量的导入", tags = {"系统日志" } ,notes = "根据项目获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchesbulk")
	public ResponseEntity<List<ActionDTO>> fetchActionESBulkByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchESBulk(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我代理的任务", tags = {"系统日志" } ,notes = "根据项目获取我代理的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyagenttask")
	public ResponseEntity<List<ActionDTO>> fetchActionMyAgentTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyAgentTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我相关的任务", tags = {"系统日志" } ,notes = "根据项目获取我相关的任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyalltask")
	public ResponseEntity<List<ActionDTO>> fetchActionMyAllTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyAllTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"系统日志" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmycompletetask")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCompleteTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端日报）", tags = {"系统日志" } ,notes = "根据项目获取我完成的任务（移动端日报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmycompletetaskmobdaily")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCompleteTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCompleteTaskMobDaily(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（移动端月报）", tags = {"系统日志" } ,notes = "根据项目获取我完成的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmycompletetaskmobmonthly")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCompleteTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCompleteTaskMobMonthly(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（月报展示）", tags = {"系统日志" } ,notes = "根据项目获取我完成的任务（月报展示）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmycompletetaskmonthlyzs")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCompleteTaskMonthlyZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCompleteTaskMonthlyZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我完成的任务（汇报）", tags = {"系统日志" } ,notes = "根据项目获取我完成的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmycompletetaskzs")
	public ResponseEntity<List<ActionDTO>> fetchActionMyCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyCompleteTaskZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"系统日志" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyfavorites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavoritesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavorites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（移动端月报）", tags = {"系统日志" } ,notes = "根据项目获取我计划参与的任务（移动端月报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyplanstaskmobmonthly")
	public ResponseEntity<List<ActionDTO>> fetchActionMyPlansTaskMobMonthlyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyPlansTaskMobMonthly(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"系统日志" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmytomorrowplantask")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTomorrowPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyTomorrowPlanTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我计划参与的任务（汇报）", tags = {"系统日志" } ,notes = "根据项目获取我计划参与的任务（汇报）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmytomorrowplantaskmobdaily")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTomorrowPlanTaskMobDailyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyTomorrowPlanTaskMobDaily(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端下周计划参与(汇报)", tags = {"系统日志" } ,notes = "根据项目获取移动端下周计划参与(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchnextweekcompletetaskmobzs")
	public ResponseEntity<List<ActionDTO>> fetchActionNextWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNextWeekCompleteTaskMobZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"系统日志" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchnextweekcompletetaskzs")
	public ResponseEntity<List<ActionDTO>> fetchActionNextWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNextWeekCompleteTaskZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取下周计划完成任务(汇报)", tags = {"系统日志" } ,notes = "根据项目获取下周计划完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchnextweekplancompletetask")
	public ResponseEntity<List<ActionDTO>> fetchActionNextWeekPlanCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNextWeekPlanCompleteTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取相关任务（计划）", tags = {"系统日志" } ,notes = "根据项目获取相关任务（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchplantask")
	public ResponseEntity<List<ActionDTO>> fetchActionPlanTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchPlanTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务（项目立项）", tags = {"系统日志" } ,notes = "根据项目获取项目任务（项目立项）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchprojectapptask")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectAppTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectAppTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目任务", tags = {"系统日志" } ,notes = "根据项目获取项目任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchprojecttask")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根任务", tags = {"系统日志" } ,notes = "根据项目获取根任务")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchroottask")
	public ResponseEntity<List<ActionDTO>> fetchActionRootTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划（当前项目未关联）", tags = {"系统日志" } ,notes = "根据项目获取关联计划（当前项目未关联）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtasklinkplan")
	public ResponseEntity<List<ActionDTO>> fetchActionTaskLinkPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTaskLinkPlan(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我本月完成的任务（下拉列表框）", tags = {"系统日志" } ,notes = "根据项目获取我本月完成的任务（下拉列表框）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchthismonthcompletetaskchoice")
	public ResponseEntity<List<ActionDTO>> fetchActionThisMonthCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchThisMonthCompleteTaskChoice(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"系统日志" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchthisweekcompletetask")
	public ResponseEntity<List<ActionDTO>> fetchActionThisWeekCompleteTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchThisWeekCompleteTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周已完成任务(下拉框选择)", tags = {"系统日志" } ,notes = "根据项目获取本周已完成任务(下拉框选择)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchthisweekcompletetaskchoice")
	public ResponseEntity<List<ActionDTO>> fetchActionThisWeekCompleteTaskChoiceByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchThisWeekCompleteTaskChoice(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取移动端本周已完成任务(汇报)", tags = {"系统日志" } ,notes = "根据项目获取移动端本周已完成任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchthisweekcompletetaskmobzs")
	public ResponseEntity<List<ActionDTO>> fetchActionThisWeekCompleteTaskMobZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchThisWeekCompleteTaskMobZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取本周完成的任务(汇报)", tags = {"系统日志" } ,notes = "根据项目获取本周完成的任务(汇报)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchthisweekcompletetaskzs")
	public ResponseEntity<List<ActionDTO>> fetchActionThisWeekCompleteTaskZSByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchThisWeekCompleteTaskZS(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取todo列表查询", tags = {"系统日志" } ,notes = "根据项目获取todo列表查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtodolisttask")
	public ResponseEntity<List<ActionDTO>> fetchActionTodoListTaskByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTodoListTask(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组", tags = {"系统日志" } ,notes = "根据项目获取任务类型分组")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtypegroup")
	public ResponseEntity<List<Map>> fetchActionTypeGroupByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Map> domains = actionService.searchTypeGroup(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取任务类型分组（计划）", tags = {"系统日志" } ,notes = "根据项目获取任务类型分组（计划）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtypegroupplan")
	public ResponseEntity<List<Map>> fetchActionTypeGroupPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Map> domains = actionService.searchTypeGroupPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(domains.getContent());
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目任务获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目任务获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立系统日志", tags = {"系统日志" },  notes = "根据项目任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取MobType", tags = {"系统日志" } ,notes = "根据项目任务获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取Type", tags = {"系统日志" } ,notes = "根据项目任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新系统日志", tags = {"系统日志" },  notes = "根据项目任务更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息", tags = {"系统日志" },  notes = "根据项目根据测试单获取相应信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotesttask")
    public ResponseEntity<ActionDTO> getInfoTestTaskByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTask(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据起始时间获取概况信息", tags = {"系统日志" },  notes = "根据项目根据起始时间获取概况信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotaskovbytime")
    public ResponseEntity<ActionDTO> getInfoTaskOvByTimeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTaskOvByTime(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告概况信息（项目报告）", tags = {"系统日志" },  notes = "根据项目根据测试报告概况信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotesttaskovproject")
    public ResponseEntity<ActionDTO> getInfoTestTaskOvProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskOvProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）", tags = {"系统日志" },  notes = "根据项目根据测试单获取相应信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotesttaskproject")
    public ResponseEntity<ActionDTO> getInfoTestTaskProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（单测试）", tags = {"系统日志" },  notes = "根据项目根据测试单获取相应信息（项目报告）（单测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotesttaskr")
    public ResponseEntity<ActionDTO> getInfoTestTaskRByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskR(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试单获取相应信息（项目报告）（测试）", tags = {"系统日志" },  notes = "根据项目根据测试单获取相应信息（项目报告）（测试）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/getinfotesttasks")
    public ResponseEntity<ActionDTO> getInfoTestTaskSByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getInfoTestTaskS(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息", tags = {"系统日志" },  notes = "根据项目根据测试报告获取基本信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/gettestreportbasicinfo")
    public ResponseEntity<ActionDTO> getTestReportBasicInfoByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTestReportBasicInfo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目根据测试报告获取基本信息（项目报告）", tags = {"系统日志" },  notes = "根据项目根据测试报告获取基本信息（项目报告）")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/gettestreportproject")
    public ResponseEntity<ActionDTO> getTestReportProjectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getTestReportProject(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目测试报告获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目测试报告获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告建立系统日志", tags = {"系统日志" },  notes = "根据项目测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取MobType", tags = {"系统日志" } ,notes = "根据项目测试报告获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取Type", tags = {"系统日志" } ,notes = "根据项目测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试报告更新系统日志", tags = {"系统日志" },  notes = "根据项目测试报告更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目激活", tags = {"系统日志" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/activate")
    public ResponseEntity<ActionDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.activate(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目阻塞", tags = {"系统日志" },  notes = "根据项目阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/block")
    public ResponseEntity<ActionDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.block(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据项目关闭", tags = {"系统日志" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/close")
    public ResponseEntity<ActionDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.close(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"系统日志" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/linkcase")
    public ResponseEntity<ActionDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目移动端测试版本计数器", tags = {"系统日志" },  notes = "根据项目移动端测试版本计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/mobtesttaskcounter")
    public ResponseEntity<ActionDTO> mobTestTaskCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobTestTaskCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目开始", tags = {"系统日志" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/start")
    public ResponseEntity<ActionDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.start(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目关联测试用例", tags = {"系统日志" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/unlinkcase")
    public ResponseEntity<ActionDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkCase(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的测试单", tags = {"系统日志" } ,notes = "根据项目获取我的测试单")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmytesttaskpc")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTestTaskPcByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyTestTaskPc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目测试版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目测试版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立系统日志", tags = {"系统日志" },  notes = "根据项目测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取MobType", tags = {"系统日志" } ,notes = "根据项目测试版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取Type", tags = {"系统日志" } ,notes = "根据项目测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新系统日志", tags = {"系统日志" },  notes = "根据项目测试版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @ApiOperation(value = "根据项目关联Bug", tags = {"系统日志" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/linkbug")
    public ResponseEntity<ActionDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目关联需求", tags = {"系统日志" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/linkstory")
    public ResponseEntity<ActionDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.linkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目移动端项目版本计数器", tags = {"系统日志" },  notes = "根据项目移动端项目版本计数器")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/mobprojectbuildcounter")
    public ResponseEntity<ActionDTO> mobProjectBuildCounterByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.mobProjectBuildCounter(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目一键发布", tags = {"系统日志" },  notes = "根据项目一键发布")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/oneclickrelease")
    public ResponseEntity<ActionDTO> oneClickReleaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.oneClickRelease(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目移除Bug关联", tags = {"系统日志" },  notes = "根据项目移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/unlinkbug")
    public ResponseEntity<ActionDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkBug(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目移除关联需求", tags = {"系统日志" },  notes = "根据项目移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/unlinkstory")
    public ResponseEntity<ActionDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unlinkStory(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取Bug产品版本", tags = {"系统日志" } ,notes = "根据项目获取Bug产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbugproductbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionBugProductBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugProductBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取Bug产品或者项目版本", tags = {"系统日志" } ,notes = "根据项目获取Bug产品或者项目版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbugproductorprojectbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionBugProductOrProjectBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchBugProductOrProjectBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"系统日志" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchcurproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionCurProductByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "根据项目获取测试版本", tags = {"系统日志" } ,notes = "根据项目获取测试版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtestbuild")
	public ResponseEntity<List<ActionDTO>> fetchActionTestBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTestBuild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取测试轮次", tags = {"系统日志" } ,notes = "根据项目获取测试轮次")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchtestrounds")
	public ResponseEntity<List<ActionDTO>> fetchActionTestRoundsByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchTestRounds(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取更新日志", tags = {"系统日志" } ,notes = "根据项目获取更新日志")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchupdatelog")
	public ResponseEntity<List<ActionDTO>> fetchActionUpdateLogByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchUpdateLog(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目版本获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目版本获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本建立系统日志", tags = {"系统日志" },  notes = "根据项目版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取MobType", tags = {"系统日志" } ,notes = "根据项目版本获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取Type", tags = {"系统日志" } ,notes = "根据项目版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本更新系统日志", tags = {"系统日志" },  notes = "根据项目版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新系统日志", tags = {"系统日志" },  notes = "根据产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除系统日志", tags = {"系统日志" },  notes = "根据产品删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'COLLECT')")
    @ApiOperation(value = "根据产品收藏", tags = {"系统日志" },  notes = "根据产品收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"系统日志" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"系统日志" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbycustom")
	public ResponseEntity<List<ActionDTO>> fetchActionByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByCustom(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"系统日志" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"系统日志" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproductnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProductNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"系统日志" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyproject")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProject(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"系统日志" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchbyprojectnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProjectNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"系统日志" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchcurdoclib")
	public ResponseEntity<List<ActionDTO>> fetchActionCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurDocLib(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"系统日志" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmyfavourites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"系统日志" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchrootmodulemulu")
	public ResponseEntity<List<ActionDTO>> fetchActionRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootModuleMuLu(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立系统日志", tags = {"系统日志" },  notes = "根据产品文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新系统日志", tags = {"系统日志" },  notes = "根据产品文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库删除系统日志", tags = {"系统日志" },  notes = "根据产品文档库删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库根据版本更新正文信息", tags = {"系统日志" },  notes = "根据产品文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/byversionupdatecontext")
    public ResponseEntity<ActionDTO> byVersionUpdateContextByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.byVersionUpdateContext(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库检查系统日志", tags = {"系统日志" },  notes = "根据产品文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"系统日志" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库行为", tags = {"系统日志" },  notes = "根据产品文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/getdocstatus")
    public ResponseEntity<ActionDTO> getDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getDocStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅收藏文档", tags = {"系统日志" },  notes = "根据产品文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/onlycollectdoc")
    public ResponseEntity<ActionDTO> onlyCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅取消收藏文档", tags = {"系统日志" },  notes = "根据产品文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/onlyuncollectdoc")
    public ResponseEntity<ActionDTO> onlyUnCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyUnCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库保存系统日志", tags = {"系统日志" },  notes = "根据产品文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"系统日志" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档（子库）", tags = {"系统日志" } ,notes = "根据产品文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchchilddoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdoclibanddoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibAndDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类文档", tags = {"系统日志" } ,notes = "根据产品文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdocmoduledoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocModuleDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档统计", tags = {"系统日志" } ,notes = "根据产品文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchdocstatus")
	public ResponseEntity<List<ActionDTO>> fetchActionDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocStatus(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文件夹文档（子目录）", tags = {"系统日志" } ,notes = "根据产品文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmoduledocchild")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleDocChild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmyfavourite")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavouritesOnlyDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取子目录文档", tags = {"系统日志" } ,notes = "根据产品文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchnotrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取根目录文档", tags = {"系统日志" } ,notes = "根据产品文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取MobType", tags = {"系统日志" } ,notes = "根据产品文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取Type", tags = {"系统日志" } ,notes = "根据产品文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库文档更新系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新系统日志", tags = {"系统日志" },  notes = "根据项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除系统日志", tags = {"系统日志" },  notes = "根据项目删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'COLLECT')")
    @ApiOperation(value = "根据项目收藏", tags = {"系统日志" },  notes = "根据项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"系统日志" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"系统日志" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbycustom")
	public ResponseEntity<List<ActionDTO>> fetchActionByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByCustom(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"系统日志" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproduct")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProduct(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"系统日志" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproductnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProductNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"系统日志" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyproject")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProject(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"系统日志" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchbyprojectnotfiles")
	public ResponseEntity<List<ActionDTO>> fetchActionByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchByProjectNotFiles(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"系统日志" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchcurdoclib")
	public ResponseEntity<List<ActionDTO>> fetchActionCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchCurDocLib(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"系统日志" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmyfavourites")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourites(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"系统日志" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchrootmodulemulu")
	public ResponseEntity<List<ActionDTO>> fetchActionRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootModuleMuLu(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立系统日志", tags = {"系统日志" },  notes = "根据项目文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新系统日志", tags = {"系统日志" },  notes = "根据项目文档库更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库删除系统日志", tags = {"系统日志" },  notes = "根据项目文档库删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库根据版本更新正文信息", tags = {"系统日志" },  notes = "根据项目文档库根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/byversionupdatecontext")
    public ResponseEntity<ActionDTO> byVersionUpdateContextByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.byVersionUpdateContext(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库检查系统日志", tags = {"系统日志" },  notes = "根据项目文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"系统日志" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/collect")
    public ResponseEntity<ActionDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.collect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库行为", tags = {"系统日志" },  notes = "根据项目文档库行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/getdocstatus")
    public ResponseEntity<ActionDTO> getDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.getDocStatus(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅收藏文档", tags = {"系统日志" },  notes = "根据项目文档库仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/onlycollectdoc")
    public ResponseEntity<ActionDTO> onlyCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅取消收藏文档", tags = {"系统日志" },  notes = "根据项目文档库仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/onlyuncollectdoc")
    public ResponseEntity<ActionDTO> onlyUnCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.onlyUnCollectDoc(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库保存系统日志", tags = {"系统日志" },  notes = "根据项目文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"系统日志" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/uncollect")
    public ResponseEntity<ActionDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
        domain = actionService.unCollect(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档（子库）", tags = {"系统日志" } ,notes = "根据项目文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchchilddoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchChildDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdoclibanddoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibAndDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"系统日志" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdoclibdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocLibDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库分类文档", tags = {"系统日志" } ,notes = "根据项目文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdocmoduledoc")
	public ResponseEntity<List<ActionDTO>> fetchActionDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocModuleDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档统计", tags = {"系统日志" } ,notes = "根据项目文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchdocstatus")
	public ResponseEntity<List<ActionDTO>> fetchActionDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchDocStatus(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文件夹文档（子目录）", tags = {"系统日志" } ,notes = "根据项目文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmoduledocchild")
	public ResponseEntity<List<ActionDTO>> fetchActionModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchModuleDocChild(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmyfavourite")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavourite(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"系统日志" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<ActionDTO>> fetchActionMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyFavouritesOnlyDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取子目录文档", tags = {"系统日志" } ,notes = "根据项目文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchnotrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchNotRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取根目录文档", tags = {"系统日志" } ,notes = "根据项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchrootdoc")
	public ResponseEntity<List<ActionDTO>> fetchActionRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchRootDoc(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取DEFAULT", tags = {"系统日志" } ,notes = "根据项目文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取查询用户使用年", tags = {"系统日志" } ,notes = "根据项目文档库文档获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取MobType", tags = {"系统日志" } ,notes = "根据项目文档库文档获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取Type", tags = {"系统日志" } ,notes = "根据项目文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库文档更新系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

}

