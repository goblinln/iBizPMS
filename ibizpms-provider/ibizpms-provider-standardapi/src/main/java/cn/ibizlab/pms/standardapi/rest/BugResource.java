package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug"})
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


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立Bug", tags = {"Bug" },  notes = "根据项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs")
    public ResponseEntity<BugDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'READ')")
    @ApiOperation(value = "根据项目获取Bug", tags = {"Bug" },  notes = "根据项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除Bug", tags = {"Bug" },  notes = "根据项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除Bug", tags = {"Bug" },  notes = "根据项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新Bug", tags = {"Bug" },  notes = "根据项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug testget = bugService.get(bug_id);
        if (testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'ACTIVATE')")
    @ApiOperation(value = "根据项目激活", tags = {"Bug" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'ASSIGNTO')")
    @ApiOperation(value = "根据项目指派", tags = {"Bug" },  notes = "根据项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'FAVORITES')")
    @ApiOperation(value = "根据项目Bug收藏", tags = {"Bug" },  notes = "根据项目Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'NFAVORITES')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"Bug" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'BUILDLINK')")
    @ApiOperation(value = "根据项目版本解除关联Bug", tags = {"Bug" },  notes = "根据项目版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'CONFIRM')")
    @ApiOperation(value = "根据项目确认", tags = {"Bug" },  notes = "根据项目确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取Bug草稿", tags = {"Bug" },  notes = "根据项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'PLANLINK')")
    @ApiOperation(value = "根据项目解除关联Bug", tags = {"Bug" },  notes = "根据项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'RELEASELINK')")
    @ApiOperation(value = "根据项目解除关联Bug", tags = {"Bug" },  notes = "根据项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'RESOLVE')")
    @ApiOperation(value = "根据项目解决", tags = {"Bug" },  notes = "根据项目解决")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProject(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
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

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立Bug", tags = {"Bug" },  notes = "根据产品建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs")
    public ResponseEntity<BugDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', #bug_id, 'READ')")
    @ApiOperation(value = "根据产品获取Bug", tags = {"Bug" },  notes = "根据产品获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除Bug", tags = {"Bug" },  notes = "根据产品删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除Bug", tags = {"Bug" },  notes = "根据产品批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新Bug", tags = {"Bug" },  notes = "根据产品更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/tests/{product_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug testget = bugService.get(bug_id);
        if (testget.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"Bug" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'ASSIGNTO')")
    @ApiOperation(value = "根据产品指派", tags = {"Bug" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', #bug_id, 'FAVORITES')")
    @ApiOperation(value = "根据产品Bug收藏", tags = {"Bug" },  notes = "根据产品Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', #bug_id, 'NFAVORITES')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"Bug" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'BUILDLINK')")
    @ApiOperation(value = "根据产品版本解除关联Bug", tags = {"Bug" },  notes = "根据产品版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'CONFIRM')")
    @ApiOperation(value = "根据产品确认", tags = {"Bug" },  notes = "根据产品确认")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取Bug草稿", tags = {"Bug" },  notes = "根据产品获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tests/{product_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'PLANLINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"Bug" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'RELEASELINK')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"Bug" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'BUGMANAGE', #bug_id, 'RESOLVE')")
    @ApiOperation(value = "根据产品解决", tags = {"Bug" },  notes = "根据产品解决")
	@RequestMapping(method = RequestMethod.POST, value = "/tests/{product_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProduct(product_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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
    @PreAuthorize("test('ZT_BUG', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
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


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目建立Bug", tags = {"Bug" },  notes = "根据产品项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs")
    public ResponseEntity<BugDTO> createByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'READ')")
    @ApiOperation(value = "根据产品项目获取Bug", tags = {"Bug" },  notes = "根据产品项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> getByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
        Bug domain = bugService.get(bug_id);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目删除Bug", tags = {"Bug" },  notes = "根据产品项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<Boolean> removeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(bug_id));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品项目批量删除Bug", tags = {"Bug" },  notes = "根据产品项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/bugs/batch")
    public ResponseEntity<Boolean> removeBatchByProductProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'UPDATE')")
    @ApiOperation(value = "根据产品项目更新Bug", tags = {"Bug" },  notes = "根据产品项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}")
    public ResponseEntity<BugDTO> updateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug testget = bugService.get(bug_id);
        if (testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
		bugService.update(domain);
        BugDTO dto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'ACTIVATE')")
    @ApiOperation(value = "根据产品项目激活", tags = {"Bug" },  notes = "根据产品项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/activate")
    public ResponseEntity<BugDTO> activateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.activate(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'ASSIGNTO')")
    @ApiOperation(value = "根据产品项目指派", tags = {"Bug" },  notes = "根据产品项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/assignto")
    public ResponseEntity<BugDTO> assignToByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.assignTo(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'FAVORITES')")
    @ApiOperation(value = "根据产品项目Bug收藏", tags = {"Bug" },  notes = "根据产品项目Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/bugfavorites")
    public ResponseEntity<BugDTO> bugFavoritesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.bugFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', #bug_id, 'NFAVORITES')")
    @ApiOperation(value = "根据产品项目取消收藏", tags = {"Bug" },  notes = "根据产品项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/bugnfavorites")
    public ResponseEntity<BugDTO> bugNFavoritesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.bugNFavorites(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'BUILDLINK')")
    @ApiOperation(value = "根据产品项目版本解除关联Bug", tags = {"Bug" },  notes = "根据产品项目版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/buildunlinkbug")
    public ResponseEntity<BugDTO> buildUnlinkBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'CONFIRM')")
    @ApiOperation(value = "根据产品项目确认", tags = {"Bug" },  notes = "根据产品项目确认")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/confirm")
    public ResponseEntity<BugDTO> confirmByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.confirm(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目获取Bug草稿", tags = {"Bug" },  notes = "根据产品项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/bugs/getdraft")
    public ResponseEntity<BugDTO> getDraftByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, BugDTO dto) {
        Bug domain = bugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(bugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'PLANLINK')")
    @ApiOperation(value = "根据产品项目解除关联Bug", tags = {"Bug" },  notes = "根据产品项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/unlinkbug")
    public ResponseEntity<BugDTO> unlinkBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.unlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'RELEASELINK')")
    @ApiOperation(value = "根据产品项目解除关联Bug", tags = {"Bug" },  notes = "根据产品项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/releaseunlinkbug")
    public ResponseEntity<BugDTO> releaseUnlinkBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'BUGMANAGE', #bug_id, 'RESOLVE')")
    @ApiOperation(value = "根据产品项目解决", tags = {"Bug" },  notes = "根据产品项目解决")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/resolve")
    public ResponseEntity<BugDTO> resolveByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @RequestBody BugDTO bugdto) {
        Bug domain = bugMapping.toDomain(bugdto);
        domain.setProject(project_id);
        domain.setId(bug_id);
        domain = bugService.resolve(domain) ;
        bugdto = bugMapping.toDto(domain);
        Map<String, Integer> opprivs = bugRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        bugdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(bugdto);
    }

    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取DEFAULT", tags = {"Bug" } ,notes = "根据产品项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/fetchdefault")
	public ResponseEntity<List<BugDTO>> fetchDefaultByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取产品BUG", tags = {"Bug" } ,notes = "根据产品项目获取产品BUG")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/fetchproductbug")
	public ResponseEntity<List<BugDTO>> fetchProductBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProductBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUG', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取项目BUG", tags = {"Bug" } ,notes = "根据产品项目获取项目BUG")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/fetchprojectbug")
	public ResponseEntity<List<BugDTO>> fetchProjectBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchProjectBugDS(context) ;
        List<BugDTO> list = bugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

