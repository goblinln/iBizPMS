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


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取系统日志", tags = {"系统日志" },  notes = "根据产品获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立系统日志", tags = {"系统日志" },  notes = "根据产品建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions")
    public ResponseEntity<ActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(product_id);domain.setObjecttype("product");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
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



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取系统日志", tags = {"系统日志" },  notes = "根据项目获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目建立系统日志", tags = {"系统日志" },  notes = "根据项目建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions")
    public ResponseEntity<ActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(project_id);domain.setObjecttype("project");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
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



    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
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
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
    @ApiOperation(value = "根据测试套件获取系统日志", tags = {"系统日志" },  notes = "根据测试套件获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'CREATE')")
    @ApiOperation(value = "根据测试套件获取系统日志草稿", tags = {"系统日志" },  notes = "根据测试套件获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
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
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
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
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'CREATE')")
    @ApiOperation(value = "根据测试套件建立系统日志", tags = {"系统日志" },  notes = "根据测试套件建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions")
    public ResponseEntity<ActionDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testsuite_id);domain.setObjecttype("testsuite");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'READ')")
	@ApiOperation(value = "根据测试套件获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据测试套件获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id, 'UPDATE')")
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




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品版本获取系统日志", tags = {"系统日志" },  notes = "根据产品版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据产品版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据产品版本建立系统日志", tags = {"系统日志" },  notes = "根据产品版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUILDMANAGE')")
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




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品产品计划获取系统日志", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品产品计划建立系统日志", tags = {"系统日志" },  notes = "根据产品产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
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




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品需求获取系统日志", tags = {"系统日志" },  notes = "根据产品需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品需求建立系统日志", tags = {"系统日志" },  notes = "根据产品需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目产品计划获取系统日志", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目产品计划获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目产品计划获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/productplans/{productplan_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据项目产品计划建立系统日志", tags = {"系统日志" },  notes = "根据项目产品计划建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/productplans/{productplan_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(productplan_id);domain.setObjecttype("productplan");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目产品计划获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目产品计划获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ActionRuntime.quickTest('UPDATE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目Bug获取系统日志", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目Bug建立系统日志", tags = {"系统日志" },  notes = "根据项目Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目版本获取系统日志", tags = {"系统日志" },  notes = "根据项目版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目版本建立系统日志", tags = {"系统日志" },  notes = "根据项目版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(build_id);domain.setObjecttype("Build");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目任务获取系统日志", tags = {"系统日志" },  notes = "根据项目任务获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目任务获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目任务获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
    @ApiOperation(value = "根据项目任务建立系统日志", tags = {"系统日志" },  notes = "根据项目任务建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(task_id);domain.setObjecttype("task");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
	@ApiOperation(value = "根据项目任务获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目任务获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TASKMANAGE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目需求获取系统日志", tags = {"系统日志" },  notes = "根据项目需求获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据项目需求获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目需求获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/stories/{story_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'STORYMANAGE')")
    @ApiOperation(value = "根据项目需求建立系统日志", tags = {"系统日志" },  notes = "根据项目需求建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/stories/{story_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(story_id);domain.setObjecttype("story");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目需求获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目需求获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ActionRuntime.quickTest('UPDATE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目测试报告获取系统日志", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'CREATE')")
    @ApiOperation(value = "根据项目测试报告建立系统日志", tags = {"系统日志" },  notes = "根据项目测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
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




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目测试版本获取系统日志", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目测试版本获取系统日志草稿", tags = {"系统日志" },  notes = "根据项目测试版本获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目测试版本建立系统日志", tags = {"系统日志" },  notes = "根据项目测试版本建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions")
    public ResponseEntity<ActionDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testtask_id);domain.setObjecttype("testtask");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目测试版本获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据项目测试版本获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
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




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品Bug获取系统日志", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品Bug获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品Bug获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品Bug建立系统日志", tags = {"系统日志" },  notes = "根据产品Bug建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/actions")
    public ResponseEntity<ActionDTO> createByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(bug_id);domain.setObjecttype("bug");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品Bug获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品Bug获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
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




    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品测试报告获取系统日志", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/{action_id}")
    public ResponseEntity<ActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id) {
        Action domain = actionService.get(action_id);
        ActionDTO dto = actionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品测试报告获取系统日志草稿", tags = {"系统日志" },  notes = "根据产品测试报告获取系统日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/testreports/{testreport_id}/actions/getdraft")
    public ResponseEntity<ActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, ActionDTO dto) {
        Action domain = actionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
        return ResponseEntity.status(HttpStatus.OK).body(actionMapping.toDto(actionService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
    @ApiOperation(value = "根据产品测试报告建立系统日志", tags = {"系统日志" },  notes = "根据产品测试报告建立系统日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/testreports/{testreport_id}/actions")
    public ResponseEntity<ActionDTO> createByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody ActionDTO actiondto) {
        Action domain = actionMapping.toDomain(actiondto);
        domain.setObjectid(testreport_id);domain.setObjecttype("testreport");
		actionService.create(domain);
        ActionDTO dto = actionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品测试报告获取项目动态(我的)", tags = {"系统日志" } ,notes = "根据产品测试报告获取项目动态(我的)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/actions/fetchmy")
	public ResponseEntity<List<ActionDTO>> fetchMyByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody ActionSearchContext context) {
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'TESTREPORTMANAGE')")
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


}

