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
import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProMonthlyActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProMonthlyActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProMonthlyActionRuntime;

@Slf4j
@Api(tags = {"月报日志" })
@RestController("WebApi-ibzpromonthlyaction")
@RequestMapping("")
public class IbzProMonthlyActionResource {

    @Autowired
    public IIbzProMonthlyActionService ibzpromonthlyactionService;

    @Autowired
    public IbzProMonthlyActionRuntime ibzpromonthlyactionRuntime;

    @Autowired
    @Lazy
    public IbzProMonthlyActionMapping ibzpromonthlyactionMapping;

    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建月报日志", tags = {"月报日志" },  notes = "新建月报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions")
    @Transactional
    public ResponseEntity<IbzProMonthlyActionDTO> create(@Validated @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
		ibzpromonthlyactionService.create(domain);
        if(!ibzpromonthlyactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProMonthlyActionDTO dto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.test(#ibzpromonthlyaction_id, 'UPDATE')")
    @ApiOperation(value = "更新月报日志", tags = {"月报日志" },  notes = "更新月报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}")
    @Transactional
    public ResponseEntity<IbzProMonthlyActionDTO> update(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
		IbzProMonthlyAction domain  = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
		ibzpromonthlyactionService.update(domain );
        if(!ibzpromonthlyactionRuntime.test(ibzpromonthlyaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProMonthlyActionDTO dto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(ibzpromonthlyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.test(#ibzpromonthlyaction_id, 'DELETE')")
    @ApiOperation(value = "删除月报日志", tags = {"月报日志" },  notes = "删除月报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactionService.remove(ibzpromonthlyaction_id));
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除月报日志", tags = {"月报日志" },  notes = "批量删除月报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzpromonthlyactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzpromonthlyactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.test(#ibzpromonthlyaction_id, 'READ')")
    @ApiOperation(value = "获取月报日志", tags = {"月报日志" },  notes = "获取月报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}")
    public ResponseEntity<IbzProMonthlyActionDTO> get(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id) {
        IbzProMonthlyAction domain = ibzpromonthlyactionService.get(ibzpromonthlyaction_id);
        IbzProMonthlyActionDTO dto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(ibzpromonthlyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取月报日志草稿", tags = {"月报日志" },  notes = "获取月报日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzpromonthlyactions/getdraft")
    public ResponseEntity<IbzProMonthlyActionDTO> getDraft(IbzProMonthlyActionDTO dto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactionMapping.toDto(ibzpromonthlyactionService.getDraft(domain)));
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查月报日志", tags = {"月报日志" },  notes = "检查月报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactionService.checkKey(ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto)));
    }

    @PreAuthorize("@IbzProMonthlyActionRuntime.test(#ibzpromonthlyaction_id, 'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"月报日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/createhis")
    public ResponseEntity<IbzProMonthlyActionDTO> createHis(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
        domain = ibzpromonthlyactionService.createHis(domain);
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        ibzpromonthlyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "Pms企业专用", tags = {"月报日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/managepmsee")
    public ResponseEntity<IbzProMonthlyActionDTO> managePmsEe(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
        domain = ibzpromonthlyactionService.managePmsEe(domain);
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        ibzpromonthlyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存月报日志", tags = {"月报日志" },  notes = "保存月报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/save")
    public ResponseEntity<IbzProMonthlyActionDTO> save(@RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        ibzpromonthlyactionService.save(domain);
        IbzProMonthlyActionDTO dto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "已读", tags = {"月报日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/sendmarkdone")
    public ResponseEntity<IbzProMonthlyActionDTO> sendMarkDone(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
        domain = ibzpromonthlyactionService.sendMarkDone(domain);
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        ibzpromonthlyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待办", tags = {"月报日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/sendtodo")
    public ResponseEntity<IbzProMonthlyActionDTO> sendTodo(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
        domain = ibzpromonthlyactionService.sendTodo(domain);
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        ibzpromonthlyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送待阅", tags = {"月报日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/sendtoread")
    public ResponseEntity<IbzProMonthlyActionDTO> sendToread(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id, @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto);
        domain.setId(ibzpromonthlyaction_id);
        domain = ibzpromonthlyactionService.sendToread(domain);
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyactionRuntime.getOPPrivs(domain.getId());
        ibzpromonthlyactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }


    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"月报日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzpromonthlyactions/fetchdefault")
	public ResponseEntity<List<IbzProMonthlyActionDTO>> fetchdefault(@RequestBody IbzProMonthlyActionSearchContext context) {
        Page<IbzProMonthlyAction> domains = ibzpromonthlyactionService.searchDefault(context) ;
        List<IbzProMonthlyActionDTO> list = ibzpromonthlyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzProMonthlyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"月报日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzpromonthlyactions/fetchtype")
	public ResponseEntity<List<IbzProMonthlyActionDTO>> fetchtype(@RequestBody IbzProMonthlyActionSearchContext context) {
        Page<IbzProMonthlyAction> domains = ibzpromonthlyactionService.searchType(context) ;
        List<IbzProMonthlyActionDTO> list = ibzpromonthlyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyactions/{ibzpromonthlyaction_id}/{action}")
    public ResponseEntity<IbzProMonthlyActionDTO> dynamicCall(@PathVariable("ibzpromonthlyaction_id") Long ibzpromonthlyaction_id , @PathVariable("action") String action , @RequestBody IbzProMonthlyActionDTO ibzpromonthlyactiondto) {
        IbzProMonthlyAction domain = ibzpromonthlyactionService.dynamicCall(ibzpromonthlyaction_id, action, ibzpromonthlyactionMapping.toDomain(ibzpromonthlyactiondto));
        ibzpromonthlyactiondto = ibzpromonthlyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyactiondto);
    }
}

