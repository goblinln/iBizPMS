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
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProToDoActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProToDoActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProToDoActionRuntime;

@Slf4j
@Api(tags = {"ToDo日志" })
@RestController("WebApi-ibzprotodoaction")
@RequestMapping("")
public class IBZProToDoActionResource {

    @Autowired
    public IIBZProToDoActionService ibzprotodoactionService;

    @Autowired
    public IBZProToDoActionRuntime ibzprotodoactionRuntime;

    @Autowired
    @Lazy
    public IBZProToDoActionMapping ibzprotodoactionMapping;

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'CREATE')")
    @ApiOperation(value = "新建ToDo日志", tags = {"ToDo日志" },  notes = "新建ToDo日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions")
    @Transactional
    public ResponseEntity<IBZProToDoActionDTO> create(@Validated @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
		ibzprotodoactionService.create(domain);
        if(!ibzprotodoactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProToDoActionDTO dto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id, 'UPDATE')")
    @ApiOperation(value = "更新ToDo日志", tags = {"ToDo日志" },  notes = "更新ToDo日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodoactions/{ibzprotodoaction_id}")
    @Transactional
    public ResponseEntity<IBZProToDoActionDTO> update(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
		IBZProToDoAction domain  = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
		ibzprotodoactionService.update(domain );
        if(!ibzprotodoactionRuntime.test(ibzprotodoaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProToDoActionDTO dto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(ibzprotodoaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id, 'DELETE')")
    @ApiOperation(value = "删除ToDo日志", tags = {"ToDo日志" },  notes = "删除ToDo日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodoactions/{ibzprotodoaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactionService.remove(ibzprotodoaction_id));
    }


    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id, 'READ')")
    @ApiOperation(value = "获取ToDo日志", tags = {"ToDo日志" },  notes = "获取ToDo日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodoactions/{ibzprotodoaction_id}")
    public ResponseEntity<IBZProToDoActionDTO> get(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id) {
        IBZProToDoAction domain = ibzprotodoactionService.get(ibzprotodoaction_id);
        IBZProToDoActionDTO dto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(ibzprotodoaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'CREATE')")
    @ApiOperation(value = "获取ToDo日志草稿", tags = {"ToDo日志" },  notes = "获取ToDo日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodoactions/getdraft")
    public ResponseEntity<IBZProToDoActionDTO> getDraft(IBZProToDoActionDTO dto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactionMapping.toDto(ibzprotodoactionService.getDraft(domain)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'CREATE')")
    @ApiOperation(value = "检查ToDo日志", tags = {"ToDo日志" },  notes = "检查ToDo日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactionService.checkKey(ibzprotodoactionMapping.toDomain(ibzprotodoactiondto)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id, 'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"ToDo日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/comment")
    public ResponseEntity<IBZProToDoActionDTO> comment(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.comment(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"ToDo日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/createhis")
    public ResponseEntity<IBZProToDoActionDTO> createHis(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.createHis(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id, 'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"ToDo日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/editcomment")
    public ResponseEntity<IBZProToDoActionDTO> editComment(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.editComment(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "Pms企业专用", tags = {"ToDo日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/managepmsee")
    public ResponseEntity<IBZProToDoActionDTO> managePmsEe(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.managePmsEe(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存ToDo日志", tags = {"ToDo日志" },  notes = "保存ToDo日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/save")
    public ResponseEntity<IBZProToDoActionDTO> save(@RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        ibzprotodoactionService.save(domain);
        IBZProToDoActionDTO dto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "已读", tags = {"ToDo日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/sendmarkdone")
    public ResponseEntity<IBZProToDoActionDTO> sendMarkDone(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.sendMarkDone(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待办", tags = {"ToDo日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/sendtodo")
    public ResponseEntity<IBZProToDoActionDTO> sendTodo(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.sendTodo(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待阅", tags = {"ToDo日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/sendtoread")
    public ResponseEntity<IBZProToDoActionDTO> sendToread(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionMapping.toDomain(ibzprotodoactiondto);
        domain.setId(ibzprotodoaction_id);
        domain = ibzprotodoactionService.sendToread(domain);
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodoactionRuntime.getOPPrivs(domain.getId());
        ibzprotodoactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }


    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"ToDo日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodoactions/fetchdefault")
	public ResponseEntity<List<IBZProToDoActionDTO>> fetchdefault(@RequestBody IBZProToDoActionSearchContext context) {
        Page<IBZProToDoAction> domains = ibzprotodoactionService.searchDefault(context) ;
        List<IBZProToDoActionDTO> list = ibzprotodoactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBZProToDoActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"ToDo日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodoactions/fetchtype")
	public ResponseEntity<List<IBZProToDoActionDTO>> fetchtype(@RequestBody IBZProToDoActionSearchContext context) {
        Page<IBZProToDoAction> domains = ibzprotodoactionService.searchType(context) ;
        List<IBZProToDoActionDTO> list = ibzprotodoactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/{action}")
    public ResponseEntity<IBZProToDoActionDTO> dynamicCall(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id , @PathVariable("action") String action , @RequestBody IBZProToDoActionDTO ibzprotodoactiondto) {
        IBZProToDoAction domain = ibzprotodoactionService.dynamicCall(ibzprotodoaction_id, action, ibzprotodoactionMapping.toDomain(ibzprotodoactiondto));
        ibzprotodoactiondto = ibzprotodoactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodoactiondto);
    }
}

