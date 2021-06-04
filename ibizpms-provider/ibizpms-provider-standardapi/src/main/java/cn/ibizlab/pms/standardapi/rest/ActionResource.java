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
    @ApiOperation(value = "根据系统用户获取系统日志", tags = {"系统日志" },  notes = "根据系统用户获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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

    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据日报建立系统日志", tags = {"系统日志" },  notes = "根据日报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/dailies/{ibzdaily_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取ProductTrends", tags = {"系统日志" } ,notes = "根据日报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据日报更新系统日志", tags = {"系统日志" },  notes = "根据日报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/dailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据日报获取系统日志", tags = {"系统日志" },  notes = "根据日报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/dailies/{ibzdaily_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据日报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取我的数据", tags = {"系统日志" } ,notes = "根据日报获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取指定用户数据", tags = {"系统日志" } ,notes = "根据日报获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据日报获取系统日志草稿", tags = {"系统日志" },  notes = "根据日报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/dailies/{ibzdaily_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzdaily_id);domain.setObjecttype("daily");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据日报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取Type", tags = {"系统日志" } ,notes = "根据日报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzdaily_id);context.setN_objecttype_eq("daily");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库建立系统日志", tags = {"系统日志" },  notes = "根据文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据文档库获取系统日志", tags = {"系统日志" },  notes = "根据文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取我的数据", tags = {"系统日志" } ,notes = "根据文档库获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取指定用户数据", tags = {"系统日志" } ,notes = "根据文档库获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取Type", tags = {"系统日志" } ,notes = "根据文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档建立系统日志", tags = {"系统日志" },  notes = "根据文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据文档获取系统日志", tags = {"系统日志" },  notes = "根据文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取我的数据", tags = {"系统日志" } ,notes = "根据文档获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取指定用户数据", tags = {"系统日志" } ,notes = "根据文档获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取Type", tags = {"系统日志" } ,notes = "根据文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据月报建立系统日志", tags = {"系统日志" },  notes = "根据月报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{ibzmonthly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取ProductTrends", tags = {"系统日志" } ,notes = "根据月报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据月报更新系统日志", tags = {"系统日志" },  notes = "根据月报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/monthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据月报获取系统日志", tags = {"系统日志" },  notes = "根据月报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/monthlies/{ibzmonthly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据月报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取我的数据", tags = {"系统日志" } ,notes = "根据月报获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取指定用户数据", tags = {"系统日志" } ,notes = "根据月报获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据月报获取系统日志草稿", tags = {"系统日志" },  notes = "根据月报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/monthlies/{ibzmonthly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzmonthly_id);domain.setObjecttype("monthly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据月报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取Type", tags = {"系统日志" } ,notes = "根据月报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzmonthly_id);context.setN_objecttype_eq("monthly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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

    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据汇报建立系统日志", tags = {"系统日志" },  notes = "根据汇报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/reportlies/{ibzreportly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取ProductTrends", tags = {"系统日志" } ,notes = "根据汇报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据汇报更新系统日志", tags = {"系统日志" },  notes = "根据汇报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/reportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据汇报获取系统日志", tags = {"系统日志" },  notes = "根据汇报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/reportlies/{ibzreportly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据汇报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取我的数据", tags = {"系统日志" } ,notes = "根据汇报获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取指定用户数据", tags = {"系统日志" } ,notes = "根据汇报获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据汇报获取系统日志草稿", tags = {"系统日志" },  notes = "根据汇报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/reportlies/{ibzreportly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzreportly_id);domain.setObjecttype("reportly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据汇报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取Type", tags = {"系统日志" } ,notes = "根据汇报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);context.setN_objecttype_eq("reportly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_TESTSUITE', #testsuite_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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

    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据待办建立系统日志", tags = {"系统日志" },  notes = "根据待办建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions")
    public ResponseEntity<ActionDTO> createByTodo(@PathVariable("todo_id") Long todo_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取ProductTrends", tags = {"系统日志" } ,notes = "根据待办获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据待办获取系统日志", tags = {"系统日志" },  notes = "根据待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据待办获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取我的数据", tags = {"系统日志" } ,notes = "根据待办获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取指定用户数据", tags = {"系统日志" } ,notes = "根据待办获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据待办获取系统日志草稿", tags = {"系统日志" },  notes = "根据待办获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTodo(@PathVariable("todo_id") Long todo_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据待办获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取Type", tags = {"系统日志" } ,notes = "根据待办获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(todo_id);context.setN_objecttype_eq("todo");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据周报建立系统日志", tags = {"系统日志" },  notes = "根据周报建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{ibzweekly_id}/actions")
    public ResponseEntity<ActionDTO> createByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取ProductTrends", tags = {"系统日志" } ,notes = "根据周报获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'UPDATE', #action_id, 'UPDATE')")
    @ApiOperation(value = "根据周报更新系统日志", tags = {"系统日志" },  notes = "根据周报更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/weeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据周报获取系统日志", tags = {"系统日志" },  notes = "根据周报获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/weeklies/{ibzweekly_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据周报获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取我的数据", tags = {"系统日志" } ,notes = "根据周报获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取指定用户数据", tags = {"系统日志" } ,notes = "根据周报获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据周报获取系统日志草稿", tags = {"系统日志" },  notes = "根据周报获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/weeklies/{ibzweekly_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(ibzweekly_id);domain.setObjecttype("weekly");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据周报获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取Type", tags = {"系统日志" } ,notes = "根据周报获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);context.setN_objecttype_eq("weekly");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户文档建立系统日志", tags = {"系统日志" },  notes = "根据系统用户文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据系统用户文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("quickTest('ZT_ACTION', 'UPDATE')")
    @ApiOperation(value = "根据系统用户文档更新系统日志", tags = {"系统日志" },  notes = "根据系统用户文档更新系统日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> updateBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        domain.setId(action_id);
		actionService.update(domain);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
    @ApiOperation(value = "根据系统用户文档获取系统日志", tags = {"系统日志" },  notes = "根据系统用户文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据系统用户文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取我的数据", tags = {"系统日志" } ,notes = "根据系统用户文档获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取指定用户数据", tags = {"系统日志" } ,notes = "根据系统用户文档获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据系统用户文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据系统用户文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_ACTION', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取Type", tags = {"系统日志" } ,notes = "根据系统用户文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据系统用户待办获取系统日志", tags = {"系统日志" },  notes = "根据系统用户待办获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_TODO', #todo_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据系统用户待办获取系统日志草稿", tags = {"系统日志" },  notes = "根据系统用户待办获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(todo_id);domain.setObjecttype("todo");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取我的数据", tags = {"系统日志" } ,notes = "根据文档库文档获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取指定用户数据", tags = {"系统日志" } ,notes = "根据文档库文档获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取Type", tags = {"系统日志" } ,notes = "根据文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库建立系统日志", tags = {"系统日志" },  notes = "根据产品文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品文档库获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取我的数据", tags = {"系统日志" } ,notes = "根据产品文档库获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品文档库获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取Type", tags = {"系统日志" } ,notes = "根据产品文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库建立系统日志", tags = {"系统日志" },  notes = "根据项目文档库建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #action_id, 'READ')")
    @ApiOperation(value = "根据项目文档库获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取我的数据", tags = {"系统日志" } ,notes = "根据项目文档库获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目文档库获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doclib_id);domain.setObjecttype("doclib");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取Type", tags = {"系统日志" } ,notes = "根据项目文档库获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doclib_id);context.setN_objecttype_eq("doclib");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目产品计划获取系统日志", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("quickTest('ZT_ACTION', 'CREATE')")
    @ApiOperation(value = "根据项目产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目Bug获取系统日志", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目需求获取系统日志", tags = {"系统日志" },  notes = "根据项目需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'STORYMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'TESTTASKMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
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
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'TESTREPORTMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
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



    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据产品文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'UPDATE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', #action_id, 'READ')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取我的数据", tags = {"系统日志" } ,notes = "根据产品文档库文档获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取指定用户数据", tags = {"系统日志" } ,notes = "根据产品文档库文档获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据产品文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取Type", tags = {"系统日志" } ,notes = "根据产品文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchType(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库文档建立系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取ProductTrends", tags = {"系统日志" } ,notes = "根据项目文档库文档获取ProductTrends")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproduct")
	public ResponseEntity<List<ActionDTO>> fetchProductByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProductTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "action" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #action_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #action_id, 'READ')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目文档库文档获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmytrends")
	public ResponseEntity<List<ActionDTO>> fetchMyTrendsByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMyTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取我的数据", tags = {"系统日志" } ,notes = "根据项目文档库文档获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchMy(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取指定用户数据", tags = {"系统日志" } ,notes = "根据项目文档库文档获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchaccount")
	public ResponseEntity<List<ActionDTO>> fetchAccountByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchAccount(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目文档库文档获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(doc_id);domain.setObjecttype("doc");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取项目动态(项目相关所有)", tags = {"系统日志" } ,notes = "根据项目文档库文档获取项目动态(项目相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchproject")
	public ResponseEntity<List<ActionDTO>> fetchProjectByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
        context.setN_objectid_eq(doc_id);context.setN_objecttype_eq("doc");
        Page<Action> domains = actionService.searchProjectTrends(context) ;
        List<ActionDTO> list = actionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_ACTION', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取Type", tags = {"系统日志" } ,notes = "根据项目文档库文档获取Type")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/fetchmain")
	public ResponseEntity<List<ActionDTO>> fetchMainByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody ActionSearchContext context) {
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

