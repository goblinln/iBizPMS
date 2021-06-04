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
import cn.ibizlab.pms.core.zentao.domain.UserContact;
import cn.ibizlab.pms.core.zentao.service.IUserContactService;
import cn.ibizlab.pms.core.zentao.filter.UserContactSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.UserContactRuntime;

@Slf4j
@Api(tags = {"用户联系方式" })
@RestController("StandardAPI-usercontact")
@RequestMapping("")
public class UserContactResource {

    @Autowired
    public IUserContactService usercontactService;

    @Autowired
    public UserContactRuntime usercontactRuntime;

    @Autowired
    @Lazy
    public UserContactMapping usercontactMapping;

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "获取用户联系方式", tags = {"用户联系方式" },  notes = "获取用户联系方式")
	@RequestMapping(method = RequestMethod.GET, value = "/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> get(@PathVariable("usercontact_id") Long usercontact_id) {
        UserContact domain = usercontactService.get(usercontact_id);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        Map<String,Integer> opprivs = usercontactRuntime.getOPPrivs(usercontact_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "更新用户联系方式", tags = {"用户联系方式" },  notes = "更新用户联系方式")
	@RequestMapping(method = RequestMethod.PUT, value = "/usercontacts/{usercontact_id}")
    @Transactional
    public ResponseEntity<UserContactDTO> update(@PathVariable("usercontact_id") Long usercontact_id, @RequestBody UserContactDTO usercontactdto) {
		UserContact domain  = usercontactMapping.toDomain(usercontactdto);
        domain.setId(usercontact_id);
		usercontactService.update(domain );
		UserContactDTO dto = usercontactMapping.toDto(domain);
        Map<String,Integer> opprivs = usercontactRuntime.getOPPrivs(usercontact_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_USERCONTACT', 'CREATE')")
    @ApiOperation(value = "获取用户联系方式草稿", tags = {"用户联系方式" },  notes = "获取用户联系方式草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/usercontacts/getdraft")
    public ResponseEntity<UserContactDTO> getDraft(UserContactDTO dto) {
        UserContact domain = usercontactMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(usercontactMapping.toDto(usercontactService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "删除用户联系方式", tags = {"用户联系方式" },  notes = "删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usercontacts/{usercontact_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("usercontact_id") Long usercontact_id) {
         return ResponseEntity.status(HttpStatus.OK).body(usercontactService.remove(usercontact_id));
    }

    @PreAuthorize("quickTest('ZT_USERCONTACT', 'NONE')")
    @ApiOperation(value = "批量删除用户联系方式", tags = {"用户联系方式" },  notes = "批量删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usercontacts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        usercontactService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_USERCONTACT', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"用户联系方式" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/usercontacts/fetchmy")
	public ResponseEntity<List<UserContactDTO>> fetchmy(@RequestBody UserContactSearchContext context) {
        usercontactRuntime.addAuthorityConditions(context,"READ");
        Page<UserContact> domains = usercontactService.searchMy(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_USERCONTACT', 'NONE')")
    @ApiOperation(value = "新建用户联系方式", tags = {"用户联系方式" },  notes = "新建用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts")
    @Transactional
    public ResponseEntity<UserContactDTO> create(@Validated @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
		usercontactService.create(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        Map<String,Integer> opprivs = usercontactRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_USERCONTACT', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"用户联系方式" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/usercontacts/fetchaccount")
	public ResponseEntity<List<UserContactDTO>> fetchaccount(@RequestBody UserContactSearchContext context) {
        usercontactRuntime.addAuthorityConditions(context,"READ");
        Page<UserContact> domains = usercontactService.searchAccount(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/usercontacts/{usercontact_id}/{action}")
    public ResponseEntity<UserContactDTO> dynamicCall(@PathVariable("usercontact_id") Long usercontact_id , @PathVariable("action") String action , @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactService.dynamicCall(usercontact_id, action, usercontactMapping.toDomain(usercontactdto));
        usercontactdto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(usercontactdto);
    }

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户获取用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户获取用户联系方式")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id) {
        UserContact domain = usercontactService.get(usercontact_id);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户更新用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户更新用户联系方式")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id, @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
        
        domain.setId(usercontact_id);
		usercontactService.update(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_USERCONTACT', 'CREATE')")
    @ApiOperation(value = "根据系统用户获取用户联系方式草稿", tags = {"用户联系方式" },  notes = "根据系统用户获取用户联系方式草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/usercontacts/getdraft")
    public ResponseEntity<UserContactDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, UserContactDTO dto) {
        UserContact domain = usercontactMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(usercontactMapping.toDto(usercontactService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_USERCONTACT', #usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户删除用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id) {
		return ResponseEntity.status(HttpStatus.OK).body(usercontactService.remove(usercontact_id));
    }

    @PreAuthorize("quickTest('ZT_USERCONTACT', 'NONE')")
    @ApiOperation(value = "根据系统用户批量删除用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户批量删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/usercontacts/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        usercontactService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_USERCONTACT', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"用户联系方式" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/usercontacts/fetchmy")
	public ResponseEntity<List<UserContactDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody UserContactSearchContext context) {
        
        usercontactRuntime.addAuthorityConditions(context,"READ");
        Page<UserContact> domains = usercontactService.searchMy(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_USERCONTACT', 'NONE')")
    @ApiOperation(value = "根据系统用户建立用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户建立用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/usercontacts")
    public ResponseEntity<UserContactDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
        
		usercontactService.create(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_USERCONTACT', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"用户联系方式" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/usercontacts/fetchaccount")
	public ResponseEntity<List<UserContactDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody UserContactSearchContext context) {
        
        usercontactRuntime.addAuthorityConditions(context,"READ");
        Page<UserContact> domains = usercontactService.searchAccount(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

