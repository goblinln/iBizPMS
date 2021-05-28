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

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id, 'UPDATE')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id, 'DELETE')")
    @ApiOperation(value = "删除测试单日志", tags = {"测试单日志" },  notes = "删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除测试单日志", tags = {"测试单日志" },  notes = "批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id, 'READ')")
    @ApiOperation(value = "获取测试单日志", tags = {"测试单日志" },  notes = "获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> get(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(ibzprotesttaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取测试单日志草稿", tags = {"测试单日志" },  notes = "获取测试单日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraft(IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查测试单日志", tags = {"测试单日志" },  notes = "检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id, 'CREATE')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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


    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('DENY')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/{action}")
    public ResponseEntity<IbzProTestTaskActionDTO> dynamicCall(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id , @PathVariable("action") String action , @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.dynamicCall(ibzprotesttaskaction_id, action, ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto));
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
}

