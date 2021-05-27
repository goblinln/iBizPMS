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


    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据Bug删除系统日志", tags = {"系统日志" },  notes = "根据Bug删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据Bug获取系统日志", tags = {"系统日志" },  notes = "根据Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug检查系统日志", tags = {"系统日志" },  notes = "根据Bug检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'MANAGE')")
    @ApiOperation(value = "根据Bug添加备注系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug创建历史日志系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'MANAGE')")
    @ApiOperation(value = "根据Bug编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据BugPms企业专用系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据Bug保存系统日志", tags = {"系统日志" },  notes = "根据Bug保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据Bug已读系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据Bug发送待办系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据Bug发送待阅系统日志", tags = {"系统日志" },  notes = "根据Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
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
	@ApiOperation(value = "根据Bug查询DEFAULT", tags = {"系统日志" } ,notes = "根据Bug查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据Bug查询MobType", tags = {"系统日志" } ,notes = "根据Bug查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据Bug查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据Bug查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据Bug查询ProductTrends", tags = {"系统日志" } ,notes = "根据Bug查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据Bug查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据Bug查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug查询查询用户使用年", tags = {"系统日志" } ,notes = "根据Bug查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug查询Type", tags = {"系统日志" } ,notes = "根据Bug查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByBug(@PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查系统日志", tags = {"系统日志" },  notes = "根据产品检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品添加备注系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品保存系统日志", tags = {"系统日志" },  notes = "根据产品保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品已读系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发送待办系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品查询MobType", tags = {"系统日志" } ,notes = "根据产品查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询Type", tags = {"系统日志" } ,notes = "根据产品查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划删除系统日志", tags = {"系统日志" },  notes = "根据产品计划删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划检查系统日志", tags = {"系统日志" },  notes = "根据产品计划检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'MANAGE')")
    @ApiOperation(value = "根据产品计划添加备注系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'MANAGE')")
    @ApiOperation(value = "根据产品计划编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品计划Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品计划保存系统日志", tags = {"系统日志" },  notes = "根据产品计划保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品计划已读系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品计划发送待办系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品计划发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductPlan(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品计划查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品计划查询MobType", tags = {"系统日志" } ,notes = "根据产品计划查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品计划查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品计划查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品计划查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品计划查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品计划查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品计划查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品计划查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划查询Type", tags = {"系统日志" } ,notes = "根据产品计划查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductPlan(@PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@TodoRuntime.test(#todo_id,'DELETE')")
    @ApiOperation(value = "根据待办删除系统日志", tags = {"系统日志" },  notes = "根据待办删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
    @ApiOperation(value = "根据待办获取系统日志", tags = {"系统日志" },  notes = "根据待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办检查系统日志", tags = {"系统日志" },  notes = "根据待办检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'MANAGE')")
    @ApiOperation(value = "根据待办添加备注系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办创建历史日志系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'MANAGE')")
    @ApiOperation(value = "根据待办编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据待办Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据待办保存系统日志", tags = {"系统日志" },  notes = "根据待办保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据待办已读系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据待办发送待办系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据待办发送待阅系统日志", tags = {"系统日志" },  notes = "根据待办系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据待办查询DEFAULT", tags = {"系统日志" } ,notes = "根据待办查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据待办查询MobType", tags = {"系统日志" } ,notes = "根据待办查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据待办查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据待办查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据待办查询ProductTrends", tags = {"系统日志" } ,notes = "根据待办查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据待办查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据待办查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办查询查询用户使用年", tags = {"系统日志" } ,notes = "根据待办查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办查询Type", tags = {"系统日志" } ,notes = "根据待办查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务删除系统日志", tags = {"系统日志" },  notes = "根据任务删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取系统日志", tags = {"系统日志" },  notes = "根据任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务检查系统日志", tags = {"系统日志" },  notes = "根据任务检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'MANAGE')")
    @ApiOperation(value = "根据任务添加备注系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务创建历史日志系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'MANAGE')")
    @ApiOperation(value = "根据任务编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据任务Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据任务保存系统日志", tags = {"系统日志" },  notes = "根据任务保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据任务已读系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据任务发送待办系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据任务发送待阅系统日志", tags = {"系统日志" },  notes = "根据任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByTask(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据任务查询DEFAULT", tags = {"系统日志" } ,notes = "根据任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据任务查询MobType", tags = {"系统日志" } ,notes = "根据任务查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据任务查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据任务查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据任务查询ProductTrends", tags = {"系统日志" } ,notes = "根据任务查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据任务查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据任务查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询查询用户使用年", tags = {"系统日志" } ,notes = "根据任务查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询Type", tags = {"系统日志" } ,notes = "根据任务查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByTask(@PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "根据发布删除系统日志", tags = {"系统日志" },  notes = "根据发布删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据发布获取系统日志", tags = {"系统日志" },  notes = "根据发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布检查系统日志", tags = {"系统日志" },  notes = "根据发布检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'MANAGE')")
    @ApiOperation(value = "根据发布添加备注系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布创建历史日志系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'MANAGE')")
    @ApiOperation(value = "根据发布编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据发布Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据发布保存系统日志", tags = {"系统日志" },  notes = "根据发布保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据发布已读系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据发布发送待办系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据发布发送待阅系统日志", tags = {"系统日志" },  notes = "根据发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByRelease(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据发布查询DEFAULT", tags = {"系统日志" } ,notes = "根据发布查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据发布查询MobType", tags = {"系统日志" } ,notes = "根据发布查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据发布查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据发布查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据发布查询ProductTrends", tags = {"系统日志" } ,notes = "根据发布查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据发布查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据发布查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布查询查询用户使用年", tags = {"系统日志" } ,notes = "根据发布查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布查询Type", tags = {"系统日志" } ,notes = "根据发布查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByRelease(@PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'DELETE')")
    @ApiOperation(value = "根据周报删除系统日志", tags = {"系统日志" },  notes = "根据周报删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
    @ApiOperation(value = "根据周报获取系统日志", tags = {"系统日志" },  notes = "根据周报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报检查系统日志", tags = {"系统日志" },  notes = "根据周报检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'MANAGE')")
    @ApiOperation(value = "根据周报添加备注系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报创建历史日志系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'MANAGE')")
    @ApiOperation(value = "根据周报编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据周报Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据周报保存系统日志", tags = {"系统日志" },  notes = "根据周报保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据周报已读系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据周报发送待办系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据周报发送待阅系统日志", tags = {"系统日志" },  notes = "根据周报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据周报查询DEFAULT", tags = {"系统日志" } ,notes = "根据周报查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据周报查询MobType", tags = {"系统日志" } ,notes = "根据周报查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据周报查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据周报查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据周报查询ProductTrends", tags = {"系统日志" } ,notes = "根据周报查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据周报查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据周报查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报查询查询用户使用年", tags = {"系统日志" } ,notes = "根据周报查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报查询Type", tags = {"系统日志" } ,notes = "根据周报查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'DELETE')")
    @ApiOperation(value = "根据测试报告删除系统日志", tags = {"系统日志" },  notes = "根据测试报告删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
    @ApiOperation(value = "根据测试报告获取系统日志", tags = {"系统日志" },  notes = "根据测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告检查系统日志", tags = {"系统日志" },  notes = "根据测试报告检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'MANAGE')")
    @ApiOperation(value = "根据测试报告添加备注系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告创建历史日志系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'MANAGE')")
    @ApiOperation(value = "根据测试报告编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试报告Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试报告保存系统日志", tags = {"系统日志" },  notes = "根据测试报告保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据测试报告已读系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试报告发送待办系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试报告发送待阅系统日志", tags = {"系统日志" },  notes = "根据测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据测试报告查询DEFAULT", tags = {"系统日志" } ,notes = "根据测试报告查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试报告查询MobType", tags = {"系统日志" } ,notes = "根据测试报告查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试报告查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试报告查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试报告查询ProductTrends", tags = {"系统日志" } ,notes = "根据测试报告查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试报告查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试报告查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告查询查询用户使用年", tags = {"系统日志" } ,notes = "根据测试报告查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告查询Type", tags = {"系统日志" } ,notes = "根据测试报告查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库检查系统日志", tags = {"系统日志" },  notes = "根据文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'MANAGE')")
    @ApiOperation(value = "根据文档库添加备注系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库创建历史日志系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'MANAGE')")
    @ApiOperation(value = "根据文档库编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库保存系统日志", tags = {"系统日志" },  notes = "根据文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据文档库已读系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库发送待办系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库发送待阅系统日志", tags = {"系统日志" },  notes = "根据文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据文档库查询DEFAULT", tags = {"系统日志" } ,notes = "根据文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库查询MobType", tags = {"系统日志" } ,notes = "根据文档库查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库查询ProductTrends", tags = {"系统日志" } ,notes = "根据文档库查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询查询用户使用年", tags = {"系统日志" } ,notes = "根据文档库查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询Type", tags = {"系统日志" } ,notes = "根据文档库查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@DocRuntime.test(#doc_id,'DELETE')")
    @ApiOperation(value = "根据文档删除系统日志", tags = {"系统日志" },  notes = "根据文档删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据文档获取系统日志", tags = {"系统日志" },  notes = "根据文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档检查系统日志", tags = {"系统日志" },  notes = "根据文档检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'MANAGE')")
    @ApiOperation(value = "根据文档添加备注系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档创建历史日志系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'MANAGE')")
    @ApiOperation(value = "根据文档编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档保存系统日志", tags = {"系统日志" },  notes = "根据文档保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据文档已读系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档发送待办系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档发送待阅系统日志", tags = {"系统日志" },  notes = "根据文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据文档查询DEFAULT", tags = {"系统日志" } ,notes = "根据文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档查询MobType", tags = {"系统日志" } ,notes = "根据文档查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档查询ProductTrends", tags = {"系统日志" } ,notes = "根据文档查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档查询查询用户使用年", tags = {"系统日志" } ,notes = "根据文档查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档查询Type", tags = {"系统日志" } ,notes = "根据文档查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'DELETE')")
    @ApiOperation(value = "根据日报删除系统日志", tags = {"系统日志" },  notes = "根据日报删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
    @ApiOperation(value = "根据日报获取系统日志", tags = {"系统日志" },  notes = "根据日报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报检查系统日志", tags = {"系统日志" },  notes = "根据日报检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'MANAGE')")
    @ApiOperation(value = "根据日报添加备注系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报创建历史日志系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'MANAGE')")
    @ApiOperation(value = "根据日报编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据日报Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据日报保存系统日志", tags = {"系统日志" },  notes = "根据日报保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据日报已读系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据日报发送待办系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据日报发送待阅系统日志", tags = {"系统日志" },  notes = "根据日报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据日报查询DEFAULT", tags = {"系统日志" } ,notes = "根据日报查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据日报查询MobType", tags = {"系统日志" } ,notes = "根据日报查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据日报查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据日报查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据日报查询ProductTrends", tags = {"系统日志" } ,notes = "根据日报查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据日报查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据日报查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报查询查询用户使用年", tags = {"系统日志" } ,notes = "根据日报查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报查询Type", tags = {"系统日志" } ,notes = "根据日报查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'DELETE')")
    @ApiOperation(value = "根据月报删除系统日志", tags = {"系统日志" },  notes = "根据月报删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
    @ApiOperation(value = "根据月报获取系统日志", tags = {"系统日志" },  notes = "根据月报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报检查系统日志", tags = {"系统日志" },  notes = "根据月报检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'MANAGE')")
    @ApiOperation(value = "根据月报添加备注系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报创建历史日志系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'MANAGE')")
    @ApiOperation(value = "根据月报编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据月报Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据月报保存系统日志", tags = {"系统日志" },  notes = "根据月报保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据月报已读系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据月报发送待办系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据月报发送待阅系统日志", tags = {"系统日志" },  notes = "根据月报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据月报查询DEFAULT", tags = {"系统日志" } ,notes = "根据月报查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据月报查询MobType", tags = {"系统日志" } ,notes = "根据月报查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据月报查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据月报查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据月报查询ProductTrends", tags = {"系统日志" } ,notes = "根据月报查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据月报查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据月报查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报查询查询用户使用年", tags = {"系统日志" } ,notes = "根据月报查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报查询Type", tags = {"系统日志" } ,notes = "根据月报查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报删除系统日志", tags = {"系统日志" },  notes = "根据汇报删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报获取系统日志", tags = {"系统日志" },  notes = "根据汇报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报检查系统日志", tags = {"系统日志" },  notes = "根据汇报检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'MANAGE')")
    @ApiOperation(value = "根据汇报添加备注系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报创建历史日志系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'MANAGE')")
    @ApiOperation(value = "根据汇报编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据汇报Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据汇报保存系统日志", tags = {"系统日志" },  notes = "根据汇报保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据汇报已读系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据汇报发送待办系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据汇报发送待阅系统日志", tags = {"系统日志" },  notes = "根据汇报系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据汇报查询DEFAULT", tags = {"系统日志" } ,notes = "根据汇报查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据汇报查询MobType", tags = {"系统日志" } ,notes = "根据汇报查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据汇报查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据汇报查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据汇报查询ProductTrends", tags = {"系统日志" } ,notes = "根据汇报查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据汇报查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据汇报查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报查询查询用户使用年", tags = {"系统日志" } ,notes = "根据汇报查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报查询Type", tags = {"系统日志" } ,notes = "根据汇报查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本删除系统日志", tags = {"系统日志" },  notes = "根据测试版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取系统日志", tags = {"系统日志" },  notes = "根据测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本检查系统日志", tags = {"系统日志" },  notes = "根据测试版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'MANAGE')")
    @ApiOperation(value = "根据测试版本添加备注系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'MANAGE')")
    @ApiOperation(value = "根据测试版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试版本保存系统日志", tags = {"系统日志" },  notes = "根据测试版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据测试版本已读系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试版本发送待办系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据测试版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试版本查询MobType", tags = {"系统日志" } ,notes = "根据测试版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据测试版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据测试版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本查询Type", tags = {"系统日志" } ,notes = "根据测试版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件建立系统日志", tags = {"系统日志" },  notes = "根据测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件更新系统日志", tags = {"系统日志" },  notes = "根据测试套件更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件删除系统日志", tags = {"系统日志" },  notes = "根据测试套件删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件检查系统日志", tags = {"系统日志" },  notes = "根据测试套件检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'MANAGE')")
    @ApiOperation(value = "根据测试套件添加备注系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件创建历史日志系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'MANAGE')")
    @ApiOperation(value = "根据测试套件编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试套件Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试套件保存系统日志", tags = {"系统日志" },  notes = "根据测试套件保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据测试套件已读系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试套件发送待办系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试套件发送待阅系统日志", tags = {"系统日志" },  notes = "根据测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取DEFAULT", tags = {"系统日志" } ,notes = "根据测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询DEFAULT", tags = {"系统日志" } ,notes = "根据测试套件查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取MobType", tags = {"系统日志" } ,notes = "根据测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询MobType", tags = {"系统日志" } ,notes = "根据测试套件查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试套件查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询ProductTrends", tags = {"系统日志" } ,notes = "根据测试套件查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试套件查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取查询用户使用年", tags = {"系统日志" } ,notes = "根据测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询查询用户使用年", tags = {"系统日志" } ,notes = "根据测试套件查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取Type", tags = {"系统日志" } ,notes = "根据测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询Type", tags = {"系统日志" } ,notes = "根据测试套件查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例删除系统日志", tags = {"系统日志" },  notes = "根据测试用例删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取系统日志", tags = {"系统日志" },  notes = "根据测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例检查系统日志", tags = {"系统日志" },  notes = "根据测试用例检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'MANAGE')")
    @ApiOperation(value = "根据测试用例添加备注系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例创建历史日志系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'MANAGE')")
    @ApiOperation(value = "根据测试用例编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试用例Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试用例保存系统日志", tags = {"系统日志" },  notes = "根据测试用例保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据测试用例已读系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试用例发送待办系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据测试用例发送待阅系统日志", tags = {"系统日志" },  notes = "根据测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByCase(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据测试用例查询DEFAULT", tags = {"系统日志" } ,notes = "根据测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试用例查询MobType", tags = {"系统日志" } ,notes = "根据测试用例查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试用例查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试用例查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试用例查询ProductTrends", tags = {"系统日志" } ,notes = "根据测试用例查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据测试用例查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试用例查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询查询用户使用年", tags = {"系统日志" } ,notes = "根据测试用例查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询Type", tags = {"系统日志" } ,notes = "根据测试用例查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByCase(@PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本删除系统日志", tags = {"系统日志" },  notes = "根据版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本获取系统日志", tags = {"系统日志" },  notes = "根据版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本检查系统日志", tags = {"系统日志" },  notes = "根据版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'MANAGE')")
    @ApiOperation(value = "根据版本添加备注系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'MANAGE')")
    @ApiOperation(value = "根据版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据版本保存系统日志", tags = {"系统日志" },  notes = "根据版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据版本已读系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据版本发送待办系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByBuild(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据版本查询MobType", tags = {"系统日志" } ,notes = "根据版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本查询Type", tags = {"系统日志" } ,notes = "根据版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByBuild(@PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'DELETE')")
    @ApiOperation(value = "根据用例库删除系统日志", tags = {"系统日志" },  notes = "根据用例库删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
    @ApiOperation(value = "根据用例库获取系统日志", tags = {"系统日志" },  notes = "根据用例库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库检查系统日志", tags = {"系统日志" },  notes = "根据用例库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'MANAGE')")
    @ApiOperation(value = "根据用例库添加备注系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库创建历史日志系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'MANAGE')")
    @ApiOperation(value = "根据用例库编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据用例库Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据用例库保存系统日志", tags = {"系统日志" },  notes = "根据用例库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据用例库已读系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据用例库发送待办系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据用例库发送待阅系统日志", tags = {"系统日志" },  notes = "根据用例库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzlib_id);domain.setObjecttype("caselib");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据用例库查询DEFAULT", tags = {"系统日志" } ,notes = "根据用例库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据用例库查询MobType", tags = {"系统日志" } ,notes = "根据用例库查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据用例库查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据用例库查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据用例库查询ProductTrends", tags = {"系统日志" } ,notes = "根据用例库查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据用例库查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据用例库查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库查询查询用户使用年", tags = {"系统日志" } ,notes = "根据用例库查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库查询Type", tags = {"系统日志" } ,notes = "根据用例库查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzlib_id);context.setN_objecttype_eq("caselib");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求删除系统日志", tags = {"系统日志" },  notes = "根据需求删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求获取系统日志", tags = {"系统日志" },  notes = "根据需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求检查系统日志", tags = {"系统日志" },  notes = "根据需求检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'MANAGE')")
    @ApiOperation(value = "根据需求添加备注系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求创建历史日志系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'MANAGE')")
    @ApiOperation(value = "根据需求编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据需求Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据需求保存系统日志", tags = {"系统日志" },  notes = "根据需求保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据需求已读系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据需求发送待办系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据需求发送待阅系统日志", tags = {"系统日志" },  notes = "根据需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByStory(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据需求查询DEFAULT", tags = {"系统日志" } ,notes = "根据需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据需求查询MobType", tags = {"系统日志" } ,notes = "根据需求查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据需求查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据需求查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据需求查询ProductTrends", tags = {"系统日志" } ,notes = "根据需求查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据需求查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据需求查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询查询用户使用年", tags = {"系统日志" } ,notes = "根据需求查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求查询Type", tags = {"系统日志" } ,notes = "根据需求查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByStory(@PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查系统日志", tags = {"系统日志" },  notes = "根据项目检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目添加备注系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目保存系统日志", tags = {"系统日志" },  notes = "根据项目保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目已读系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目发送待办系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目查询MobType", tags = {"系统日志" } ,notes = "根据项目查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询Type", tags = {"系统日志" } ,notes = "根据项目查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库检查系统日志", tags = {"系统日志" },  notes = "根据产品文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库添加备注系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库保存系统日志", tags = {"系统日志" },  notes = "根据产品文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品文档库已读系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库发送待办系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品文档库查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库查询MobType", tags = {"系统日志" } ,notes = "根据产品文档库查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品文档库查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询Type", tags = {"系统日志" } ,notes = "根据产品文档库查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品Bug删除系统日志", tags = {"系统日志" },  notes = "根据产品Bug删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品Bug检查系统日志", tags = {"系统日志" },  notes = "根据产品Bug检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品Bug添加备注系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品Bug编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品BugPms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品Bug保存系统日志", tags = {"系统日志" },  notes = "根据产品Bug保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品Bug已读系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品Bug发送待办系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品Bug发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品Bug系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品Bug查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品Bug查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品Bug查询MobType", tags = {"系统日志" } ,notes = "根据产品Bug查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品Bug查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品Bug查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品Bug查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品Bug查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品Bug查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品Bug查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品Bug查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug查询Type", tags = {"系统日志" } ,notes = "根据产品Bug查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品发布删除系统日志", tags = {"系统日志" },  notes = "根据产品发布删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品发布获取系统日志", tags = {"系统日志" },  notes = "根据产品发布获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品发布检查系统日志", tags = {"系统日志" },  notes = "根据产品发布检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发布添加备注系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品发布编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发布Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发布保存系统日志", tags = {"系统日志" },  notes = "根据产品发布保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品发布已读系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发布发送待办系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品发布发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品发布系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(release_id);domain.setObjecttype("release");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品发布查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品发布查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品发布查询MobType", tags = {"系统日志" } ,notes = "根据产品发布查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品发布查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品发布查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品发布查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品发布查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品发布查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品发布查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品发布查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布查询Type", tags = {"系统日志" } ,notes = "根据产品发布查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(release_id);context.setN_objecttype_eq("release");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件建立系统日志", tags = {"系统日志" },  notes = "根据产品测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件更新系统日志", tags = {"系统日志" },  notes = "根据产品测试套件更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件删除系统日志", tags = {"系统日志" },  notes = "根据产品测试套件删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件获取系统日志", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件检查系统日志", tags = {"系统日志" },  notes = "根据产品测试套件检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试套件添加备注系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试套件编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试套件Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试套件保存系统日志", tags = {"系统日志" },  notes = "根据产品测试套件保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品测试套件已读系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试套件发送待办系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试套件发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品测试套件系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("TestSuite");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试套件获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchActionDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试套件查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取MobType", tags = {"系统日志" } ,notes = "根据产品测试套件获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchActionMobTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询MobType", tags = {"系统日志" } ,notes = "根据产品测试套件查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchActionMyTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试套件查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProductTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试套件查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchActionProjectTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试套件查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试套件获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchActionQueryUserYEARByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试套件查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取Type", tags = {"系统日志" } ,notes = "根据产品测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchActionTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询Type", tags = {"系统日志" } ,notes = "根据产品测试套件查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("TestSuite");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除系统日志", tags = {"系统日志" },  notes = "根据产品测试版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取系统日志", tags = {"系统日志" },  notes = "根据产品测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本检查系统日志", tags = {"系统日志" },  notes = "根据产品测试版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试版本添加备注系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试版本保存系统日志", tags = {"系统日志" },  notes = "根据产品测试版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品测试版本已读系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试版本发送待办系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品测试版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试版本查询MobType", tags = {"系统日志" } ,notes = "根据产品测试版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本查询Type", tags = {"系统日志" } ,notes = "根据产品测试版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试报告删除系统日志", tags = {"系统日志" },  notes = "根据产品测试报告删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品测试报告检查系统日志", tags = {"系统日志" },  notes = "根据产品测试报告检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试报告添加备注系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试报告编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试报告Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试报告保存系统日志", tags = {"系统日志" },  notes = "根据产品测试报告保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品测试报告已读系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试报告发送待办系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试报告发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品测试报告查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试报告查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试报告查询MobType", tags = {"系统日志" } ,notes = "根据产品测试报告查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试报告查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试报告查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试报告查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试报告查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试报告查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试报告查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试报告查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告查询Type", tags = {"系统日志" } ,notes = "根据产品测试报告查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本删除系统日志", tags = {"系统日志" },  notes = "根据产品版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品版本检查系统日志", tags = {"系统日志" },  notes = "根据产品版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本添加备注系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品版本保存系统日志", tags = {"系统日志" },  notes = "根据产品版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品版本已读系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品版本发送待办系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品版本查询MobType", tags = {"系统日志" } ,notes = "根据产品版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本查询Type", tags = {"系统日志" } ,notes = "根据产品版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除系统日志", tags = {"系统日志" },  notes = "根据产品测试用例删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取系统日志", tags = {"系统日志" },  notes = "根据产品测试用例获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品测试用例检查系统日志", tags = {"系统日志" },  notes = "根据产品测试用例检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试用例添加备注系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试用例编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试用例Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试用例保存系统日志", tags = {"系统日志" },  notes = "根据产品测试用例保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品测试用例已读系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试用例发送待办系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品测试用例发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品测试用例系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(case_id);domain.setObjecttype("case");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品测试用例查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试用例查询MobType", tags = {"系统日志" } ,notes = "根据产品测试用例查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试用例查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试用例查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试用例查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试用例查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品测试用例查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试用例查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品测试用例查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询Type", tags = {"系统日志" } ,notes = "根据产品测试用例查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(case_id);context.setN_objecttype_eq("case");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除系统日志", tags = {"系统日志" },  notes = "根据产品产品计划删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据产品产品计划检查系统日志", tags = {"系统日志" },  notes = "根据产品产品计划检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品计划添加备注系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品计划编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品产品计划Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品产品计划保存系统日志", tags = {"系统日志" },  notes = "根据产品产品计划保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品产品计划已读系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品产品计划发送待办系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品产品计划发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品产品计划系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品产品计划查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品产品计划查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品产品计划查询MobType", tags = {"系统日志" } ,notes = "根据产品产品计划查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品产品计划查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品产品计划查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品产品计划查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品产品计划查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品产品计划查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品产品计划查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品产品计划查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划查询Type", tags = {"系统日志" } ,notes = "根据产品产品计划查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求删除系统日志", tags = {"系统日志" },  notes = "根据产品需求删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求检查系统日志", tags = {"系统日志" },  notes = "根据产品需求检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求添加备注系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品需求Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品需求保存系统日志", tags = {"系统日志" },  notes = "根据产品需求保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品需求已读系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品需求发送待办系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品需求发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品需求系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品需求查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品需求查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品需求查询MobType", tags = {"系统日志" } ,notes = "根据产品需求查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品需求查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品需求查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品需求查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品需求查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品需求查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品需求查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品需求查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求查询Type", tags = {"系统日志" } ,notes = "根据产品需求查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库文档删除系统日志", tags = {"系统日志" },  notes = "根据文档库文档删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档检查系统日志", tags = {"系统日志" },  notes = "根据文档库文档检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'MANAGE')")
    @ApiOperation(value = "根据文档库文档添加备注系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档创建历史日志系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'MANAGE')")
    @ApiOperation(value = "根据文档库文档编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库文档Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库文档保存系统日志", tags = {"系统日志" },  notes = "根据文档库文档保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据文档库文档已读系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库文档发送待办系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据文档库文档发送待阅系统日志", tags = {"系统日志" },  notes = "根据文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据文档库文档查询DEFAULT", tags = {"系统日志" } ,notes = "根据文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库文档查询MobType", tags = {"系统日志" } ,notes = "根据文档库文档查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库文档查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库文档查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库文档查询ProductTrends", tags = {"系统日志" } ,notes = "根据文档库文档查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据文档库文档查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库文档查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档查询查询用户使用年", tags = {"系统日志" } ,notes = "根据文档库文档查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档查询Type", tags = {"系统日志" } ,notes = "根据文档库文档查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库检查系统日志", tags = {"系统日志" },  notes = "根据项目文档库检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目文档库添加备注系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目文档库编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库保存系统日志", tags = {"系统日志" },  notes = "根据项目文档库保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目文档库已读系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库发送待办系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目文档库系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目文档库查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库查询MobType", tags = {"系统日志" } ,notes = "根据项目文档库查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目文档库查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询Type", tags = {"系统日志" } ,notes = "根据项目文档库查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除系统日志", tags = {"系统日志" },  notes = "根据项目任务删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务检查系统日志", tags = {"系统日志" },  notes = "根据项目任务检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目任务添加备注系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目任务编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务保存系统日志", tags = {"系统日志" },  notes = "根据项目任务保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目任务已读系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务发送待办系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目任务发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目任务系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目任务查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目任务查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目任务查询MobType", tags = {"系统日志" } ,notes = "根据项目任务查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目任务查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目任务查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目任务查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目任务查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目任务查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目任务查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目任务查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询Type", tags = {"系统日志" } ,notes = "根据项目任务查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试报告删除系统日志", tags = {"系统日志" },  notes = "根据项目测试报告删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据项目测试报告检查系统日志", tags = {"系统日志" },  notes = "根据项目测试报告检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目测试报告添加备注系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目测试报告编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试报告Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试报告保存系统日志", tags = {"系统日志" },  notes = "根据项目测试报告保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目测试报告已读系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试报告发送待办系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试报告发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目测试报告系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目测试报告查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目测试报告查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试报告查询MobType", tags = {"系统日志" } ,notes = "根据项目测试报告查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试报告查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试报告查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试报告查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试报告查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试报告查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试报告查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目测试报告查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告查询Type", tags = {"系统日志" } ,notes = "根据项目测试报告查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除系统日志", tags = {"系统日志" },  notes = "根据项目测试版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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
    @ApiOperation(value = "根据项目测试版本检查系统日志", tags = {"系统日志" },  notes = "根据项目测试版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目测试版本添加备注系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目测试版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试版本保存系统日志", tags = {"系统日志" },  notes = "根据项目测试版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目测试版本已读系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试版本发送待办系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目测试版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目测试版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目测试版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目测试版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试版本查询MobType", tags = {"系统日志" } ,notes = "根据项目测试版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目测试版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目测试版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本查询Type", tags = {"系统日志" } ,notes = "根据项目测试版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本删除系统日志", tags = {"系统日志" },  notes = "根据项目版本删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本检查系统日志", tags = {"系统日志" },  notes = "根据项目版本检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目版本添加备注系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目版本编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目版本Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目版本保存系统日志", tags = {"系统日志" },  notes = "根据项目版本保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目版本已读系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目版本发送待办系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目版本发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目版本系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目版本查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目版本查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目版本查询MobType", tags = {"系统日志" } ,notes = "根据项目版本查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目版本查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目版本查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目版本查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目版本查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目版本查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目版本查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目版本查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本查询Type", tags = {"系统日志" } ,notes = "根据项目版本查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库文档删除系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档检查系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库文档添加备注系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档创建历史日志系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库文档编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库文档Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库文档保存系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品文档库文档已读系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库文档发送待办系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据产品文档库文档发送待阅系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据产品文档库文档查询DEFAULT", tags = {"系统日志" } ,notes = "根据产品文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库文档查询MobType", tags = {"系统日志" } ,notes = "根据产品文档库文档查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库文档查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库文档查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库文档查询ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库文档查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据产品文档库文档查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库文档查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档查询查询用户使用年", tags = {"系统日志" } ,notes = "根据产品文档库文档查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档查询Type", tags = {"系统日志" } ,notes = "根据产品文档库文档查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库文档删除系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
		return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档检查系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目文档库文档添加备注系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> commentByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.comment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档创建历史日志系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHisByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.createHis(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目文档库文档编辑备注信息系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editCommentByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.editComment(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库文档Pms企业专用系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库文档保存系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/save")
    public ResponseEntity<ActionDTO> saveByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        actionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(domain));
    }


    @ApiOperation(value = "根据项目文档库文档已读系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDoneByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库文档发送待办系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodoByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendTodo(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @ApiOperation(value = "根据项目文档库文档发送待阅系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToreadByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
        domain = actionService.sendToread(domain) ;
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
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
	@ApiOperation(value = "根据项目文档库文档查询DEFAULT", tags = {"系统日志" } ,notes = "根据项目文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchActionDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库文档查询MobType", tags = {"系统日志" } ,notes = "根据项目文档库文档查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchActionMobTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库文档查询项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库文档查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchActionMyTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库文档查询ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库文档查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProductTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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
	@ApiOperation(value = "根据项目文档库文档查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库文档查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchActionProjectTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档查询查询用户使用年", tags = {"系统日志" } ,notes = "根据项目文档库文档查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchActionQueryUserYEARByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
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

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档查询Type", tags = {"系统日志" } ,notes = "根据项目文档库文档查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchActionTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

