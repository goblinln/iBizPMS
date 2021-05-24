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

    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建系统日志", tags = {"系统日志" },  notes = "批量新建系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ActionDTO> actiondtos) {
        actionService.createBatch(actionMapping.toDomain(actiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

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

    @PreAuthorize("@ActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新系统日志", tags = {"系统日志" },  notes = "批量更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/actions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ActionDTO> actiondtos) {
        actionService.updateBatch(actionMapping.toDomain(actiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'DELETE')")
    @ApiOperation(value = "删除系统日志", tags = {"系统日志" },  notes = "删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/actions/{action_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("action_id") Long action_id) {
         return ResponseEntity.status(HttpStatus.OK).body(actionService.remove(action_id));
    }

    @PreAuthorize("@ActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除系统日志", tags = {"系统日志" },  notes = "批量删除系统日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/actions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        actionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @ApiOperation(value = "获取系统日志草稿", tags = {"系统日志" },  notes = "获取系统日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraft(ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查系统日志", tags = {"系统日志" },  notes = "检查系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ActionDTO actiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(actionService.checkKey(actionMapping.toDomain(actiondto)));
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"系统日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/comment")
    public ResponseEntity<ActionDTO> comment(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.comment(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"系统日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/createhis")
    public ResponseEntity<ActionDTO> createHis(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.createHis(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @PreAuthorize("@ActionRuntime.test('CREATE')")
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"系统日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"系统日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/editcomment")
    public ResponseEntity<ActionDTO> editComment(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.editComment(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @PreAuthorize("@ActionRuntime.test('MANAGE')")
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"系统日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"系统日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/managepmsee")
    public ResponseEntity<ActionDTO> managePmsEe(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.managePmsEe(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"系统日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存系统日志", tags = {"系统日志" },  notes = "保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/save")
    public ResponseEntity<ActionDTO> save(@RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        actionService.save(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存系统日志", tags = {"系统日志" },  notes = "批量保存系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ActionDTO> actiondtos) {
        actionService.saveBatch(actionMapping.toDomain(actiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"系统日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/sendmarkdone")
    public ResponseEntity<ActionDTO> sendMarkDone(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.sendMarkDone(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"系统日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"系统日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/sendtodo")
    public ResponseEntity<ActionDTO> sendTodo(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.sendTodo(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"系统日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"系统日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/sendtoread")
    public ResponseEntity<ActionDTO> sendToread(@PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setId(action_id);
        domain = actionService.sendToread(domain);
        actiondto = actionMapping.toDto(domain);
        Map<String,Integer> opprivs = actionRuntime.getOPPrivs(domain.getId());
        actiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"系统日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<ActionDTO> actiondtos) {
        List<Action> domains = actionMapping.toDomain(actiondtos);
        boolean result = actionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"系统日志" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchdefault")
	public ResponseEntity<List<ActionDTO>> fetchdefault(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchDefault(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"系统日志" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchdefault")
	public ResponseEntity<Page<ActionDTO>> searchDefault(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取MobType", tags = {"系统日志" } ,notes = "获取MobType")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchmobtype")
	public ResponseEntity<List<ActionDTO>> fetchmobtype(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchMobType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询MobType", tags = {"系统日志" } ,notes = "查询MobType")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchmobtype")
	public ResponseEntity<Page<ActionDTO>> searchMobType(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目动态(我的)", tags = {"系统日志" } ,notes = "获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchmytrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目动态(我的)", tags = {"系统日志" } ,notes = "查询项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchmytrends")
	public ResponseEntity<Page<ActionDTO>> searchMyTrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchMyTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ProductTrends", tags = {"系统日志" } ,notes = "获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchproducttrends")
	public ResponseEntity<List<ActionDTO>> fetchproducttrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询ProductTrends", tags = {"系统日志" } ,notes = "查询ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchproducttrends")
	public ResponseEntity<Page<ActionDTO>> searchProductTrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchprojecttrends")
	public ResponseEntity<List<ActionDTO>> fetchprojecttrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "查询项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchprojecttrends")
	public ResponseEntity<Page<ActionDTO>> searchProjectTrends(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取查询用户使用年", tags = {"系统日志" } ,notes = "获取查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchqueryuseryear")
	public ResponseEntity<List<ActionDTO>> fetchqueryuseryear(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询查询用户使用年", tags = {"系统日志" } ,notes = "查询查询用户使用年")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchqueryuseryear")
	public ResponseEntity<Page<ActionDTO>> searchQueryUserYEAR(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchQueryUserYEAR(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Type", tags = {"系统日志" } ,notes = "获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/actions/fetchtype")
	public ResponseEntity<List<ActionDTO>> fetchtype(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Type", tags = {"系统日志" } ,notes = "查询Type")
    @RequestMapping(method= RequestMethod.POST , value="/actions/searchtype")
	public ResponseEntity<Page<ActionDTO>> searchType(@RequestBody ActionSearchContext context) {
        actionRuntime.addAuthorityConditions(context,"READ");
        Page<Action> domains = actionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(actionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/{action}")
    public ResponseEntity<ActionDTO> dynamicCall(@PathVariable("action_id") Long action_id , @PathVariable("action") String action , @RequestBody ActionDTO actiondto) {
        Action domain = actionService.dynamicCall(action_id, action, actionMapping.toDomain(actiondto));
        actiondto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(actiondto);
    }
}

