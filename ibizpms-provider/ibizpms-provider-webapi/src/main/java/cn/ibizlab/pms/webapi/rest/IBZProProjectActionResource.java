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


    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'DELETE')")
    @ApiOperation(value = "删除项目日志", tags = {"项目日志" },  notes = "删除项目日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectactions/{ibzproprojectaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionService.remove(ibzproprojectaction_id));
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

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'CREATE')")
    @ApiOperation(value = "获取项目日志草稿", tags = {"项目日志" },  notes = "获取项目日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectactions/getdraft")
    public ResponseEntity<IBZProProjectActionDTO> getDraft(IBZProProjectActionDTO dto) {
        IBZProProjectAction domain = ibzproprojectactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectactionMapping.toDto(ibzproprojectactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'CREATE')")
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
}

