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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBugAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBugActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBugActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProBugActionRuntime;

@Slf4j
@Api(tags = {"Bug日志" })
@RestController("WebApi-ibzprobugaction")
@RequestMapping("")
public class IbzProBugActionResource {

    @Autowired
    public IIbzProBugActionService ibzprobugactionService;

    @Autowired
    public IbzProBugActionRuntime ibzprobugactionRuntime;

    @Autowired
    @Lazy
    public IbzProBugActionMapping ibzprobugactionMapping;

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建Bug日志", tags = {"Bug日志" },  notes = "新建Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions")
    @Transactional
    public ResponseEntity<IbzProBugActionDTO> create(@Validated @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
		ibzprobugactionService.create(domain);
        if(!ibzprobugactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建Bug日志", tags = {"Bug日志" },  notes = "批量新建Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        ibzprobugactionService.createBatch(ibzprobugactionMapping.toDomain(ibzprobugactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'UPDATE')")
    @ApiOperation(value = "更新Bug日志", tags = {"Bug日志" },  notes = "更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobugactions/{ibzprobugaction_id}")
    @Transactional
    public ResponseEntity<IbzProBugActionDTO> update(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
		IbzProBugAction domain  = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
		ibzprobugactionService.update(domain );
        if(!ibzprobugactionRuntime.test(ibzprobugaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(ibzprobugaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新Bug日志", tags = {"Bug日志" },  notes = "批量更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobugactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        ibzprobugactionService.updateBatch(ibzprobugactionMapping.toDomain(ibzprobugactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'DELETE')")
    @ApiOperation(value = "删除Bug日志", tags = {"Bug日志" },  notes = "删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.remove(ibzprobugaction_id));
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除Bug日志", tags = {"Bug日志" },  notes = "批量删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobugactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'READ')")
    @ApiOperation(value = "获取Bug日志", tags = {"Bug日志" },  notes = "获取Bug日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> get(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
        IbzProBugAction domain = ibzprobugactionService.get(ibzprobugaction_id);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(ibzprobugaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取Bug日志草稿", tags = {"Bug日志" },  notes = "获取Bug日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/getdraft")
    public ResponseEntity<IbzProBugActionDTO> getDraft(IbzProBugActionDTO dto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(ibzprobugactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查Bug日志", tags = {"Bug日志" },  notes = "检查Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.checkKey(ibzprobugactionMapping.toDomain(ibzprobugactiondto)));
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"Bug日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/comment")
    public ResponseEntity<IbzProBugActionDTO> comment(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.comment(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"Bug日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/createhis")
    public ResponseEntity<IbzProBugActionDTO> createHis(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.createHis(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"Bug日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"Bug日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/editcomment")
    public ResponseEntity<IbzProBugActionDTO> editComment(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.editComment(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"Bug日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"Bug日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/managepmsee")
    public ResponseEntity<IbzProBugActionDTO> managePmsEe(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.managePmsEe(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"Bug日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存Bug日志", tags = {"Bug日志" },  notes = "保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/save")
    public ResponseEntity<IbzProBugActionDTO> save(@RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        ibzprobugactionService.save(domain);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存Bug日志", tags = {"Bug日志" },  notes = "批量保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        ibzprobugactionService.saveBatch(ibzprobugactionMapping.toDomain(ibzprobugactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"Bug日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/sendmarkdone")
    public ResponseEntity<IbzProBugActionDTO> sendMarkDone(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendMarkDone(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"Bug日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"Bug日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/sendtodo")
    public ResponseEntity<IbzProBugActionDTO> sendTodo(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendTodo(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"Bug日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"Bug日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/sendtoread")
    public ResponseEntity<IbzProBugActionDTO> sendToread(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendToread(domain);
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(domain.getId());
        ibzprobugactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"Bug日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"Bug日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/fetchdefault")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchdefault(@RequestBody IbzProBugActionSearchContext context) {
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"Bug日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/searchdefault")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchDefault(@RequestBody IbzProBugActionSearchContext context) {
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/fetchtype")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchtype(@RequestBody IbzProBugActionSearchContext context) {
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/searchtype")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchType(@RequestBody IbzProBugActionSearchContext context) {
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/{action}")
    public ResponseEntity<IbzProBugActionDTO> dynamicCall(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id , @PathVariable("action") String action , @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionService.dynamicCall(ibzprobugaction_id, action, ibzprobugactionMapping.toDomain(ibzprobugactiondto));
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug建立Bug日志", tags = {"Bug日志" },  notes = "根据Bug建立Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions")
    public ResponseEntity<IbzProBugActionDTO> createByBug(@PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
		ibzprobugactionService.create(domain);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug批量建立Bug日志", tags = {"Bug日志" },  notes = "根据Bug批量建立Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> createBatchByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
            domain.setObjectid(bug_id);
        }
        ibzprobugactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据Bug更新Bug日志", tags = {"Bug日志" },  notes = "根据Bug更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> updateByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
		ibzprobugactionService.update(domain);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据Bug批量更新Bug日志", tags = {"Bug日志" },  notes = "根据Bug批量更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> updateBatchByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
            domain.setObjectid(bug_id);
        }
        ibzprobugactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据Bug删除Bug日志", tags = {"Bug日志" },  notes = "根据Bug删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<Boolean> removeByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.remove(ibzprobugaction_id));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据Bug批量删除Bug日志", tags = {"Bug日志" },  notes = "根据Bug批量删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> removeBatchByBug(@RequestBody List<Long> ids) {
        ibzprobugactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据Bug获取Bug日志", tags = {"Bug日志" },  notes = "根据Bug获取Bug日志")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> getByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
        IbzProBugAction domain = ibzprobugactionService.get(ibzprobugaction_id);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据Bug获取Bug日志草稿", tags = {"Bug日志" },  notes = "根据Bug获取Bug日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/ibzprobugactions/getdraft")
    public ResponseEntity<IbzProBugActionDTO> getDraftByBug(@PathVariable("bug_id") Long bug_id, IbzProBugActionDTO dto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(dto);
        domain.setObjectid(bug_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(ibzprobugactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据Bug检查Bug日志", tags = {"Bug日志" },  notes = "根据Bug检查Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByBug(@PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.checkKey(ibzprobugactionMapping.toDomain(ibzprobugactiondto)));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'MANAGE')")
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/comment")
    public ResponseEntity<IbzProBugActionDTO> commentByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.comment(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/createhis")
    public ResponseEntity<IbzProBugActionDTO> createHisByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.createHis(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@BugRuntime.test(#bug_id,'MANAGE')")
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/editcomment")
    public ResponseEntity<IbzProBugActionDTO> editCommentByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.editComment(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/managepmsee")
    public ResponseEntity<IbzProBugActionDTO> managePmsEeByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.managePmsEe(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据Bug保存Bug日志", tags = {"Bug日志" },  notes = "根据Bug保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/save")
    public ResponseEntity<IbzProBugActionDTO> saveByBug(@PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        ibzprobugactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据Bug批量保存Bug日志", tags = {"Bug日志" },  notes = "根据Bug批量保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
             domain.setObjectid(bug_id);
        }
        ibzprobugactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendmarkdone")
    public ResponseEntity<IbzProBugActionDTO> sendMarkDoneByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendMarkDone(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendtodo")
    public ResponseEntity<IbzProBugActionDTO> sendTodoByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendTodo(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据BugBug日志", tags = {"Bug日志" },  notes = "根据BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendtoread")
    public ResponseEntity<IbzProBugActionDTO> sendToreadByBug(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendToread(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByBug(@PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取数据集", tags = {"Bug日志" } ,notes = "根据Bug获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/fetchdefault")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchIbzProBugActionDefaultByBug(@PathVariable("bug_id") Long bug_id,@RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug查询数据集", tags = {"Bug日志" } ,notes = "根据Bug查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/searchdefault")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchIbzProBugActionDefaultByBug(@PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug获取动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "根据Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/fetchtype")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchIbzProBugActionTypeByBug(@PathVariable("bug_id") Long bug_id,@RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug查询动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "根据Bug查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/searchtype")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchIbzProBugActionTypeByBug(@PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug建立Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug建立Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions")
    public ResponseEntity<IbzProBugActionDTO> createByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
		ibzprobugactionService.create(domain);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug批量建立Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug批量建立Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> createBatchByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
            domain.setObjectid(bug_id);
        }
        ibzprobugactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品Bug更新Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> updateByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
		ibzprobugactionService.update(domain);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品Bug批量更新Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug批量更新Bug日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
            domain.setObjectid(bug_id);
        }
        ibzprobugactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品Bug删除Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<Boolean> removeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.remove(ibzprobugaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品Bug批量删除Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug批量删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductBug(@RequestBody List<Long> ids) {
        ibzprobugactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品Bug获取Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug获取Bug日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> getByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
        IbzProBugAction domain = ibzprobugactionService.get(ibzprobugaction_id);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品Bug获取Bug日志草稿", tags = {"Bug日志" },  notes = "根据产品Bug获取Bug日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/getdraft")
    public ResponseEntity<IbzProBugActionDTO> getDraftByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, IbzProBugActionDTO dto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(dto);
        domain.setObjectid(bug_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(ibzprobugactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品Bug检查Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug检查Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.checkKey(ibzprobugactionMapping.toDomain(ibzprobugactiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/comment")
    public ResponseEntity<IbzProBugActionDTO> commentByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.comment(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/createhis")
    public ResponseEntity<IbzProBugActionDTO> createHisByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.createHis(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/editcomment")
    public ResponseEntity<IbzProBugActionDTO> editCommentByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.editComment(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/managepmsee")
    public ResponseEntity<IbzProBugActionDTO> managePmsEeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.managePmsEe(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品Bug保存Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/save")
    public ResponseEntity<IbzProBugActionDTO> saveByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        ibzprobugactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品Bug批量保存Bug日志", tags = {"Bug日志" },  notes = "根据产品Bug批量保存Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domainlist=ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        for(IbzProBugAction domain:domainlist){
             domain.setObjectid(bug_id);
        }
        ibzprobugactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendmarkdone")
    public ResponseEntity<IbzProBugActionDTO> sendMarkDoneByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendMarkDone(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendtodo")
    public ResponseEntity<IbzProBugActionDTO> sendTodoByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendTodo(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品BugBug日志", tags = {"Bug日志" },  notes = "根据产品BugBug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/sendtoread")
    public ResponseEntity<IbzProBugActionDTO> sendToreadByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(ibzprobugactiondto);
        domain.setObjectid(bug_id);
        domain.setId(ibzprobugaction_id);
        domain = ibzprobugactionService.sendToread(domain) ;
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品BugBug日志]", tags = {"Bug日志" },  notes = "批量处理[根据产品BugBug日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody List<IbzProBugActionDTO> ibzprobugactiondtos) {
        List<IbzProBugAction> domains = ibzprobugactionMapping.toDomain(ibzprobugactiondtos);
        boolean result = ibzprobugactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取数据集", tags = {"Bug日志" } ,notes = "根据产品Bug获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/fetchdefault")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchIbzProBugActionDefaultByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug查询数据集", tags = {"Bug日志" } ,notes = "根据产品Bug查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/searchdefault")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchIbzProBugActionDefaultByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug获取动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "根据产品Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/fetchtype")
	public ResponseEntity<List<IbzProBugActionDTO>> fetchIbzProBugActionTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
        List<IbzProBugActionDTO> list = ibzprobugactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug查询动态(根据类型过滤)", tags = {"Bug日志" } ,notes = "根据产品Bug查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/searchtype")
	public ResponseEntity<Page<IbzProBugActionDTO>> searchIbzProBugActionTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @RequestBody IbzProBugActionSearchContext context) {
        context.setN_objectid_eq(bug_id);
        Page<IbzProBugAction> domains = ibzprobugactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobugactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

