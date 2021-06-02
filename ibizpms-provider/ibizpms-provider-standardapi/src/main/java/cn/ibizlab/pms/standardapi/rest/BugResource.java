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
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug" })
@RestController("StandardAPI-bug")
@RequestMapping("")
public class BugResource {

    @Autowired
    public IBugService bugService;

    @Autowired
    public BugRuntime bugRuntime;

    @Autowired
    @Lazy
    public BugMapping bugMapping;


    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取DEFAULT", tags = {"Bug" } ,notes = "根据系统用户获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefaultBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取项目BUG", tags = {"Bug" } ,notes = "根据系统用户获取项目BUG")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/fetchprojectbug")
	public ResponseEntity<List<BugDTO>> fetchProjectBugBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchProjectBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@BugRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "根据系统用户更新Bug", tags = {"Bug" },  notes = "根据系统用户更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"Bug" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/fetchaccount")
	public ResponseEntity<List<BugDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取产品BUG", tags = {"Bug" } ,notes = "根据系统用户获取产品BUG")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/fetchproductbug")
	public ResponseEntity<List<BugDTO>> fetchProductBugBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchProductBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('ASSIGNTO')")
    @ApiOperation(value = "根据系统用户指派", tags = {"Bug" },  notes = "根据系统用户指派")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('RESOLVE')")
    @ApiOperation(value = "根据系统用户解决", tags = {"Bug" },  notes = "根据系统用户解决")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"Bug" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/bugs/fetchmy")
	public ResponseEntity<List<BugDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody BugSearchContext context) {
        
        Page<Bug> domains = bugService.searchMy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取Bug草稿", tags = {"Bug" },  notes = "根据系统用户获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@BugRuntime.quickTest('CONFIRM')")
    @ApiOperation(value = "根据系统用户确认", tags = {"Bug" },  notes = "根据系统用户确认")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('ACTIVATE')")
    @ApiOperation(value = "根据系统用户激活", tags = {"Bug" },  notes = "根据系统用户激活")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户删除Bug", tags = {"Bug" },  notes = "根据系统用户删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("@BugRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据系统用户批量删除Bug", tags = {"Bug" },  notes = "根据系统用户批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.quickTest('READ')")
    @ApiOperation(value = "根据系统用户获取Bug", tags = {"Bug" },  notes = "根据系统用户获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户建立Bug", tags = {"Bug" },  notes = "根据系统用户建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/bugs")
    public ResponseEntity<BugDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"Bug" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取项目BUG", tags = {"Bug" } ,notes = "根据项目获取项目BUG")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchprojectbug")
	public ResponseEntity<List<BugDTO>> fetchProjectBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProjectBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目更新Bug", tags = {"Bug" },  notes = "根据项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取指定用户数据", tags = {"Bug" } ,notes = "根据项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchaccount")
	public ResponseEntity<List<BugDTO>> fetchAccountByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取产品BUG", tags = {"Bug" } ,notes = "根据项目获取产品BUG")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchproductbug")
	public ResponseEntity<List<BugDTO>> fetchProductBugByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProductBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目指派", tags = {"Bug" },  notes = "根据项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目解决", tags = {"Bug" },  notes = "根据项目解决")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取我的数据", tags = {"Bug" } ,notes = "根据项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/fetchmy")
	public ResponseEntity<List<BugDTO>> fetchMyByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目获取Bug草稿", tags = {"Bug" },  notes = "根据项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目确认", tags = {"Bug" },  notes = "根据项目确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目激活", tags = {"Bug" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目删除Bug", tags = {"Bug" },  notes = "根据项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目批量删除Bug", tags = {"Bug" },  notes = "根据项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取Bug", tags = {"Bug" },  notes = "根据项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据项目建立Bug", tags = {"Bug" },  notes = "根据项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs")
    public ResponseEntity<BugDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"Bug" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取项目BUG", tags = {"Bug" } ,notes = "根据产品获取项目BUG")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/fetchprojectbug")
	public ResponseEntity<List<BugDTO>> fetchProjectBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchProjectBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品更新Bug", tags = {"Bug" },  notes = "根据产品更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取指定用户数据", tags = {"Bug" } ,notes = "根据产品获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/fetchaccount")
	public ResponseEntity<List<BugDTO>> fetchAccountByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取产品BUG", tags = {"Bug" } ,notes = "根据产品获取产品BUG")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/fetchproductbug")
	public ResponseEntity<List<BugDTO>> fetchProductBugByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchProductBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品指派", tags = {"Bug" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品解决", tags = {"Bug" },  notes = "根据产品解决")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取我的数据", tags = {"Bug" } ,notes = "根据产品获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/fetchmy")
	public ResponseEntity<List<BugDTO>> fetchMyByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchMy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品获取Bug草稿", tags = {"Bug" },  notes = "根据产品获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品确认", tags = {"Bug" },  notes = "根据产品确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品激活", tags = {"Bug" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品删除Bug", tags = {"Bug" },  notes = "根据产品删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品批量删除Bug", tags = {"Bug" },  notes = "根据产品批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取Bug", tags = {"Bug" },  notes = "根据产品获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据产品建立Bug", tags = {"Bug" },  notes = "根据产品建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs")
    public ResponseEntity<BugDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }




    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取DEFAULT", tags = {"Bug" } ,notes = "根据系统用户项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefaultBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取项目BUG", tags = {"Bug" } ,notes = "根据系统用户项目获取项目BUG")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/fetchprojectbug")
	public ResponseEntity<List<BugDTO>> fetchProjectBugBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProjectBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目更新Bug", tags = {"Bug" },  notes = "根据系统用户项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取指定用户数据", tags = {"Bug" } ,notes = "根据系统用户项目获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/fetchaccount")
	public ResponseEntity<List<BugDTO>> fetchAccountBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchAccount(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取产品BUG", tags = {"Bug" } ,notes = "根据系统用户项目获取产品BUG")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/fetchproductbug")
	public ResponseEntity<List<BugDTO>> fetchProductBugBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProductBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目指派", tags = {"Bug" },  notes = "根据系统用户项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目解决", tags = {"Bug" },  notes = "根据系统用户项目解决")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据系统用户项目获取我的数据", tags = {"Bug" } ,notes = "根据系统用户项目获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/projects/{project_id}/bugs/fetchmy")
	public ResponseEntity<List<BugDTO>> fetchMyBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchMy(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目获取Bug草稿", tags = {"Bug" },  notes = "根据系统用户项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目确认", tags = {"Bug" },  notes = "根据系统用户项目确认")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目激活", tags = {"Bug" },  notes = "根据系统用户项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目删除Bug", tags = {"Bug" },  notes = "根据系统用户项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目批量删除Bug", tags = {"Bug" },  notes = "根据系统用户项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据系统用户项目获取Bug", tags = {"Bug" },  notes = "根据系统用户项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUGMANAGE')")
    @ApiOperation(value = "根据系统用户项目建立Bug", tags = {"Bug" },  notes = "根据系统用户项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/projects/{project_id}/bugs")
    public ResponseEntity<BugDTO> createBySysUserProject(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

