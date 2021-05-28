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
import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProReleaseActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProReleaseActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProReleaseActionRuntime;

@Slf4j
@Api(tags = {"发布日志" })
@RestController("WebApi-ibzproreleaseaction")
@RequestMapping("")
public class IBZProReleaseActionResource {

    @Autowired
    public IIBZProReleaseActionService ibzproreleaseactionService;

    @Autowired
    public IBZProReleaseActionRuntime ibzproreleaseactionRuntime;

    @Autowired
    @Lazy
    public IBZProReleaseActionMapping ibzproreleaseactionMapping;

    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建发布日志", tags = {"发布日志" },  notes = "新建发布日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions")
    @Transactional
    public ResponseEntity<IBZProReleaseActionDTO> create(@Validated @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
		ibzproreleaseactionService.create(domain);
        if(!ibzproreleaseactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProReleaseActionDTO dto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id, 'UPDATE')")
    @ApiOperation(value = "更新发布日志", tags = {"发布日志" },  notes = "更新发布日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}")
    @Transactional
    public ResponseEntity<IBZProReleaseActionDTO> update(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
		IBZProReleaseAction domain  = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
		ibzproreleaseactionService.update(domain );
        if(!ibzproreleaseactionRuntime.test(ibzproreleaseaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProReleaseActionDTO dto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(ibzproreleaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id, 'DELETE')")
    @ApiOperation(value = "删除发布日志", tags = {"发布日志" },  notes = "删除发布日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactionService.remove(ibzproreleaseaction_id));
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ids, 'DELETE')")
    @ApiOperation(value = "批量删除发布日志", tags = {"发布日志" },  notes = "批量删除发布日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleaseactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproreleaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id, 'READ')")
    @ApiOperation(value = "获取发布日志", tags = {"发布日志" },  notes = "获取发布日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}")
    public ResponseEntity<IBZProReleaseActionDTO> get(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id) {
        IBZProReleaseAction domain = ibzproreleaseactionService.get(ibzproreleaseaction_id);
        IBZProReleaseActionDTO dto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(ibzproreleaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取发布日志草稿", tags = {"发布日志" },  notes = "获取发布日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreleaseactions/getdraft")
    public ResponseEntity<IBZProReleaseActionDTO> getDraft(IBZProReleaseActionDTO dto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactionMapping.toDto(ibzproreleaseactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查发布日志", tags = {"发布日志" },  notes = "检查发布日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactionService.checkKey(ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id, 'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"发布日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/comment")
    public ResponseEntity<IBZProReleaseActionDTO> comment(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.comment(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id, 'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"发布日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/createhis")
    public ResponseEntity<IBZProReleaseActionDTO> createHis(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.createHis(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id, 'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"发布日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/editcomment")
    public ResponseEntity<IBZProReleaseActionDTO> editComment(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.editComment(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "Pms企业专用", tags = {"发布日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/managepmsee")
    public ResponseEntity<IBZProReleaseActionDTO> managePmsEe(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.managePmsEe(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存发布日志", tags = {"发布日志" },  notes = "保存发布日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/save")
    public ResponseEntity<IBZProReleaseActionDTO> save(@RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        ibzproreleaseactionService.save(domain);
        IBZProReleaseActionDTO dto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "已读", tags = {"发布日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZProReleaseActionDTO> sendMarkDone(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.sendMarkDone(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待办", tags = {"发布日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/sendtodo")
    public ResponseEntity<IBZProReleaseActionDTO> sendTodo(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.sendTodo(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待阅", tags = {"发布日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/sendtoread")
    public ResponseEntity<IBZProReleaseActionDTO> sendToread(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto);
        domain.setId(ibzproreleaseaction_id);
        domain = ibzproreleaseactionService.sendToread(domain);
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleaseactionRuntime.getOPPrivs(domain.getId());
        ibzproreleaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }


    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"发布日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleaseactions/fetchdefault")
	public ResponseEntity<List<IBZProReleaseActionDTO>> fetchdefault(@RequestBody IBZProReleaseActionSearchContext context) {
        Page<IBZProReleaseAction> domains = ibzproreleaseactionService.searchDefault(context) ;
        List<IBZProReleaseActionDTO> list = ibzproreleaseactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBZProReleaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"发布日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleaseactions/fetchtype")
	public ResponseEntity<List<IBZProReleaseActionDTO>> fetchtype(@RequestBody IBZProReleaseActionSearchContext context) {
        Page<IBZProReleaseAction> domains = ibzproreleaseactionService.searchType(context) ;
        List<IBZProReleaseActionDTO> list = ibzproreleaseactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/{action}")
    public ResponseEntity<IBZProReleaseActionDTO> dynamicCall(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id , @PathVariable("action") String action , @RequestBody IBZProReleaseActionDTO ibzproreleaseactiondto) {
        IBZProReleaseAction domain = ibzproreleaseactionService.dynamicCall(ibzproreleaseaction_id, action, ibzproreleaseactionMapping.toDomain(ibzproreleaseactiondto));
        ibzproreleaseactiondto = ibzproreleaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleaseactiondto);
    }
}

