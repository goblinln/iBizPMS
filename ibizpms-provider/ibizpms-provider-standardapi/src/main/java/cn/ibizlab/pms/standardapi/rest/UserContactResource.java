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


    @PreAuthorize("@UserContactRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"用户联系方式" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/usercontacts/fetchmy")
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
    @PreAuthorize("@UserContactRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据系统用户获取用户联系方式草稿", tags = {"用户联系方式" },  notes = "根据系统用户获取用户联系方式草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/usercontacts/getdraft")
    public ResponseEntity<UserContactDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, UserContactDTO dto) {
        UserContact domain = usercontactMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(usercontactMapping.toDto(usercontactService.getDraft(domain)));
    }

    @PreAuthorize("@UserContactRuntime.test(#usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户更新用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户更新用户联系方式")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id, @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
        
        domain.setId(usercontact_id);
		usercontactService.update(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@UserContactRuntime.test(#usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户获取用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户获取用户联系方式")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id) {
        UserContact domain = usercontactService.get(usercontact_id);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@UserContactRuntime.quickTest('READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"用户联系方式" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/usercontacts/fetchaccount")
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
    @PreAuthorize("@UserContactRuntime.test(#usercontact_id, 'NONE')")
    @ApiOperation(value = "根据系统用户删除用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/usercontacts/{usercontact_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usercontact_id") Long usercontact_id) {
		return ResponseEntity.status(HttpStatus.OK).body(usercontactService.remove(usercontact_id));
    }

    @PreAuthorize("@UserContactRuntime.quickTest('NONE')")
    @ApiOperation(value = "根据系统用户批量删除用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户批量删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/usercontacts/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        usercontactService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@UserContactRuntime.quickTest('NONE')")
    @ApiOperation(value = "根据系统用户建立用户联系方式", tags = {"用户联系方式" },  notes = "根据系统用户建立用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/usercontacts")
    public ResponseEntity<UserContactDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
        
		usercontactService.create(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

