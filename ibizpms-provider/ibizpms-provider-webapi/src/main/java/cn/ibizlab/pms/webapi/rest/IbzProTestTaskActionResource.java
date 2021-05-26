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
import cn.ibizlab.pms.core.ibiz.domain.IbzProTestTaskAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProTestTaskActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProTestTaskActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProTestTaskActionRuntime;

@Slf4j
@Api(tags = {"测试单日志" })
@RestController("WebApi-ibzprotesttaskaction")
@RequestMapping("")
public class IbzProTestTaskActionResource {

    @Autowired
    public IIbzProTestTaskActionService ibzprotesttaskactionService;

    @Autowired
    public IbzProTestTaskActionRuntime ibzprotesttaskactionRuntime;

    @Autowired
    @Lazy
    public IbzProTestTaskActionMapping ibzprotesttaskactionMapping;

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试单日志", tags = {"测试单日志" },  notes = "新建测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions")
    @Transactional
    public ResponseEntity<IbzProTestTaskActionDTO> create(@Validated @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
		ibzprotesttaskactionService.create(domain);
        if(!ibzprotesttaskactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试单日志", tags = {"测试单日志" },  notes = "批量新建测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.createBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'UPDATE')")
    @ApiOperation(value = "更新测试单日志", tags = {"测试单日志" },  notes = "更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    @Transactional
    public ResponseEntity<IbzProTestTaskActionDTO> update(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
		IbzProTestTaskAction domain  = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain );
        if(!ibzprotesttaskactionRuntime.test(ibzprotesttaskaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(ibzprotesttaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试单日志", tags = {"测试单日志" },  notes = "批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.updateBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'DELETE')")
    @ApiOperation(value = "删除测试单日志", tags = {"测试单日志" },  notes = "删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试单日志", tags = {"测试单日志" },  notes = "批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'READ')")
    @ApiOperation(value = "获取测试单日志", tags = {"测试单日志" },  notes = "获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> get(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(ibzprotesttaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试单日志草稿", tags = {"测试单日志" },  notes = "获取测试单日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraft(IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试单日志", tags = {"测试单日志" },  notes = "检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "添加备注", tags = {"测试单日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/comment")
    public ResponseEntity<IbzProTestTaskActionDTO> comment(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.comment(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"测试单日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/createhis")
    public ResponseEntity<IbzProTestTaskActionDTO> createHis(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.createHis(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"测试单日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "编辑备注信息", tags = {"测试单日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/editcomment")
    public ResponseEntity<IbzProTestTaskActionDTO> editComment(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.editComment(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"测试单日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"测试单日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/managepmsee")
    public ResponseEntity<IbzProTestTaskActionDTO> managePmsEe(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.managePmsEe(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"测试单日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存测试单日志", tags = {"测试单日志" },  notes = "保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> save(@RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        ibzprotesttaskactionService.save(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试单日志", tags = {"测试单日志" },  notes = "批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.saveBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"测试单日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendmarkdone")
    public ResponseEntity<IbzProTestTaskActionDTO> sendMarkDone(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendMarkDone(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"测试单日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"测试单日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtodo")
    public ResponseEntity<IbzProTestTaskActionDTO> sendTodo(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendTodo(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"测试单日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"测试单日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtoread")
    public ResponseEntity<IbzProTestTaskActionDTO> sendToread(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendToread(domain);
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        ibzprotesttaskactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"测试单日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试单日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchdefault(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试单日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchDefault(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/fetchtype")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchtype(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/searchtype")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchType(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/{action}")
    public ResponseEntity<IbzProTestTaskActionDTO> dynamicCall(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id , @PathVariable("action") String action , @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.dynamicCall(ibzprotesttaskaction_id, action, ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto));
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/comment")
    public ResponseEntity<IbzProTestTaskActionDTO> commentByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.comment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/createhis")
    public ResponseEntity<IbzProTestTaskActionDTO> createHisByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.createHis(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/editcomment")
    public ResponseEntity<IbzProTestTaskActionDTO> editCommentByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.editComment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/managepmsee")
    public ResponseEntity<IbzProTestTaskActionDTO> managePmsEeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.managePmsEe(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendmarkdone")
    public ResponseEntity<IbzProTestTaskActionDTO> sendMarkDoneByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendMarkDone(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtodo")
    public ResponseEntity<IbzProTestTaskActionDTO> sendTodoByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendTodo(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据测试版本测试单日志", tags = {"测试单日志" },  notes = "根据测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtoread")
    public ResponseEntity<IbzProTestTaskActionDTO> sendToreadByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendToread(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/fetchtype")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionTypeByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本查询动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据测试版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/searchtype")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionTypeByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据产品测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/comment")
    public ResponseEntity<IbzProTestTaskActionDTO> commentByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.comment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/createhis")
    public ResponseEntity<IbzProTestTaskActionDTO> createHisByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.createHis(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/editcomment")
    public ResponseEntity<IbzProTestTaskActionDTO> editCommentByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.editComment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/managepmsee")
    public ResponseEntity<IbzProTestTaskActionDTO> managePmsEeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.managePmsEe(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendmarkdone")
    public ResponseEntity<IbzProTestTaskActionDTO> sendMarkDoneByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendMarkDone(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtodo")
    public ResponseEntity<IbzProTestTaskActionDTO> sendTodoByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendTodo(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品测试版本测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtoread")
    public ResponseEntity<IbzProTestTaskActionDTO> sendToreadByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendToread(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据产品测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据产品测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据产品测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据产品测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchtype")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本查询动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据产品测试版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchtype")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据项目测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/comment")
    public ResponseEntity<IbzProTestTaskActionDTO> commentByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.comment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/createhis")
    public ResponseEntity<IbzProTestTaskActionDTO> createHisByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.createHis(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/editcomment")
    public ResponseEntity<IbzProTestTaskActionDTO> editCommentByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.editComment(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/managepmsee")
    public ResponseEntity<IbzProTestTaskActionDTO> managePmsEeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.managePmsEe(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendmarkdone")
    public ResponseEntity<IbzProTestTaskActionDTO> sendMarkDoneByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendMarkDone(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtodo")
    public ResponseEntity<IbzProTestTaskActionDTO> sendTodoByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendTodo(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目测试版本测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/sendtoread")
    public ResponseEntity<IbzProTestTaskActionDTO> sendToreadByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
        domain = ibzprotesttaskactionService.sendToread(domain) ;
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目测试版本测试单日志]", tags = {"测试单日志" },  notes = "批量处理[根据项目测试版本测试单日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domains = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        boolean result = ibzprotesttaskactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据项目测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据项目测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据项目测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchtype")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本查询动态(根据类型过滤)", tags = {"测试单日志" } ,notes = "根据项目测试版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchtype")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

