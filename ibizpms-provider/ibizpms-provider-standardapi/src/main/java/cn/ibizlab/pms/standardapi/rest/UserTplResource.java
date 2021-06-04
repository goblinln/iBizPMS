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
import cn.ibizlab.pms.core.zentao.domain.UserTpl;
import cn.ibizlab.pms.core.zentao.service.IUserTplService;
import cn.ibizlab.pms.core.zentao.filter.UserTplSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.UserTplRuntime;

@Slf4j
@Api(tags = {"用户模板" })
@RestController("StandardAPI-usertpl")
@RequestMapping("")
public class UserTplResource {

    @Autowired
    public IUserTplService usertplService;

    @Autowired
    public UserTplRuntime usertplRuntime;

    @Autowired
    @Lazy
    public UserTplMapping usertplMapping;


    @PreAuthorize("test('ZT_USERTPL', #usertpl_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新用户模板", tags = {"用户模板" },  notes = "根据系统用户更新用户模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/usertpls/{usertpl_id}")
    public ResponseEntity<UserTplDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usertpl_id") Long usertpl_id, @RequestBody UserTplDTO usertpldto) {
        UserTpl domain = usertplMapping.toDomain(usertpldto);
        
        domain.setId(usertpl_id);
		usertplService.update(domain);
        if(!usertplRuntime.test(domain.getId(),"UPDATE"))
            throw new RuntimeException("无权限操作");
        UserTplDTO dto = usertplMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_USERTPL','READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"用户模板" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/usertpls/fetchaccount")
	public ResponseEntity<List<UserTplDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody UserTplSearchContext context) {
        
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchAccount(context) ;
        List<UserTplDTO> list = usertplMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_USERTPL','CREATE')")
    @ApiOperation(value = "根据系统用户建立用户模板", tags = {"用户模板" },  notes = "根据系统用户建立用户模板")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/usertpls")
    public ResponseEntity<UserTplDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody UserTplDTO usertpldto) {
        UserTpl domain = usertplMapping.toDomain(usertpldto);
        
		usertplService.create(domain);
        if(!usertplRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        UserTplDTO dto = usertplMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_USERTPL', #usertpl_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户删除用户模板", tags = {"用户模板" },  notes = "根据系统用户删除用户模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/usertpls/{usertpl_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usertpl_id") Long usertpl_id) {
		return ResponseEntity.status(HttpStatus.OK).body(usertplService.remove(usertpl_id));
    }

    @PreAuthorize("quickTest('ZT_USERTPL','DELETE')")
    @ApiOperation(value = "根据系统用户批量删除用户模板", tags = {"用户模板" },  notes = "根据系统用户批量删除用户模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/usertpls/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        usertplService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_USERTPL','CREATE')")
    @ApiOperation(value = "根据系统用户获取用户模板草稿", tags = {"用户模板" },  notes = "根据系统用户获取用户模板草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/usertpls/getdraft")
    public ResponseEntity<UserTplDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, UserTplDTO dto) {
        UserTpl domain = usertplMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(usertplMapping.toDto(usertplService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_USERTPL','READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"用户模板" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/usertpls/fetchmy")
	public ResponseEntity<List<UserTplDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody UserTplSearchContext context) {
        
        usertplRuntime.addAuthorityConditions(context,"READ");
        Page<UserTpl> domains = usertplService.searchMy(context) ;
        List<UserTplDTO> list = usertplMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_USERTPL', #usertpl_id, 'READ')")
    @ApiOperation(value = "根据系统用户获取用户模板", tags = {"用户模板" },  notes = "根据系统用户获取用户模板")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/usertpls/{usertpl_id}")
    public ResponseEntity<UserTplDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("usertpl_id") Long usertpl_id) {
        UserTpl domain = usertplService.get(usertpl_id);
        UserTplDTO dto = usertplMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}

