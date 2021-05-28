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

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id, 'UPDATE')")
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


    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id, 'DELETE')")
    @ApiOperation(value = "删除Bug日志", tags = {"Bug日志" },  notes = "删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.remove(ibzprobugaction_id));
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除Bug日志", tags = {"Bug日志" },  notes = "批量删除Bug日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobugactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id, 'READ')")
    @ApiOperation(value = "获取Bug日志", tags = {"Bug日志" },  notes = "获取Bug日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/{ibzprobugaction_id}")
    public ResponseEntity<IbzProBugActionDTO> get(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id) {
        IbzProBugAction domain = ibzprobugactionService.get(ibzprobugaction_id);
        IbzProBugActionDTO dto = ibzprobugactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobugactionRuntime.getOPPrivs(ibzprobugaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取Bug日志草稿", tags = {"Bug日志" },  notes = "获取Bug日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/getdraft")
    public ResponseEntity<IbzProBugActionDTO> getDraft(IbzProBugActionDTO dto) {
        IbzProBugAction domain = ibzprobugactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionMapping.toDto(ibzprobugactionService.getDraft(domain)));
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查Bug日志", tags = {"Bug日志" },  notes = "检查Bug日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobugactionService.checkKey(ibzprobugactionMapping.toDomain(ibzprobugactiondto)));
    }

    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id, 'CREATE')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProBugActionRuntime.quickTest('DENY')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/{action}")
    public ResponseEntity<IbzProBugActionDTO> dynamicCall(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id , @PathVariable("action") String action , @RequestBody IbzProBugActionDTO ibzprobugactiondto) {
        IbzProBugAction domain = ibzprobugactionService.dynamicCall(ibzprobugaction_id, action, ibzprobugactionMapping.toDomain(ibzprobugactiondto));
        ibzprobugactiondto = ibzprobugactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobugactiondto);
    }
}

