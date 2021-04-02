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
import cn.ibizlab.pms.core.zentao.domain.UserContact;
import cn.ibizlab.pms.core.zentao.service.IUserContactService;
import cn.ibizlab.pms.core.zentao.filter.UserContactSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"用户联系方式" })
@RestController("WebApi-usercontact")
@RequestMapping("")
public class UserContactResource {

    @Autowired
    public IUserContactService usercontactService;


    @Autowired
    @Lazy
    public UserContactMapping usercontactMapping;

    @ApiOperation(value = "新建用户联系方式", tags = {"用户联系方式" },  notes = "新建用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts")
    public ResponseEntity<UserContactDTO> create(@Validated @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
		usercontactService.create(domain);
        UserContactDTO dto = usercontactMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量新建用户联系方式", tags = {"用户联系方式" },  notes = "批量新建用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<UserContactDTO> usercontactdtos) {
        usercontactService.createBatch(usercontactMapping.toDomain(usercontactdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新用户联系方式", tags = {"用户联系方式" },  notes = "更新用户联系方式")
	@RequestMapping(method = RequestMethod.PUT, value = "/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> update(@PathVariable("usercontact_id") Long usercontact_id, @RequestBody UserContactDTO usercontactdto) {
		UserContact domain  = usercontactMapping.toDomain(usercontactdto);
        domain .setId(usercontact_id);
		usercontactService.update(domain );
		UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量更新用户联系方式", tags = {"用户联系方式" },  notes = "批量更新用户联系方式")
	@RequestMapping(method = RequestMethod.PUT, value = "/usercontacts/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<UserContactDTO> usercontactdtos) {
        usercontactService.updateBatch(usercontactMapping.toDomain(usercontactdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "删除用户联系方式", tags = {"用户联系方式" },  notes = "删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usercontacts/{usercontact_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("usercontact_id") Long usercontact_id) {
         return ResponseEntity.status(HttpStatus.OK).body(usercontactService.remove(usercontact_id));
    }

    @ApiOperation(value = "批量删除用户联系方式", tags = {"用户联系方式" },  notes = "批量删除用户联系方式")
	@RequestMapping(method = RequestMethod.DELETE, value = "/usercontacts/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        usercontactService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "获取用户联系方式", tags = {"用户联系方式" },  notes = "获取用户联系方式")
	@RequestMapping(method = RequestMethod.GET, value = "/usercontacts/{usercontact_id}")
    public ResponseEntity<UserContactDTO> get(@PathVariable("usercontact_id") Long usercontact_id) {
        UserContact domain = usercontactService.get(usercontact_id);
        UserContactDTO dto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用户联系方式草稿", tags = {"用户联系方式" },  notes = "获取用户联系方式草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/usercontacts/getdraft")
    public ResponseEntity<UserContactDTO> getDraft(UserContactDTO dto) {
        UserContact domain = usercontactMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(usercontactMapping.toDto(usercontactService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用户联系方式", tags = {"用户联系方式" },  notes = "检查用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody UserContactDTO usercontactdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(usercontactService.checkKey(usercontactMapping.toDomain(usercontactdto)));
    }

    @ApiOperation(value = "保存用户联系方式", tags = {"用户联系方式" },  notes = "保存用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts/save")
    public ResponseEntity<UserContactDTO> save(@RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactMapping.toDomain(usercontactdto);
        usercontactService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(usercontactMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存用户联系方式", tags = {"用户联系方式" },  notes = "批量保存用户联系方式")
	@RequestMapping(method = RequestMethod.POST, value = "/usercontacts/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<UserContactDTO> usercontactdtos) {
        usercontactService.saveBatch(usercontactMapping.toDomain(usercontactdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "获取抄送联系人", tags = {"用户联系方式" } ,notes = "获取抄送联系人")
    @RequestMapping(method= RequestMethod.GET , value="/usercontacts/fetchcurusercontact")
	public ResponseEntity<List<UserContactDTO>> fetchCurUSERCONTACT(UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchCurUSERCONTACT(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询抄送联系人", tags = {"用户联系方式" } ,notes = "查询抄送联系人")
    @RequestMapping(method= RequestMethod.POST , value="/usercontacts/searchcurusercontact")
	public ResponseEntity<Page<UserContactDTO>> searchCurUSERCONTACT(@RequestBody UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchCurUSERCONTACT(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(usercontactMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取DEFAULT", tags = {"用户联系方式" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/usercontacts/fetchdefault")
	public ResponseEntity<List<UserContactDTO>> fetchDefault(UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchDefault(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询DEFAULT", tags = {"用户联系方式" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/usercontacts/searchdefault")
	public ResponseEntity<Page<UserContactDTO>> searchDefault(@RequestBody UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(usercontactMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我的联系人", tags = {"用户联系方式" } ,notes = "获取我的联系人")
    @RequestMapping(method= RequestMethod.GET , value="/usercontacts/fetchmyusercontact")
	public ResponseEntity<List<UserContactDTO>> fetchMyUSERCONTACT(UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchMyUSERCONTACT(context) ;
        List<UserContactDTO> list = usercontactMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我的联系人", tags = {"用户联系方式" } ,notes = "查询我的联系人")
    @RequestMapping(method= RequestMethod.POST , value="/usercontacts/searchmyusercontact")
	public ResponseEntity<Page<UserContactDTO>> searchMyUSERCONTACT(@RequestBody UserContactSearchContext context) {
        Page<UserContact> domains = usercontactService.searchMyUSERCONTACT(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(usercontactMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/usercontacts/{usercontact_id}/{action}")
    public ResponseEntity<UserContactDTO> dynamicCall(@PathVariable("usercontact_id") Long usercontact_id , @PathVariable("action") String action , @RequestBody UserContactDTO usercontactdto) {
        UserContact domain = usercontactService.dynamicCall(usercontact_id, action, usercontactMapping.toDomain(usercontactdto));
        usercontactdto = usercontactMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(usercontactdto);
    }

}

