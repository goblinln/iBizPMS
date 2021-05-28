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
import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProWeeklyActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProWeeklyActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProWeeklyActionRuntime;

@Slf4j
@Api(tags = {"周报日志" })
@RestController("WebApi-ibzproweeklyaction")
@RequestMapping("")
public class IBZProWeeklyActionResource {

    @Autowired
    public IIBZProWeeklyActionService ibzproweeklyactionService;

    @Autowired
    public IBZProWeeklyActionRuntime ibzproweeklyactionRuntime;

    @Autowired
    @Lazy
    public IBZProWeeklyActionMapping ibzproweeklyactionMapping;

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'CREATE')")
    @ApiOperation(value = "新建周报日志", tags = {"周报日志" },  notes = "新建周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions")
    @Transactional
    public ResponseEntity<IBZProWeeklyActionDTO> create(@Validated @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
		ibzproweeklyactionService.create(domain);
        if(!ibzproweeklyactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id, 'UPDATE')")
    @ApiOperation(value = "更新周报日志", tags = {"周报日志" },  notes = "更新周报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    @Transactional
    public ResponseEntity<IBZProWeeklyActionDTO> update(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
		IBZProWeeklyAction domain  = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
		ibzproweeklyactionService.update(domain );
        if(!ibzproweeklyactionRuntime.test(ibzproweeklyaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(ibzproweeklyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id, 'DELETE')")
    @ApiOperation(value = "删除周报日志", tags = {"周报日志" },  notes = "删除周报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.remove(ibzproweeklyaction_id));
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id, 'READ')")
    @ApiOperation(value = "获取周报日志", tags = {"周报日志" },  notes = "获取周报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<IBZProWeeklyActionDTO> get(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
        IBZProWeeklyAction domain = ibzproweeklyactionService.get(ibzproweeklyaction_id);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(ibzproweeklyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'CREATE')")
    @ApiOperation(value = "获取周报日志草稿", tags = {"周报日志" },  notes = "获取周报日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyactions/getdraft")
    public ResponseEntity<IBZProWeeklyActionDTO> getDraft(IBZProWeeklyActionDTO dto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionMapping.toDto(ibzproweeklyactionService.getDraft(domain)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'CREATE')")
    @ApiOperation(value = "检查周报日志", tags = {"周报日志" },  notes = "检查周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.checkKey(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"周报日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/createhis")
    public ResponseEntity<IBZProWeeklyActionDTO> createHis(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
        domain = ibzproweeklyactionService.createHis(domain);
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        ibzproweeklyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "Pms企业专用", tags = {"周报日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/managepmsee")
    public ResponseEntity<IBZProWeeklyActionDTO> managePmsEe(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
        domain = ibzproweeklyactionService.managePmsEe(domain);
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        ibzproweeklyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存周报日志", tags = {"周报日志" },  notes = "保存周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/save")
    public ResponseEntity<IBZProWeeklyActionDTO> save(@RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        ibzproweeklyactionService.save(domain);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "已读", tags = {"周报日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/sendmarkdone")
    public ResponseEntity<IBZProWeeklyActionDTO> sendMarkDone(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
        domain = ibzproweeklyactionService.sendMarkDone(domain);
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        ibzproweeklyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待办", tags = {"周报日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/sendtodo")
    public ResponseEntity<IBZProWeeklyActionDTO> sendTodo(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
        domain = ibzproweeklyactionService.sendTodo(domain);
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        ibzproweeklyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待阅", tags = {"周报日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/sendtoread")
    public ResponseEntity<IBZProWeeklyActionDTO> sendToread(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
        domain = ibzproweeklyactionService.sendToread(domain);
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        ibzproweeklyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }


    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"周报日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/fetchdefault")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchdefault(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchDefault(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"周报日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/fetchtype")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchtype(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchType(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/{action}")
    public ResponseEntity<IBZProWeeklyActionDTO> dynamicCall(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id , @PathVariable("action") String action , @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionService.dynamicCall(ibzproweeklyaction_id, action, ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto));
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }
}

