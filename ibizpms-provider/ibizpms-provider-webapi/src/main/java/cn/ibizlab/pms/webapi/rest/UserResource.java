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
import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.service.IUserService;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.UserRuntime;

@Slf4j
@Api(tags = {"用户" })
@RestController("WebApi-user")
@RequestMapping("")
public class UserResource {

    @Autowired
    public IUserService userService;

    @Autowired
    public UserRuntime userRuntime;

    @Autowired
    @Lazy
    public UserMapping userMapping;

    @PreAuthorize("@UserRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建用户", tags = {"用户" },  notes = "新建用户")
	@RequestMapping(method = RequestMethod.POST, value = "/users")
    @Transactional
    public ResponseEntity<UserDTO> create(@Validated @RequestBody UserDTO userdto) {
        User domain = userMapping.toDomain(userdto);
		userService.create(domain);
        if(!userRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        UserDTO dto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@UserRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建用户", tags = {"用户" },  notes = "批量新建用户")
	@RequestMapping(method = RequestMethod.POST, value = "/users/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<UserDTO> userdtos) {
        userService.createBatch(userMapping.toDomain(userdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserRuntime.test(#user_id,'UPDATE')")
    @ApiOperation(value = "更新用户", tags = {"用户" },  notes = "更新用户")
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{user_id}")
    @Transactional
    public ResponseEntity<UserDTO> update(@PathVariable("user_id") Long user_id, @RequestBody UserDTO userdto) {
		User domain  = userMapping.toDomain(userdto);
        domain.setId(user_id);
		userService.update(domain );
        if(!userRuntime.test(user_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		UserDTO dto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(user_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@UserRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新用户", tags = {"用户" },  notes = "批量更新用户")
	@RequestMapping(method = RequestMethod.PUT, value = "/users/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<UserDTO> userdtos) {
        userService.updateBatch(userMapping.toDomain(userdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserRuntime.test(#user_id,'DELETE')")
    @ApiOperation(value = "删除用户", tags = {"用户" },  notes = "删除用户")
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{user_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("user_id") Long user_id) {
         return ResponseEntity.status(HttpStatus.OK).body(userService.remove(user_id));
    }

    @PreAuthorize("@UserRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除用户", tags = {"用户" },  notes = "批量删除用户")
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        userService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserRuntime.test(#user_id,'READ')")
    @ApiOperation(value = "获取用户", tags = {"用户" },  notes = "获取用户")
	@RequestMapping(method = RequestMethod.GET, value = "/users/{user_id}")
    public ResponseEntity<UserDTO> get(@PathVariable("user_id") Long user_id) {
        User domain = userService.get(user_id);
        UserDTO dto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(user_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用户草稿", tags = {"用户" },  notes = "获取用户草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/users/getdraft")
    public ResponseEntity<UserDTO> getDraft(UserDTO dto) {
        User domain = userMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(userMapping.toDto(userService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用户", tags = {"用户" },  notes = "检查用户")
	@RequestMapping(method = RequestMethod.POST, value = "/users/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody UserDTO userdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(userService.checkKey(userMapping.toDomain(userdto)));
    }

    @PreAuthorize("@UserRuntime.test(#user_id,'READ')")
    @ApiOperation(value = "根据代码账户查询用户信息", tags = {"用户" },  notes = "根据代码账户查询用户信息")
	@RequestMapping(method = RequestMethod.GET, value = "/users/{user_id}/getbycommiter")
    public ResponseEntity<UserDTO> getByCommiter(@PathVariable("user_id") Long user_id, @RequestBody UserDTO userdto) {
        User domain = userMapping.toDomain(userdto);
        domain.setId(user_id);
        domain = userService.getByCommiter(domain);
        userdto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(userdto);
    }

    @ApiOperation(value = "保存用户", tags = {"用户" },  notes = "保存用户")
	@RequestMapping(method = RequestMethod.POST, value = "/users/save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userdto) {
        User domain = userMapping.toDomain(userdto);
        userService.save(domain);
        UserDTO dto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存用户", tags = {"用户" },  notes = "批量保存用户")
	@RequestMapping(method = RequestMethod.POST, value = "/users/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<UserDTO> userdtos) {
        userService.saveBatch(userMapping.toDomain(userdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "同步账号", tags = {"用户" },  notes = "同步账号")
	@RequestMapping(method = RequestMethod.POST, value = "/users/{user_id}/syncaccount")
    public ResponseEntity<UserDTO> syncAccount(@PathVariable("user_id") Long user_id, @RequestBody UserDTO userdto) {
        User domain = userMapping.toDomain(userdto);
        domain.setId(user_id);
        domain = userService.syncAccount(domain);
        userdto = userMapping.toDto(domain);
        Map<String,Integer> opprivs = userRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(userdto);
    }
    @ApiOperation(value = "批量处理[同步账号]", tags = {"用户" },  notes = "批量处理[同步账号]")
	@RequestMapping(method = RequestMethod.POST, value = "/users/syncaccountbatch")
    public ResponseEntity<Boolean> syncAccountBatch(@RequestBody List<UserDTO> userdtos) {
        List<User> domains = userMapping.toDomain(userdtos);
        boolean result = userService.syncAccountBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Bug用户", tags = {"用户" } ,notes = "获取Bug用户")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchbuguser")
	public ResponseEntity<List<UserDTO>> fetchbuguser(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchBugUser(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Bug用户", tags = {"用户" } ,notes = "查询Bug用户")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchbuguser")
	public ResponseEntity<Page<UserDTO>> searchBugUser(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchBugUser(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用户" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchdefault")
	public ResponseEntity<List<UserDTO>> fetchdefault(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchDefault(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"用户" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchdefault")
	public ResponseEntity<Page<UserDTO>> searchDefault(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根据源代码账户获取登录名", tags = {"用户" } ,notes = "获取根据源代码账户获取登录名")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchgetbycommiter")
	public ResponseEntity<List<UserDTO>> fetchgetbycommiter(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchGetByCommiter(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询根据源代码账户获取登录名", tags = {"用户" } ,notes = "查询根据源代码账户获取登录名")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchgetbycommiter")
	public ResponseEntity<Page<UserDTO>> searchGetByCommiter(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchGetByCommiter(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目团队管理", tags = {"用户" } ,notes = "获取项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchprojectteamm")
	public ResponseEntity<List<UserDTO>> fetchprojectteamm(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamM(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目团队管理", tags = {"用户" } ,notes = "查询项目团队管理")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchprojectteamm")
	public ResponseEntity<Page<UserDTO>> searchProjectTeamM(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamM(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目团队成员", tags = {"用户" } ,notes = "获取项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchprojectteamuser")
	public ResponseEntity<List<UserDTO>> fetchprojectteamuser(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamUser(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目团队成员", tags = {"用户" } ,notes = "查询项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchprojectteamuser")
	public ResponseEntity<Page<UserDTO>> searchProjectTeamUser(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamUser(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目团队成员", tags = {"用户" } ,notes = "获取项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchprojectteamusertask")
	public ResponseEntity<List<UserDTO>> fetchprojectteamusertask(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamUserTask(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目团队成员", tags = {"用户" } ,notes = "查询项目团队成员")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchprojectteamusertask")
	public ResponseEntity<Page<UserDTO>> searchProjectTeamUserTask(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchProjectTeamUserTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "获取TASKTEAM", tags = {"用户" } ,notes = "获取TASKTEAM")
    @RequestMapping(method= RequestMethod.POST , value="/users/fetchtaskteam")
	public ResponseEntity<List<UserDTO>> fetchtaskteam(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchTaskTeam(context) ;
        List<UserDTO> list = userMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserRuntime.quickTest('READ')")
	@ApiOperation(value = "查询TASKTEAM", tags = {"用户" } ,notes = "查询TASKTEAM")
    @RequestMapping(method= RequestMethod.POST , value="/users/searchtaskteam")
	public ResponseEntity<Page<UserDTO>> searchTaskTeam(@RequestBody UserSearchContext context) {
        userRuntime.addAuthorityConditions(context,"READ");
        Page<User> domains = userService.searchTaskTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(userMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/users/{user_id}/{action}")
    public ResponseEntity<UserDTO> dynamicCall(@PathVariable("user_id") Long user_id , @PathVariable("action") String action , @RequestBody UserDTO userdto) {
        User domain = userService.dynamicCall(user_id, action, userMapping.toDomain(userdto));
        userdto = userMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(userdto);
    }
}

