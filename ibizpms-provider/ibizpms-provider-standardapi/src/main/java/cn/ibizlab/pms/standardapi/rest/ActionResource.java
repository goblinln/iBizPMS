package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ActionRuntime;

@Slf4j
@Api(tags = {"系统日志" })
@RestController("StandardAPI-action")
@RequestMapping("")
public class ActionResource {

    @Autowired
    public IActionService actionService;

    @Autowired
    public ActionRuntime actionRuntime;

    @Autowired
    @Lazy
    public ActionMapping actionMapping;


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户获取系统日志", tags = {"系统日志" },  notes = "根据系统用户获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户建立系统日志", tags = {"系统日志" },  notes = "根据系统用户建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取Type", tags = {"系统日志" } ,notes = "根据系统用户获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新系统日志", tags = {"系统日志" },  notes = "根据系统用户更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ActionSearchContext context) {
        
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取Type", tags = {"系统日志" } ,notes = "根据产品获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的数据", tags = {"系统日志" } ,notes = "根据产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取Type", tags = {"系统日志" } ,notes = "根据项目获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取我的数据", tags = {"系统日志" } ,notes = "根据项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取ProductTrends", tags = {"系统日志" } ,notes = "根据测试套件获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取指定用户数据", tags = {"系统日志" } ,notes = "根据测试套件获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据测试套件建立系统日志", tags = {"系统日志" },  notes = "根据测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取Type", tags = {"系统日志" } ,notes = "根据测试套件获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取我的数据", tags = {"系统日志" } ,notes = "根据测试套件获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);context.setN_objecttype_eq("testsuite");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户Bug获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户Bug获取系统日志", tags = {"系统日志" },  notes = "根据系统用户Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户Bug建立系统日志", tags = {"系统日志" },  notes = "根据系统用户Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取Type", tags = {"系统日志" } ,notes = "根据系统用户Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户Bug更新系统日志", tags = {"系统日志" },  notes = "根据系统用户Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户Bug获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户Bug获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户产品获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户产品获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品获取系统日志", tags = {"系统日志" },  notes = "根据系统用户产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品建立系统日志", tags = {"系统日志" },  notes = "根据系统用户产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取Type", tags = {"系统日志" } ,notes = "根据系统用户产品获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户产品获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品更新系统日志", tags = {"系统日志" },  notes = "根据系统用户产品更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(product_id);context.setN_objecttype_eq("product");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(project_id);context.setN_objecttype_eq("project");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户需求获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户需求获取系统日志", tags = {"系统日志" },  notes = "根据系统用户需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户需求建立系统日志", tags = {"系统日志" },  notes = "根据系统用户需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取Type", tags = {"系统日志" } ,notes = "根据系统用户需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户需求更新系统日志", tags = {"系统日志" },  notes = "根据系统用户需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户需求获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户需求获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户任务获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户任务获取系统日志", tags = {"系统日志" },  notes = "根据系统用户任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户任务建立系统日志", tags = {"系统日志" },  notes = "根据系统用户任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取Type", tags = {"系统日志" } ,notes = "根据系统用户任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户任务更新系统日志", tags = {"系统日志" },  notes = "根据系统用户任务更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户任务获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户任务获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/tasks/{task_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户测试版本获取系统日志", tags = {"系统日志" },  notes = "根据系统用户测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户测试版本建立系统日志", tags = {"系统日志" },  notes = "根据系统用户测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取Type", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户测试版本更新系统日志", tags = {"系统日志" },  notes = "根据系统用户测试版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户测试版本获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户测试版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/testtasks/{testtask_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户待办获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户待办获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户待办获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户待办获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户待办获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户待办获取系统日志", tags = {"系统日志" },  notes = "根据系统用户待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户待办建立系统日志", tags = {"系统日志" },  notes = "根据系统用户待办建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取Type", tags = {"系统日志" } ,notes = "根据系统用户待办获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户待办获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户待办更新系统日志", tags = {"系统日志" },  notes = "根据系统用户待办更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户待办获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品版本建立系统日志", tags = {"系统日志" },  notes = "根据产品版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取Type", tags = {"系统日志" } ,notes = "根据产品版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取我的数据", tags = {"系统日志" } ,notes = "根据产品版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品产品计划获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立系统日志", tags = {"系统日志" },  notes = "根据产品产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取Type", tags = {"系统日志" } ,notes = "根据产品产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取我的数据", tags = {"系统日志" } ,notes = "根据产品产品计划获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品需求获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品需求建立系统日志", tags = {"系统日志" },  notes = "根据产品需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取Type", tags = {"系统日志" } ,notes = "根据产品需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取我的数据", tags = {"系统日志" } ,notes = "根据产品需求获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据项目产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目产品计划获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目产品计划获取系统日志", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据项目产品计划建立系统日志", tags = {"系统日志" },  notes = "根据项目产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取Type", tags = {"系统日志" } ,notes = "根据项目产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据项目产品计划更新系统日志", tags = {"系统日志" },  notes = "根据项目产品计划更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取我的数据", tags = {"系统日志" } ,notes = "根据项目产品计划获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目Bug获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目Bug获取系统日志", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目Bug建立系统日志", tags = {"系统日志" },  notes = "根据项目Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取Type", tags = {"系统日志" } ,notes = "根据项目Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据项目Bug更新系统日志", tags = {"系统日志" },  notes = "根据项目Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取我的数据", tags = {"系统日志" } ,notes = "根据项目Bug获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目版本建立系统日志", tags = {"系统日志" },  notes = "根据项目版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取Type", tags = {"系统日志" } ,notes = "根据项目版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取我的数据", tags = {"系统日志" } ,notes = "根据项目版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目任务获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务建立系统日志", tags = {"系统日志" },  notes = "根据项目任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取Type", tags = {"系统日志" } ,notes = "根据项目任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取我的数据", tags = {"系统日志" } ,notes = "根据项目任务获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目需求获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目需求获取系统日志", tags = {"系统日志" },  notes = "根据项目需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目需求建立系统日志", tags = {"系统日志" },  notes = "根据项目需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取Type", tags = {"系统日志" } ,notes = "根据项目需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据项目需求更新系统日志", tags = {"系统日志" },  notes = "根据项目需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取我的数据", tags = {"系统日志" } ,notes = "根据项目需求获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目测试报告获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目测试报告建立系统日志", tags = {"系统日志" },  notes = "根据项目测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取Type", tags = {"系统日志" } ,notes = "根据项目测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取我的数据", tags = {"系统日志" } ,notes = "根据项目测试报告获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目测试版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立系统日志", tags = {"系统日志" },  notes = "根据项目测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取Type", tags = {"系统日志" } ,notes = "根据项目测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取我的数据", tags = {"系统日志" } ,notes = "根据项目测试版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品Bug获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品Bug建立系统日志", tags = {"系统日志" },  notes = "根据产品Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取Type", tags = {"系统日志" } ,notes = "根据产品Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据产品Bug更新系统日志", tags = {"系统日志" },  notes = "根据产品Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取我的数据", tags = {"系统日志" } ,notes = "根据产品Bug获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品测试报告获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品测试报告建立系统日志", tags = {"系统日志" },  notes = "根据产品测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取Type", tags = {"系统日志" } ,notes = "根据产品测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据产品测试报告更新系统日志", tags = {"系统日志" },  notes = "根据产品测试报告更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取我的数据", tags = {"系统日志" } ,notes = "根据产品测试报告获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品版本获取系统日志", tags = {"系统日志" },  notes = "根据系统用户产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品版本建立系统日志", tags = {"系统日志" },  notes = "根据系统用户产品版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取Type", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品版本更新系统日志", tags = {"系统日志" },  notes = "根据系统用户产品版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品版本获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户产品版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProductBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据系统用户产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品产品计划建立系统日志", tags = {"系统日志" },  notes = "根据系统用户产品产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取Type", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品产品计划更新系统日志", tags = {"系统日志" },  notes = "根据系统用户产品产品计划更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品产品计划获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户产品产品计划获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProductProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品需求获取系统日志", tags = {"系统日志" },  notes = "根据系统用户产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户产品需求建立系统日志", tags = {"系统日志" },  notes = "根据系统用户产品需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取Type", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品需求更新系统日志", tags = {"系统日志" },  notes = "根据系统用户产品需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户产品需求获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户产品需求获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/products/{product_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProductStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目产品计划获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目产品计划建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目产品计划更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目产品计划更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目产品计划获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目产品计划获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectProductPlan(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(productplan_id);context.setN_objecttype_eq("productplan");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目Bug获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目Bug建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目Bug更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目Bug更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目Bug获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目Bug获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectBug(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(bug_id);context.setN_objecttype_eq("bug");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目版本获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目版本建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目版本更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目版本获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectBuild(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(build_id);context.setN_objecttype_eq("Build");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目任务获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目任务建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目任务更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目任务更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目任务获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目任务获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/tasks/{task_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(task_id);context.setN_objecttype_eq("task");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目需求获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目需求建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目需求更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目需求更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目需求获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目需求获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectStory(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(story_id);context.setN_objecttype_eq("story");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目测试报告建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目测试报告更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试报告更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试报告获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目测试报告获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testreports/{testreport_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectTestReport(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);context.setN_objecttype_eq("testreport");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据系统用户项目测试版本建立系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取Type", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户项目测试版本更新系统日志", tags = {"系统日志" },  notes = "根据系统用户项目测试版本更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户项目测试版本获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户项目测试版本获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserProjectTestTask(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);context.setN_objecttype_eq("testtask");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

