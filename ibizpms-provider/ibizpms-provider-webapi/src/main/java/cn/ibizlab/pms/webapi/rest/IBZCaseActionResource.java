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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZCaseActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZCaseActionRuntime;

@Slf4j
@Api(tags = {"测试用例日志" })
@RestController("WebApi-ibzcaseaction")
@RequestMapping("")
public class IBZCaseActionResource {

    @Autowired
    public IIBZCaseActionService ibzcaseactionService;

    @Autowired
    public IBZCaseActionRuntime ibzcaseactionRuntime;

    @Autowired
    @Lazy
    public IBZCaseActionMapping ibzcaseactionMapping;

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试用例日志", tags = {"测试用例日志" },  notes = "新建测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions")
    @Transactional
    public ResponseEntity<IBZCaseActionDTO> create(@Validated @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
		ibzcaseactionService.create(domain);
        if(!ibzcaseactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试用例日志", tags = {"测试用例日志" },  notes = "批量新建测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.createBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'UPDATE')")
    @ApiOperation(value = "更新测试用例日志", tags = {"测试用例日志" },  notes = "更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/{ibzcaseaction_id}")
    @Transactional
    public ResponseEntity<IBZCaseActionDTO> update(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
		IBZCaseAction domain  = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain );
        if(!ibzcaseactionRuntime.test(ibzcaseaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(ibzcaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试用例日志", tags = {"测试用例日志" },  notes = "批量更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.updateBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'DELETE')")
    @ApiOperation(value = "删除测试用例日志", tags = {"测试用例日志" },  notes = "删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试用例日志", tags = {"测试用例日志" },  notes = "批量删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'READ')")
    @ApiOperation(value = "获取测试用例日志", tags = {"测试用例日志" },  notes = "获取测试用例日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> get(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(ibzcaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试用例日志草稿", tags = {"测试用例日志" },  notes = "获取测试用例日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraft(IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试用例日志", tags = {"测试用例日志" },  notes = "检查测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"测试用例日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/comment")
    public ResponseEntity<IBZCaseActionDTO> comment(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.comment(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"测试用例日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/createhis")
    public ResponseEntity<IBZCaseActionDTO> createHis(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.createHis(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"测试用例日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"测试用例日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/editcomment")
    public ResponseEntity<IBZCaseActionDTO> editComment(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.editComment(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"测试用例日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"测试用例日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/managepmsee")
    public ResponseEntity<IBZCaseActionDTO> managePmsEe(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.managePmsEe(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"测试用例日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存测试用例日志", tags = {"测试用例日志" },  notes = "保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> save(@RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        ibzcaseactionService.save(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试用例日志", tags = {"测试用例日志" },  notes = "批量保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.saveBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"测试用例日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZCaseActionDTO> sendMarkDone(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendMarkDone(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"测试用例日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"测试用例日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/sendtodo")
    public ResponseEntity<IBZCaseActionDTO> sendTodo(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendTodo(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"测试用例日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"测试用例日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/sendtoread")
    public ResponseEntity<IBZCaseActionDTO> sendToread(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendToread(domain);
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        ibzcaseactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"测试用例日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试用例日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchdefault(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试用例日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchDefault(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/fetchtype")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchtype(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/searchtype")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchType(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/{action}")
    public ResponseEntity<IBZCaseActionDTO> dynamicCall(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id , @PathVariable("action") String action , @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionService.dynamicCall(ibzcaseaction_id, action, ibzcaseactionMapping.toDomain(ibzcaseactiondto));
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例建立测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions")
    public ResponseEntity<IBZCaseActionDTO> createByCase(@PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
		ibzcaseactionService.create(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例批量建立测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例批量建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例更新测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> updateByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例批量更新测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例批量更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例删除测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> removeByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例批量删除测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例批量删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatchByCase(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例获取测试用例日志")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试用例获取测试用例日志草稿", tags = {"测试用例日志" },  notes = "根据测试用例获取测试用例日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraftByCase(@PathVariable("case_id") Long case_id, IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        domain.setObjectid(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试用例检查测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例检查测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByCase(@PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'MANAGE')")
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/comment")
    public ResponseEntity<IBZCaseActionDTO> commentByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.comment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/createhis")
    public ResponseEntity<IBZCaseActionDTO> createHisByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.createHis(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@CaseRuntime.test(#case_id,'MANAGE')")
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/editcomment")
    public ResponseEntity<IBZCaseActionDTO> editCommentByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.editComment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/managepmsee")
    public ResponseEntity<IBZCaseActionDTO> managePmsEeByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.managePmsEe(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试用例保存测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> saveByCase(@PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        ibzcaseactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试用例批量保存测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例批量保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
             domain.setObjectid(case_id);
        }
        ibzcaseactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZCaseActionDTO> sendMarkDoneByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendMarkDone(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtodo")
    public ResponseEntity<IBZCaseActionDTO> sendTodoByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendTodo(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtoread")
    public ResponseEntity<IBZCaseActionDTO> sendToreadByCase(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendToread(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByCase(@PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取数据集", tags = {"测试用例日志" } ,notes = "根据测试用例获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionDefaultByCase(@PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询数据集", tags = {"测试用例日志" } ,notes = "根据测试用例查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionDefaultByCase(@PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/fetchtype")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionTypeByCase(@PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据测试用例查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/searchtype")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionTypeByCase(@PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例建立测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions")
    public ResponseEntity<IBZCaseActionDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
		ibzcaseactionService.create(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例批量建立测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例批量建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例更新测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> updateByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例批量更新测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例批量更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例批量删除测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例批量删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductCase(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例获取测试用例日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试用例获取测试用例日志草稿", tags = {"测试用例日志" },  notes = "根据产品测试用例获取测试用例日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        domain.setObjectid(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试用例检查测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例检查测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/comment")
    public ResponseEntity<IBZCaseActionDTO> commentByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.comment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/createhis")
    public ResponseEntity<IBZCaseActionDTO> createHisByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.createHis(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/editcomment")
    public ResponseEntity<IBZCaseActionDTO> editCommentByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.editComment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/managepmsee")
    public ResponseEntity<IBZCaseActionDTO> managePmsEeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.managePmsEe(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试用例保存测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> saveByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        ibzcaseactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试用例批量保存测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例批量保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
             domain.setObjectid(case_id);
        }
        ibzcaseactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZCaseActionDTO> sendMarkDoneByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendMarkDone(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtodo")
    public ResponseEntity<IBZCaseActionDTO> sendTodoByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendTodo(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtoread")
    public ResponseEntity<IBZCaseActionDTO> sendToreadByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendToread(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取数据集", tags = {"测试用例日志" } ,notes = "根据产品测试用例获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询数据集", tags = {"测试用例日志" } ,notes = "根据产品测试用例查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据产品测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/fetchtype")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据产品测试用例查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/searchtype")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求测试用例建立测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions")
    public ResponseEntity<IBZCaseActionDTO> createByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
		ibzcaseactionService.create(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求测试用例批量建立测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例批量建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求测试用例更新测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> updateByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求测试用例批量更新测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例批量更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求测试用例删除测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> removeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求测试用例批量删除测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例批量删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatchByStoryCase(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求测试用例获取测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例获取测试用例日志")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> getByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求测试用例获取测试用例日志草稿", tags = {"测试用例日志" },  notes = "根据需求测试用例获取测试用例日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraftByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        domain.setObjectid(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求测试用例检查测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例检查测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'MANAGE')")
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/comment")
    public ResponseEntity<IBZCaseActionDTO> commentByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.comment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/createhis")
    public ResponseEntity<IBZCaseActionDTO> createHisByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.createHis(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'MANAGE')")
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/editcomment")
    public ResponseEntity<IBZCaseActionDTO> editCommentByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.editComment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/managepmsee")
    public ResponseEntity<IBZCaseActionDTO> managePmsEeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.managePmsEe(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求测试用例保存测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> saveByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        ibzcaseactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求测试用例批量保存测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例批量保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
             domain.setObjectid(case_id);
        }
        ibzcaseactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZCaseActionDTO> sendMarkDoneByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendMarkDone(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtodo")
    public ResponseEntity<IBZCaseActionDTO> sendTodoByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendTodo(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtoread")
    public ResponseEntity<IBZCaseActionDTO> sendToreadByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendToread(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例获取数据集", tags = {"测试用例日志" } ,notes = "根据需求测试用例获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionDefaultByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例查询数据集", tags = {"测试用例日志" } ,notes = "根据需求测试用例查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionDefaultByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例获取动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据需求测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/ibzcaseactions/fetchtype")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionTypeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求测试用例查询动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据需求测试用例查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/cases/{case_id}/ibzcaseactions/searchtype")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionTypeByStoryCase(@PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求测试用例建立测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions")
    public ResponseEntity<IBZCaseActionDTO> createByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
		ibzcaseactionService.create(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求测试用例批量建立测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例批量建立测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求测试用例更新测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> updateByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求测试用例批量更新测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例批量更新测试用例日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
            domain.setObjectid(case_id);
        }
        ibzcaseactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求测试用例删除测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> removeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求测试用例批量删除测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例批量删除测试用例日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryCase(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求测试用例获取测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例获取测试用例日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> getByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求测试用例获取测试用例日志草稿", tags = {"测试用例日志" },  notes = "根据产品需求测试用例获取测试用例日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraftByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        domain.setObjectid(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求测试用例检查测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例检查测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/comment")
    public ResponseEntity<IBZCaseActionDTO> commentByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.comment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/createhis")
    public ResponseEntity<IBZCaseActionDTO> createHisByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.createHis(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/editcomment")
    public ResponseEntity<IBZCaseActionDTO> editCommentByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.editComment(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/managepmsee")
    public ResponseEntity<IBZCaseActionDTO> managePmsEeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.managePmsEe(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求测试用例保存测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> saveByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        ibzcaseactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求测试用例批量保存测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例批量保存测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domainlist=ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        for(IBZCaseAction domain:domainlist){
             domain.setObjectid(case_id);
        }
        ibzcaseactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendmarkdone")
    public ResponseEntity<IBZCaseActionDTO> sendMarkDoneByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendMarkDone(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtodo")
    public ResponseEntity<IBZCaseActionDTO> sendTodoByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendTodo(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品需求测试用例测试用例日志", tags = {"测试用例日志" },  notes = "根据产品需求测试用例测试用例日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/sendtoread")
    public ResponseEntity<IBZCaseActionDTO> sendToreadByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setObjectid(case_id);
        domain.setId(ibzcaseaction_id);
        domain = ibzcaseactionService.sendToread(domain) ;
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品需求测试用例测试用例日志]", tags = {"测试用例日志" },  notes = "批量处理[根据产品需求测试用例测试用例日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        List<IBZCaseAction> domains = ibzcaseactionMapping.toDomain(ibzcaseactiondtos);
        boolean result = ibzcaseactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例获取数据集", tags = {"测试用例日志" } ,notes = "根据产品需求测试用例获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionDefaultByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例查询数据集", tags = {"测试用例日志" } ,notes = "根据产品需求测试用例查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionDefaultByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例获取动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据产品需求测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/fetchtype")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchIBZCaseActionTypeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id,@RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求测试用例查询动态(根据类型过滤)", tags = {"测试用例日志" } ,notes = "根据产品需求测试用例查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/cases/{case_id}/ibzcaseactions/searchtype")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchIBZCaseActionTypeByProductStoryCase(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("case_id") Long case_id, @RequestBody IBZCaseActionSearchContext context) {
        context.setN_objectid_eq(case_id);
        Page<IBZCaseAction> domains = ibzcaseactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

