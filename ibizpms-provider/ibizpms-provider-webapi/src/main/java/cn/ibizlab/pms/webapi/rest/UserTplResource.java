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
import cn.ibizlab.pms.core.zentao.domain.UserTpl;
import cn.ibizlab.pms.core.zentao.service.IUserTplService;
import cn.ibizlab.pms.core.zentao.filter.UserTplSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.UserTplRuntime;

@Slf4j
@Api(tags = {"用户模板" })
@RestController("WebApi-usertpl")
@RequestMapping("")
public class UserTplResource {

    @Autowired
    public IUserTplService usertplService;

    @Autowired
    public UserTplRuntime usertplRuntime;

    @Autowired
    @Lazy
    public UserTplMapping usertplMapping;

    @PreAuthorize("@UserTplRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建用户模板", tags = {"用户模板" },  notes = "新建用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/usertpls")
    @Transactional
    public ResponseEntity<UserTplDTO> create(@Validated @RequestBody UserTplDTO usertpldto) {
        UserTpl domain = usertplMapping.toDomain(usertpldto);
		usertplService.create(domain);
        if(!usertplRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        UserTplDTO dto = usertplMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@UserTplRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建用户模板", tags = {"用户模板" },  notes = "批量新建用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/usertpls/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<UserTplDTO> usertpldtos) {
        usertplService.createBatch(usertplMapping.toDomain(usertpldtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserTplRuntime.test(#usertpl_id,'UPDATE')")
    @ApiOperation(value = "更新用户模板", tags = {"用户模板" },  notes = "更新用户模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/usertpls/{usertpl_id}")
    @Transactional
    public ResponseEntity<UserTplDTO> update(@PathVariable("usertpl_id") Long usertpl_id, @RequestBody UserTplDTO usertpldto) {
		UserTpl domain  = usertplMapping.toDomain(usertpldto);
        domain.setId(usertpl_id);
		usertplService.update(domain );
        if(!usertplRuntime.test(usertpl_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		UserTplDTO dto = usertplMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@UserTplRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新用户模板", tags = {"用户模板" },  notes = "批量更新用户模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/usertpls/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<UserTplDTO> usertpldtos) {
        usertplService.updateBatch(usertplMapping.toDomain(usertpldtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserTplRuntime.test(#usertpl_id,'DELETE')")
    @ApiOperation(value = "删除用户模板", tags = {"用户模板" },  notes = "删除用户模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usertpls/{usertpl_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("usertpl_id") Long usertpl_id) {
         return ResponseEntity.status(HttpStatus.OK).body(usertplService.remove(usertpl_id));
    }

    @PreAuthorize("@UserTplRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除用户模板", tags = {"用户模板" },  notes = "批量删除用户模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usertpls/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        usertplService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserTplRuntime.test(#usertpl_id,'READ')")
    @ApiOperation(value = "获取用户模板", tags = {"用户模板" },  notes = "获取用户模板")
	@RequestMapping(method = RequestMethod.GET, value = "/usertpls/{usertpl_id}")
    public ResponseEntity<UserTplDTO> get(@PathVariable("usertpl_id") Long usertpl_id) {
        UserTpl domain = usertplService.get(usertpl_id);
        UserTplDTO dto = usertplMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用户模板草稿", tags = {"用户模板" },  notes = "获取用户模板草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/usertpls/getdraft")
    public ResponseEntity<UserTplDTO> getDraft(UserTplDTO dto) {
        UserTpl domain = usertplMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(usertplMapping.toDto(usertplService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用户模板", tags = {"用户模板" },  notes = "检查用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/usertpls/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody UserTplDTO usertpldto) {
        return  ResponseEntity.status(HttpStatus.OK).body(usertplService.checkKey(usertplMapping.toDomain(usertpldto)));
    }

    @ApiOperation(value = "保存用户模板", tags = {"用户模板" },  notes = "保存用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/usertpls/save")
    public ResponseEntity<UserTplDTO> save(@RequestBody UserTplDTO usertpldto) {
        UserTpl domain = usertplMapping.toDomain(usertpldto);
        usertplService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(usertplMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存用户模板", tags = {"用户模板" },  notes = "批量保存用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/usertpls/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<UserTplDTO> usertpldtos) {
        usertplService.saveBatch(usertplMapping.toDomain(usertpldtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserTplRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用户模板" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/usertpls/fetchdefault")
	public ResponseEntity<List<UserTplDTO>> fetchDefault(UserTplSearchContext context) {
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchDefault(context) ;
        List<UserTplDTO> list = usertplMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserTplRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"用户模板" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/usertpls/searchdefault")
	public ResponseEntity<Page<UserTplDTO>> searchDefault(@RequestBody UserTplSearchContext context) {
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(usertplMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@UserTplRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的模板", tags = {"用户模板" } ,notes = "获取我的模板")
    @RequestMapping(method= RequestMethod.GET , value="/usertpls/fetchmyusertpl")
	public ResponseEntity<List<UserTplDTO>> fetchMyUserTpl(UserTplSearchContext context) {
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchMyUserTpl(context) ;
        List<UserTplDTO> list = usertplMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@UserTplRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的模板", tags = {"用户模板" } ,notes = "查询我的模板")
    @RequestMapping(method= RequestMethod.POST , value="/usertpls/searchmyusertpl")
	public ResponseEntity<Page<UserTplDTO>> searchMyUserTpl(@RequestBody UserTplSearchContext context) {
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchMyUserTpl(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(usertplMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/usertpls/{usertpl_id}/{action}")
    public ResponseEntity<UserTplDTO> dynamicCall(@PathVariable("usertpl_id") Long usertpl_id , @PathVariable("action") String action , @RequestBody UserTplDTO usertpldto) {
        UserTpl domain = usertplService.dynamicCall(usertpl_id, action, usertplMapping.toDomain(usertpldto));
        usertpldto = usertplMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(usertpldto);
    }
}

