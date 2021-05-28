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
    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取系统日志草稿", tags = {"系统日志" },  notes = "获取系统日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraft(ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/{action}")
    public ResponseEntity<ActionDTO> dynamicCall(@PathVariable("action_id") Long action_id , @PathVariable("action") String action , @RequestBody ActionDTO actiondto) {
        Action domain = actionService.dynamicCall(action_id, action, actionMapping.toDomain(actiondto));
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByBug(@PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据Bug获取系统日志", tags = {"系统日志" },  notes = "根据Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
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
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductPlan(@PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
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
    @ApiOperation(value = "根据产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办获取系统日志草稿", tags = {"系统日志" },  notes = "根据待办获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTodo(@PathVariable("todo_id") Long todo_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
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
    @ApiOperation(value = "根据待办获取系统日志", tags = {"系统日志" },  notes = "根据待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTask(@PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
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
    @ApiOperation(value = "根据任务获取系统日志", tags = {"系统日志" },  notes = "根据任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布获取系统日志草稿", tags = {"系统日志" },  notes = "根据发布获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByRelease(@PathVariable("release_id") Long release_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
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
    @ApiOperation(value = "根据发布获取系统日志", tags = {"系统日志" },  notes = "根据发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报获取系统日志草稿", tags = {"系统日志" },  notes = "根据周报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
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
    @ApiOperation(value = "根据周报获取系统日志", tags = {"系统日志" },  notes = "根据周报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestReport(@PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
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
    @ApiOperation(value = "根据测试报告获取系统日志", tags = {"系统日志" },  notes = "根据测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
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
    @ApiOperation(value = "根据文档库获取系统日志", tags = {"系统日志" },  notes = "根据文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
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
    @ApiOperation(value = "根据文档获取系统日志", tags = {"系统日志" },  notes = "根据文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报获取系统日志草稿", tags = {"系统日志" },  notes = "根据日报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
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
    @ApiOperation(value = "根据日报获取系统日志", tags = {"系统日志" },  notes = "根据日报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报获取系统日志草稿", tags = {"系统日志" },  notes = "根据月报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
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
    @ApiOperation(value = "根据月报获取系统日志", tags = {"系统日志" },  notes = "根据月报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报获取系统日志草稿", tags = {"系统日志" },  notes = "根据汇报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
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
    @ApiOperation(value = "根据汇报获取系统日志", tags = {"系统日志" },  notes = "根据汇报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
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
    @ApiOperation(value = "根据测试版本获取系统日志", tags = {"系统日志" },  notes = "根据测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
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
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试用例获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByCase(@PathVariable("case_id") Long case_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
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
    @ApiOperation(value = "根据测试用例获取系统日志", tags = {"系统日志" },  notes = "根据测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByBuild(@PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
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
    @ApiOperation(value = "根据版本获取系统日志", tags = {"系统日志" },  notes = "根据版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库获取系统日志草稿", tags = {"系统日志" },  notes = "根据用例库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
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
    @ApiOperation(value = "根据用例库获取系统日志", tags = {"系统日志" },  notes = "根据用例库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByStory(@PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
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
    @ApiOperation(value = "根据需求获取系统日志", tags = {"系统日志" },  notes = "根据需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
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
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品文档库获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品发布获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品发布获取系统日志", tags = {"系统日志" },  notes = "根据产品发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品测试套件获取系统日志", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品测试版本获取系统日志", tags = {"系统日志" },  notes = "根据产品测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试用例获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品测试用例获取系统日志", tags = {"系统日志" },  notes = "根据产品测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目文档库获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据产品文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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
    @ApiOperation(value = "根据项目文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
}

