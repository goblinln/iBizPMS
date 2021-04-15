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
import cn.ibizlab.pms.core.zentao.domain.Group;
import cn.ibizlab.pms.core.zentao.service.IGroupService;
import cn.ibizlab.pms.core.zentao.filter.GroupSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.GroupRuntime;

@Slf4j
@Api(tags = {"群组" })
@RestController("WebApi-group")
@RequestMapping("")
public class GroupResource {

    @Autowired
    public IGroupService groupService;

    @Autowired
    public GroupRuntime groupRuntime;

    @Autowired
    @Lazy
    public GroupMapping groupMapping;

    @PreAuthorize("@GroupRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建群组", tags = {"群组" },  notes = "新建群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups")
    @Transactional
    public ResponseEntity<GroupDTO> create(@Validated @RequestBody GroupDTO groupdto) {
        Group domain = groupMapping.toDomain(groupdto);
		groupService.create(domain);
        if(!groupRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        GroupDTO dto = groupMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@GroupRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建群组", tags = {"群组" },  notes = "批量新建群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<GroupDTO> groupdtos) {
        groupService.createBatch(groupMapping.toDomain(groupdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@GroupRuntime.test(#group_id,'UPDATE')")
    @ApiOperation(value = "更新群组", tags = {"群组" },  notes = "更新群组")
	@RequestMapping(method = RequestMethod.PUT, value = "/groups/{group_id}")
    @Transactional
    public ResponseEntity<GroupDTO> update(@PathVariable("group_id") Long group_id, @RequestBody GroupDTO groupdto) {
		Group domain  = groupMapping.toDomain(groupdto);
        domain.setId(group_id);
		groupService.update(domain );
        if(!groupRuntime.test(group_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		GroupDTO dto = groupMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@GroupRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新群组", tags = {"群组" },  notes = "批量更新群组")
	@RequestMapping(method = RequestMethod.PUT, value = "/groups/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<GroupDTO> groupdtos) {
        groupService.updateBatch(groupMapping.toDomain(groupdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@GroupRuntime.test(#group_id,'DELETE')")
    @ApiOperation(value = "删除群组", tags = {"群组" },  notes = "删除群组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/groups/{group_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("group_id") Long group_id) {
         return ResponseEntity.status(HttpStatus.OK).body(groupService.remove(group_id));
    }

    @PreAuthorize("@GroupRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除群组", tags = {"群组" },  notes = "批量删除群组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/groups/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        groupService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@GroupRuntime.test(#group_id,'READ')")
    @ApiOperation(value = "获取群组", tags = {"群组" },  notes = "获取群组")
	@RequestMapping(method = RequestMethod.GET, value = "/groups/{group_id}")
    public ResponseEntity<GroupDTO> get(@PathVariable("group_id") Long group_id) {
        Group domain = groupService.get(group_id);
        GroupDTO dto = groupMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取群组草稿", tags = {"群组" },  notes = "获取群组草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/groups/getdraft")
    public ResponseEntity<GroupDTO> getDraft(GroupDTO dto) {
        Group domain = groupMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(groupMapping.toDto(groupService.getDraft(domain)));
    }

    @ApiOperation(value = "检查群组", tags = {"群组" },  notes = "检查群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody GroupDTO groupdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(groupService.checkKey(groupMapping.toDomain(groupdto)));
    }

    @ApiOperation(value = "保存群组", tags = {"群组" },  notes = "保存群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/save")
    public ResponseEntity<GroupDTO> save(@RequestBody GroupDTO groupdto) {
        Group domain = groupMapping.toDomain(groupdto);
        groupService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(groupMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存群组", tags = {"群组" },  notes = "批量保存群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<GroupDTO> groupdtos) {
        groupService.saveBatch(groupMapping.toDomain(groupdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@GroupRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"群组" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/groups/fetchdefault")
	public ResponseEntity<List<GroupDTO>> fetchDefault(@RequestBody GroupSearchContext context) {
        groupRuntime.addAuthorityConditions(context,"READ");
        Page<Group> domains = groupService.searchDefault(context) ;
        List<GroupDTO> list = groupMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@GroupRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"群组" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/groups/searchdefault")
	public ResponseEntity<Page<GroupDTO>> searchDefault(@RequestBody GroupSearchContext context) {
        groupRuntime.addAuthorityConditions(context,"READ");
        Page<Group> domains = groupService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(groupMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/groups/{group_id}/{action}")
    public ResponseEntity<GroupDTO> dynamicCall(@PathVariable("group_id") Long group_id , @PathVariable("action") String action , @RequestBody GroupDTO groupdto) {
        Group domain = groupService.dynamicCall(group_id, action, groupMapping.toDomain(groupdto));
        groupdto = groupMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(groupdto);
    }
}

