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
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectRuntime;

@Slf4j
@Api(tags = {"项目" })
@RestController("StandardAPI-accountproject")
@RequestMapping("")
public class AccountProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    public ProjectRuntime projectRuntime;

    @Autowired
    @Lazy
    public AccountProjectMapping accountprojectMapping;

    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"项目" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountprojects/fetchaccount")
	public ResponseEntity<List<AccountProjectDTO>> fetchaccount(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchAccount(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PROJECT', #accountproject_id, 'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/accountprojects/{accountproject_id}")
    public ResponseEntity<AccountProjectDTO> get(@PathVariable("accountproject_id") Long accountproject_id) {
        Project domain = projectService.get(accountproject_id);
        AccountProjectDTO dto = accountprojectMapping.toDto(domain);
        Map<String,Integer> opprivs = projectRuntime.getOPPrivs(accountproject_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"项目" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountprojects/fetchmy")
	public ResponseEntity<List<AccountProjectDTO>> fetchmy(@RequestBody ProjectSearchContext context) {
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMy(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/accountprojects/{accountproject_id}/{action}")
    public ResponseEntity<AccountProjectDTO> dynamicCall(@PathVariable("accountproject_id") Long accountproject_id , @PathVariable("action") String action , @RequestBody AccountProjectDTO accountprojectdto) {
        Project domain = projectService.dynamicCall(accountproject_id, action, accountprojectMapping.toDomain(accountprojectdto));
        accountprojectdto = accountprojectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accountprojectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT','READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"项目" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountprojects/fetchaccount")
	public ResponseEntity<List<AccountProjectDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchAccount(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_PROJECT', #accountproject_id, 'READ')")
    @ApiOperation(value = "根据系统用户获取项目", tags = {"项目" },  notes = "根据系统用户获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accountprojects/{accountproject_id}")
    public ResponseEntity<AccountProjectDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accountproject_id") Long accountproject_id) {
        Project domain = projectService.get(accountproject_id);
        AccountProjectDTO dto = accountprojectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT','READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"项目" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountprojects/fetchmy")
	public ResponseEntity<List<AccountProjectDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody ProjectSearchContext context) {
        
        projectRuntime.addAuthorityConditions(context,"READ");
        Page<Project> domains = projectService.searchMy(context) ;
        List<AccountProjectDTO> list = accountprojectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

