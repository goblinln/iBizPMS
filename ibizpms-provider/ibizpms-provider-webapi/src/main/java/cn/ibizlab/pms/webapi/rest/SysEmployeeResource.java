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
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"人员" })
@RestController("WebApi-sysemployee")
@RequestMapping("")
public class SysEmployeeResource {

    @Autowired
    public ISysEmployeeService sysemployeeService;


    @Autowired
    @Lazy
    public SysEmployeeMapping sysemployeeMapping;

    @ApiOperation(value = "新建人员", tags = {"人员" },  notes = "新建人员")
	@RequestMapping(method = RequestMethod.POST, value = "/sysemployees")
    @Transactional
    public ResponseEntity<SysEmployeeDTO> create(@Validated @RequestBody SysEmployeeDTO sysemployeedto) {
        SysEmployee domain = sysemployeeMapping.toDomain(sysemployeedto);
		sysemployeeService.create(domain);
        SysEmployeeDTO dto = sysemployeeMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "sysemployee" , versionfield = "updatedate")
    @ApiOperation(value = "更新人员", tags = {"人员" },  notes = "更新人员")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysemployees/{sysemployee_id}")
    @Transactional
    public ResponseEntity<SysEmployeeDTO> update(@PathVariable("sysemployee_id") String sysemployee_id, @RequestBody SysEmployeeDTO sysemployeedto) {
		SysEmployee domain  = sysemployeeMapping.toDomain(sysemployeedto);
        domain.setUserid(sysemployee_id);
		sysemployeeService.update(domain );
		SysEmployeeDTO dto = sysemployeeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除人员", tags = {"人员" },  notes = "删除人员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysemployees/{sysemployee_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("sysemployee_id") String sysemployee_id) {
         return ResponseEntity.status(HttpStatus.OK).body(sysemployeeService.remove(sysemployee_id));
    }


    @ApiOperation(value = "获取人员", tags = {"人员" },  notes = "获取人员")
	@RequestMapping(method = RequestMethod.GET, value = "/sysemployees/{sysemployee_id}")
    public ResponseEntity<SysEmployeeDTO> get(@PathVariable("sysemployee_id") String sysemployee_id) {
        SysEmployee domain = sysemployeeService.get(sysemployee_id);
        SysEmployeeDTO dto = sysemployeeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取人员草稿", tags = {"人员" },  notes = "获取人员草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/sysemployees/getdraft")
    public ResponseEntity<SysEmployeeDTO> getDraft(SysEmployeeDTO dto) {
        SysEmployee domain = sysemployeeMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(sysemployeeMapping.toDto(sysemployeeService.getDraft(domain)));
    }

    @ApiOperation(value = "检查人员", tags = {"人员" },  notes = "检查人员")
	@RequestMapping(method = RequestMethod.POST, value = "/sysemployees/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SysEmployeeDTO sysemployeedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(sysemployeeService.checkKey(sysemployeeMapping.toDomain(sysemployeedto)));
    }

    @ApiOperation(value = "保存人员", tags = {"人员" },  notes = "保存人员")
	@RequestMapping(method = RequestMethod.POST, value = "/sysemployees/save")
    public ResponseEntity<SysEmployeeDTO> save(@RequestBody SysEmployeeDTO sysemployeedto) {
        SysEmployee domain = sysemployeeMapping.toDomain(sysemployeedto);
        sysemployeeService.save(domain);
        SysEmployeeDTO dto = sysemployeeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取Bug用户", tags = {"人员" } ,notes = "获取Bug用户")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchbuguser")
	public ResponseEntity<List<SysEmployeeDTO>> fetchbuguser(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchBugUser(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取联系人用户", tags = {"人员" } ,notes = "获取联系人用户")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchcontactlist")
	public ResponseEntity<List<SysEmployeeDTO>> fetchcontactlist(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchContActList(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取数据集", tags = {"人员" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchdefault")
	public ResponseEntity<List<SysEmployeeDTO>> fetchdefault(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchDefault(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队管理", tags = {"人员" } ,notes = "获取项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchproductteamm")
	public ResponseEntity<List<SysEmployeeDTO>> fetchproductteamm(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProductTeamM(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队管理", tags = {"人员" } ,notes = "获取项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteamm")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteamm(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamM(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队管理", tags = {"人员" } ,notes = "获取项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteammproduct")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteammproduct(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamMProduct(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队成员(临时)", tags = {"人员" } ,notes = "获取项目团队成员(临时)")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteamtaskusertemp")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteamtaskusertemp(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamTaskUserTemp(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队成员", tags = {"人员" } ,notes = "获取项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteamuser")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteamuser(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamUser(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队成员", tags = {"人员" } ,notes = "获取项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteamusertask")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteamusertask(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectTeamUserTask(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取项目团队成员选择", tags = {"人员" } ,notes = "获取项目团队成员选择")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchprojectteampk")
	public ResponseEntity<List<SysEmployeeDTO>> fetchprojectteampk(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchProjectteamPk(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取产品团队成员选择", tags = {"人员" } ,notes = "获取产品团队成员选择")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchstoryproductteampk")
	public ResponseEntity<List<SysEmployeeDTO>> fetchstoryproductteampk(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchStoryProductTeamPK(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取任务多人团队", tags = {"人员" } ,notes = "获取任务多人团队")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchtaskmteam")
	public ResponseEntity<List<SysEmployeeDTO>> fetchtaskmteam(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchTaskMTeam(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
	@ApiOperation(value = "获取数据查询2", tags = {"人员" } ,notes = "获取数据查询2")
    @RequestMapping(method= RequestMethod.POST , value="/sysemployees/fetchtaskteam")
	public ResponseEntity<List<SysEmployeeDTO>> fetchtaskteam(@RequestBody SysEmployeeSearchContext context) {
        Page<SysEmployee> domains = sysemployeeService.searchTaskTeam(context) ;
        List<SysEmployeeDTO> list = sysemployeeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/{sysemployee_id}/{action}")
    public ResponseEntity<SysEmployeeDTO> dynamicCall(@PathVariable("sysemployee_id") String sysemployee_id , @PathVariable("action") String action , @RequestBody SysEmployeeDTO sysemployeedto) {
        SysEmployee domain = sysemployeeService.dynamicCall(sysemployee_id, action, sysemployeeMapping.toDomain(sysemployeedto));
        sysemployeedto = sysemployeeMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(sysemployeedto);
    }
}

