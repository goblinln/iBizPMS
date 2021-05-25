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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProjectActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProProjectActionRuntime;

@Slf4j
@Api(tags = {"项目日志" })
@RestController("WebApi-ibzproprojectaction")
@RequestMapping("")
public class IBZProProjectActionResource {

    @Autowired
    public IIBZProProjectActionService ibzproprojectactionService;

    @Autowired
    public IBZProProjectActionRuntime ibzproprojectactionRuntime;

    @Autowired
    @Lazy
    public IBZProProjectActionMapping ibzproprojectactionMapping;

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目日志", tags = {"项目日志" },  notes = "新建项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions")
    @Transactional
    public ResponseEntity<IBZProProjectActionDTO> create(@Validated @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
		ibzproprojectactionService.create(domain);
        if(!ibzproprojectactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建项目日志", tags = {"项目日志" },  notes = "批量新建项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        ibzproprojectactionService.createBatch(ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'UPDATE')")
    @ApiOperation(value = "更新项目日志", tags = {"项目日志" },  notes = "更新项目日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectactions/{ibzproprojectaction_id}")
    @Transactional
    public ResponseEntity<IBZProProjectActionDTO> update(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
		IBZProProjectAction domain  = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
		ibzproprojectactionService.update(domain );
        if(!ibzproprojectactionRuntime.test(ibzproprojectaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(ibzproprojectaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新项目日志", tags = {"项目日志" },  notes = "批量更新项目日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        ibzproprojectactionService.updateBatch(ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'DELETE')")
    @ApiOperation(value = "删除项目日志", tags = {"项目日志" },  notes = "删除项目日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionService.remove(ibzproprojectaction_id));
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除项目日志", tags = {"项目日志" },  notes = "批量删除项目日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproprojectactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'READ')")
    @ApiOperation(value = "获取项目日志", tags = {"项目日志" },  notes = "获取项目日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<IBZProProjectActionDTO> get(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id) {
        IBZProProjectAction domain = ibzproprojectactionService.get(ibzproprojectaction_id);
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(ibzproprojectaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目日志草稿", tags = {"项目日志" },  notes = "获取项目日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectactions/getdraft")
    public ResponseEntity<IBZProProjectActionDTO> getDraft(IBZProProjectActionDTO dto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionMapping.toDto(ibzproprojectactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目日志", tags = {"项目日志" },  notes = "检查项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionService.checkKey(ibzproprojectactionMapping.toDomain(ibzproprojectactiondto)));
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"项目日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/comment")
    public ResponseEntity<IBZProProjectActionDTO> comment(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.comment(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"项目日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/createhis")
    public ResponseEntity<IBZProProjectActionDTO> createHis(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.createHis(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"项目日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"项目日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/editcomment")
    public ResponseEntity<IBZProProjectActionDTO> editComment(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.editComment(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"项目日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"项目日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/managepmsee")
    public ResponseEntity<IBZProProjectActionDTO> managePmsEe(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.managePmsEe(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"项目日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存项目日志", tags = {"项目日志" },  notes = "保存项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/save")
    public ResponseEntity<IBZProProjectActionDTO> save(@RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        ibzproprojectactionService.save(domain);
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存项目日志", tags = {"项目日志" },  notes = "批量保存项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        ibzproprojectactionService.saveBatch(ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"项目日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/sendmarkdone")
    public ResponseEntity<IBZProProjectActionDTO> sendMarkDone(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendMarkDone(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"项目日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"项目日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/sendtodo")
    public ResponseEntity<IBZProProjectActionDTO> sendTodo(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendTodo(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"项目日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"项目日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/sendtoread")
    public ResponseEntity<IBZProProjectActionDTO> sendToread(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendToread(domain);
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectactionRuntime.getOPPrivs(domain.getId());
        ibzproprojectactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"项目日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"项目日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/fetchdefault")
	public ResponseEntity<List<IBZProProjectActionDTO>> fetchdefault(@RequestBody IBZProProjectActionSearchContext context) {
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchDefault(context) ;
        List<IBZProProjectActionDTO> list = ibzproprojectactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"项目日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/searchdefault")
	public ResponseEntity<Page<IBZProProjectActionDTO>> searchDefault(@RequestBody IBZProProjectActionSearchContext context) {
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"项目日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/fetchtype")
	public ResponseEntity<List<IBZProProjectActionDTO>> fetchtype(@RequestBody IBZProProjectActionSearchContext context) {
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchType(context) ;
        List<IBZProProjectActionDTO> list = ibzproprojectactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProjectActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"项目日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/searchtype")
	public ResponseEntity<Page<IBZProProjectActionDTO>> searchType(@RequestBody IBZProProjectActionSearchContext context) {
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/{action}")
    public ResponseEntity<IBZProProjectActionDTO> dynamicCall(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id , @PathVariable("action") String action , @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionService.dynamicCall(ibzproprojectaction_id, action, ibzproprojectactionMapping.toDomain(ibzproprojectactiondto));
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立项目日志", tags = {"项目日志" },  notes = "根据项目建立项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions")
    public ResponseEntity<IBZProProjectActionDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
		ibzproprojectactionService.create(domain);
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目批量建立项目日志", tags = {"项目日志" },  notes = "根据项目批量建立项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domainlist=ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        for(IBZProProjectAction domain:domainlist){
            domain.setObjectid(project_id);
        }
        ibzproprojectactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新项目日志", tags = {"项目日志" },  notes = "根据项目更新项目日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<IBZProProjectActionDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
		ibzproprojectactionService.update(domain);
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目批量更新项目日志", tags = {"项目日志" },  notes = "根据项目批量更新项目日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domainlist=ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        for(IBZProProjectAction domain:domainlist){
            domain.setObjectid(project_id);
        }
        ibzproprojectactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除项目日志", tags = {"项目日志" },  notes = "根据项目删除项目日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionService.remove(ibzproprojectaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目批量删除项目日志", tags = {"项目日志" },  notes = "根据项目批量删除项目日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/ibzproprojectactions/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        ibzproprojectactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取项目日志", tags = {"项目日志" },  notes = "根据项目获取项目日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<IBZProProjectActionDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id) {
        IBZProProjectAction domain = ibzproprojectactionService.get(ibzproprojectaction_id);
        IBZProProjectActionDTO dto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取项目日志草稿", tags = {"项目日志" },  notes = "根据项目获取项目日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/ibzproprojectactions/getdraft")
    public ResponseEntity<IBZProProjectActionDTO> getDraftByProject(@PathVariable("project_id") Long project_id, IBZProProjectActionDTO dto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(dto);
        domain.setObjectid(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionMapping.toDto(ibzproprojectactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目检查项目日志", tags = {"项目日志" },  notes = "根据项目检查项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionService.checkKey(ibzproprojectactionMapping.toDomain(ibzproprojectactiondto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/comment")
    public ResponseEntity<IBZProProjectActionDTO> commentByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.comment(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/createhis")
    public ResponseEntity<IBZProProjectActionDTO> createHisByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.createHis(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'MANAGE')")
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/editcomment")
    public ResponseEntity<IBZProProjectActionDTO> editCommentByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.editComment(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/managepmsee")
    public ResponseEntity<IBZProProjectActionDTO> managePmsEeByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.managePmsEe(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目保存项目日志", tags = {"项目日志" },  notes = "根据项目保存项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/save")
    public ResponseEntity<IBZProProjectActionDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        ibzproprojectactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目批量保存项目日志", tags = {"项目日志" },  notes = "根据项目批量保存项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domainlist=ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        for(IBZProProjectAction domain:domainlist){
             domain.setObjectid(project_id);
        }
        ibzproprojectactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/sendmarkdone")
    public ResponseEntity<IBZProProjectActionDTO> sendMarkDoneByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendMarkDone(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/sendtodo")
    public ResponseEntity<IBZProProjectActionDTO> sendTodoByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendTodo(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目项目日志", tags = {"项目日志" },  notes = "根据项目项目日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/sendtoread")
    public ResponseEntity<IBZProProjectActionDTO> sendToreadByProject(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectActionDTO ibzproprojectactiondto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(ibzproprojectactiondto);
        domain.setObjectid(project_id);
        domain.setId(ibzproprojectaction_id);
        domain = ibzproprojectactionService.sendToread(domain) ;
        ibzproprojectactiondto = ibzproprojectactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactiondto);
    }
    @ApiOperation(value = "批量处理[根据项目项目日志]", tags = {"项目日志" },  notes = "批量处理[根据项目项目日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProject(@PathVariable("project_id") Long project_id, @RequestBody List<IBZProProjectActionDTO> ibzproprojectactiondtos) {
        List<IBZProProjectAction> domains = ibzproprojectactionMapping.toDomain(ibzproprojectactiondtos);
        boolean result = ibzproprojectactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取数据集", tags = {"项目日志" } ,notes = "根据项目获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/fetchdefault")
	public ResponseEntity<List<IBZProProjectActionDTO>> fetchIBZProProjectActionDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody IBZProProjectActionSearchContext context) {
        context.setN_objectid_eq(project_id);
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchDefault(context) ;
        List<IBZProProjectActionDTO> list = ibzproprojectactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询数据集", tags = {"项目日志" } ,notes = "根据项目查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/searchdefault")
	public ResponseEntity<Page<IBZProProjectActionDTO>> searchIBZProProjectActionDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody IBZProProjectActionSearchContext context) {
        context.setN_objectid_eq(project_id);
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取动态(根据类型过滤)", tags = {"项目日志" } ,notes = "根据项目获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/fetchtype")
	public ResponseEntity<List<IBZProProjectActionDTO>> fetchIBZProProjectActionTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody IBZProProjectActionSearchContext context) {
        context.setN_objectid_eq(project_id);
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchType(context) ;
        List<IBZProProjectActionDTO> list = ibzproprojectactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询动态(根据类型过滤)", tags = {"项目日志" } ,notes = "根据项目查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/searchtype")
	public ResponseEntity<Page<IBZProProjectActionDTO>> searchIBZProProjectActionTypeByProject(@PathVariable("project_id") Long project_id, @RequestBody IBZProProjectActionSearchContext context) {
        context.setN_objectid_eq(project_id);
        Page<IBZProProjectAction> domains = ibzproprojectactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

