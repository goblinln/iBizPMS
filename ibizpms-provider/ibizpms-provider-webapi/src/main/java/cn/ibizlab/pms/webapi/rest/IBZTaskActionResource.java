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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTaskActionRuntime;

@Slf4j
@Api(tags = {"任务日志" })
@RestController("WebApi-ibztaskaction")
@RequestMapping("")
public class IBZTaskActionResource {

    @Autowired
    public IIBZTaskActionService ibztaskactionService;

    @Autowired
    public IBZTaskActionRuntime ibztaskactionRuntime;

    @Autowired
    @Lazy
    public IBZTaskActionMapping ibztaskactionMapping;

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务日志", tags = {"任务日志" },  notes = "新建任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> create(@Validated @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
		ibztaskactionService.create(domain);
        if(!ibztaskactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'UPDATE')")
    @ApiOperation(value = "更新任务日志", tags = {"任务日志" },  notes = "更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/{ibztaskaction_id}")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> update(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
		IBZTaskAction domain  = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain );
        if(!ibztaskactionRuntime.test(ibztaskaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(ibztaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'DELETE')")
    @ApiOperation(value = "删除任务日志", tags = {"任务日志" },  notes = "删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }


    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
    @ApiOperation(value = "获取任务日志", tags = {"任务日志" },  notes = "获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> get(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(ibztaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取任务日志草稿", tags = {"任务日志" },  notes = "获取任务日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraft(IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查任务日志", tags = {"任务日志" },  notes = "检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "添加备注", tags = {"任务日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/comment")
    public ResponseEntity<IBZTaskActionDTO> comment(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.comment(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"任务日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/createhis")
    public ResponseEntity<IBZTaskActionDTO> createHis(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.createHis(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @ApiOperation(value = "编辑备注信息", tags = {"任务日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/editcomment")
    public ResponseEntity<IBZTaskActionDTO> editComment(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.editComment(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @ApiOperation(value = "Pms企业专用", tags = {"任务日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/managepmsee")
    public ResponseEntity<IBZTaskActionDTO> managePmsEe(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.managePmsEe(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @ApiOperation(value = "保存任务日志", tags = {"任务日志" },  notes = "保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> save(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        ibztaskactionService.save(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "已读", tags = {"任务日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/sendmarkdone")
    public ResponseEntity<IBZTaskActionDTO> sendMarkDone(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.sendMarkDone(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @ApiOperation(value = "发送待办", tags = {"任务日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/sendtodo")
    public ResponseEntity<IBZTaskActionDTO> sendTodo(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.sendTodo(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @ApiOperation(value = "发送待阅", tags = {"任务日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/sendtoread")
    public ResponseEntity<IBZTaskActionDTO> sendToread(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
        domain = ibztaskactionService.sendToread(domain);
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        ibztaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }


    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchdefault(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"任务日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchDefault(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchtype(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchType(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/{action}")
    public ResponseEntity<IBZTaskActionDTO> dynamicCall(@PathVariable("ibztaskaction_id") Long ibztaskaction_id , @PathVariable("action") String action , @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionService.dynamicCall(ibztaskaction_id, action, ibztaskactionMapping.toDomain(ibztaskactiondto));
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }
}

