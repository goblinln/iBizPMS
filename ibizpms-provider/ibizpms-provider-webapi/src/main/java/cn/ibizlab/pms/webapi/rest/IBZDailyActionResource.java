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
import cn.ibizlab.pms.core.ibiz.domain.IBZDailyAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZDailyActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZDailyActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZDailyActionRuntime;

@Slf4j
@Api(tags = {"日报日志" })
@RestController("WebApi-ibzdailyaction")
@RequestMapping("")
public class IBZDailyActionResource {

    @Autowired
    public IIBZDailyActionService ibzdailyactionService;

    @Autowired
    public IBZDailyActionRuntime ibzdailyactionRuntime;

    @Autowired
    @Lazy
    public IBZDailyActionMapping ibzdailyactionMapping;

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建日报日志", tags = {"日报日志" },  notes = "新建日报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions")
    @Transactional
    public ResponseEntity<IBZDailyActionDTO> create(@Validated @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
		ibzdailyactionService.create(domain);
        if(!ibzdailyactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZDailyActionDTO dto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'UPDATE')")
    @ApiOperation(value = "更新日报日志", tags = {"日报日志" },  notes = "更新日报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailyactions/{ibzdailyaction_id}")
    @Transactional
    public ResponseEntity<IBZDailyActionDTO> update(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
		IBZDailyAction domain  = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
		ibzdailyactionService.update(domain );
        if(!ibzdailyactionRuntime.test(ibzdailyaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZDailyActionDTO dto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(ibzdailyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'DELETE')")
    @ApiOperation(value = "删除日报日志", tags = {"日报日志" },  notes = "删除日报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailyactions/{ibzdailyaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactionService.remove(ibzdailyaction_id));
    }


    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'READ')")
    @ApiOperation(value = "获取日报日志", tags = {"日报日志" },  notes = "获取日报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailyactions/{ibzdailyaction_id}")
    public ResponseEntity<IBZDailyActionDTO> get(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id) {
        IBZDailyAction domain = ibzdailyactionService.get(ibzdailyaction_id);
        IBZDailyActionDTO dto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(ibzdailyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取日报日志草稿", tags = {"日报日志" },  notes = "获取日报日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailyactions/getdraft")
    public ResponseEntity<IBZDailyActionDTO> getDraft(IBZDailyActionDTO dto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactionMapping.toDto(ibzdailyactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查日报日志", tags = {"日报日志" },  notes = "检查日报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyactionService.checkKey(ibzdailyactionMapping.toDomain(ibzdailyactiondto)));
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"日报日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/comment")
    public ResponseEntity<IBZDailyActionDTO> comment(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.comment(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"日报日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/createhis")
    public ResponseEntity<IBZDailyActionDTO> createHis(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.createHis(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"日报日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/editcomment")
    public ResponseEntity<IBZDailyActionDTO> editComment(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.editComment(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @ApiOperation(value = "Pms企业专用", tags = {"日报日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/managepmsee")
    public ResponseEntity<IBZDailyActionDTO> managePmsEe(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.managePmsEe(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @ApiOperation(value = "保存日报日志", tags = {"日报日志" },  notes = "保存日报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/save")
    public ResponseEntity<IBZDailyActionDTO> save(@RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        ibzdailyactionService.save(domain);
        IBZDailyActionDTO dto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "已读", tags = {"日报日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/sendmarkdone")
    public ResponseEntity<IBZDailyActionDTO> sendMarkDone(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.sendMarkDone(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @ApiOperation(value = "发送待办", tags = {"日报日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/sendtodo")
    public ResponseEntity<IBZDailyActionDTO> sendTodo(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.sendTodo(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @ApiOperation(value = "发送待阅", tags = {"日报日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/sendtoread")
    public ResponseEntity<IBZDailyActionDTO> sendToread(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionMapping.toDomain(ibzdailyactiondto);
        domain.setId(ibzdailyaction_id);
        domain = ibzdailyactionService.sendToread(domain);
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyactionRuntime.getOPPrivs(domain.getId());
        ibzdailyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }


    @PreAuthorize("@IBZDailyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"日报日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/fetchdefault")
	public ResponseEntity<List<IBZDailyActionDTO>> fetchdefault(@RequestBody IBZDailyActionSearchContext context) {
        Page<IBZDailyAction> domains = ibzdailyactionService.searchDefault(context) ;
        List<IBZDailyActionDTO> list = ibzdailyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"日报日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/searchdefault")
	public ResponseEntity<Page<IBZDailyActionDTO>> searchDefault(@RequestBody IBZDailyActionSearchContext context) {
        Page<IBZDailyAction> domains = ibzdailyactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"日报日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/fetchtype")
	public ResponseEntity<List<IBZDailyActionDTO>> fetchtype(@RequestBody IBZDailyActionSearchContext context) {
        Page<IBZDailyAction> domains = ibzdailyactionService.searchType(context) ;
        List<IBZDailyActionDTO> list = ibzdailyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZDailyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"日报日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/searchtype")
	public ResponseEntity<Page<IBZDailyActionDTO>> searchType(@RequestBody IBZDailyActionSearchContext context) {
        Page<IBZDailyAction> domains = ibzdailyactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/{action}")
    public ResponseEntity<IBZDailyActionDTO> dynamicCall(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id , @PathVariable("action") String action , @RequestBody IBZDailyActionDTO ibzdailyactiondto) {
        IBZDailyAction domain = ibzdailyactionService.dynamicCall(ibzdailyaction_id, action, ibzdailyactionMapping.toDomain(ibzdailyactiondto));
        ibzdailyactiondto = ibzdailyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyactiondto);
    }
}

