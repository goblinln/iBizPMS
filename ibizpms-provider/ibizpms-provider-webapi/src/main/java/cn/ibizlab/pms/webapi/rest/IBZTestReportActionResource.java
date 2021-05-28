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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestReportActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestReportActionRuntime;

@Slf4j
@Api(tags = {"报告日志" })
@RestController("WebApi-ibztestreportaction")
@RequestMapping("")
public class IBZTestReportActionResource {

    @Autowired
    public IIBZTestReportActionService ibztestreportactionService;

    @Autowired
    public IBZTestReportActionRuntime ibztestreportactionRuntime;

    @Autowired
    @Lazy
    public IBZTestReportActionMapping ibztestreportactionMapping;

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建报告日志", tags = {"报告日志" },  notes = "新建报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions")
    @Transactional
    public ResponseEntity<IBZTestReportActionDTO> create(@Validated @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
		ibztestreportactionService.create(domain);
        if(!ibztestreportactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'UPDATE')")
    @ApiOperation(value = "更新报告日志", tags = {"报告日志" },  notes = "更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestreportactions/{ibztestreportaction_id}")
    @Transactional
    public ResponseEntity<IBZTestReportActionDTO> update(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
		IBZTestReportAction domain  = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
		ibztestreportactionService.update(domain );
        if(!ibztestreportactionRuntime.test(ibztestreportaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(ibztestreportaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'DELETE')")
    @ApiOperation(value = "删除报告日志", tags = {"报告日志" },  notes = "删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }


    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'READ')")
    @ApiOperation(value = "获取报告日志", tags = {"报告日志" },  notes = "获取报告日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> get(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
        IBZTestReportAction domain = ibztestreportactionService.get(ibztestreportaction_id);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(ibztestreportaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取报告日志草稿", tags = {"报告日志" },  notes = "获取报告日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraft(IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查报告日志", tags = {"报告日志" },  notes = "检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"报告日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/comment")
    public ResponseEntity<IBZTestReportActionDTO> comment(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.comment(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"报告日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/createhis")
    public ResponseEntity<IBZTestReportActionDTO> createHis(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.createHis(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"报告日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/editcomment")
    public ResponseEntity<IBZTestReportActionDTO> editComment(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.editComment(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @ApiOperation(value = "Pms企业专用", tags = {"报告日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/managepmsee")
    public ResponseEntity<IBZTestReportActionDTO> managePmsEe(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.managePmsEe(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @ApiOperation(value = "保存报告日志", tags = {"报告日志" },  notes = "保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/save")
    public ResponseEntity<IBZTestReportActionDTO> save(@RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        ibztestreportactionService.save(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "已读", tags = {"报告日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/sendmarkdone")
    public ResponseEntity<IBZTestReportActionDTO> sendMarkDone(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.sendMarkDone(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @ApiOperation(value = "发送待办", tags = {"报告日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/sendtodo")
    public ResponseEntity<IBZTestReportActionDTO> sendTodo(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.sendTodo(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @ApiOperation(value = "发送待阅", tags = {"报告日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/sendtoread")
    public ResponseEntity<IBZTestReportActionDTO> sendToread(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
        domain = ibztestreportactionService.sendToread(domain);
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        ibztestreportactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }


    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"报告日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchdefault(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"报告日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/fetchtype")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchtype(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/{action}")
    public ResponseEntity<IBZTestReportActionDTO> dynamicCall(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id , @PathVariable("action") String action , @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionService.dynamicCall(ibztestreportaction_id, action, ibztestreportactionMapping.toDomain(ibztestreportactiondto));
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }
}

