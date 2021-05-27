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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuiteAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestSuiteActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuiteActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestSuiteActionRuntime;

@Slf4j
@Api(tags = {"套件日志" })
@RestController("WebApi-ibztestsuiteaction")
@RequestMapping("")
public class IBZTestSuiteActionResource {

    @Autowired
    public IIBZTestSuiteActionService ibztestsuiteactionService;

    @Autowired
    public IBZTestSuiteActionRuntime ibztestsuiteactionRuntime;

    @Autowired
    @Lazy
    public IBZTestSuiteActionMapping ibztestsuiteactionMapping;

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建套件日志", tags = {"套件日志" },  notes = "新建套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions")
    @Transactional
    public ResponseEntity<IBZTestSuiteActionDTO> create(@Validated @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
		ibztestsuiteactionService.create(domain);
        if(!ibztestsuiteactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'UPDATE')")
    @ApiOperation(value = "更新套件日志", tags = {"套件日志" },  notes = "更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    @Transactional
    public ResponseEntity<IBZTestSuiteActionDTO> update(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
		IBZTestSuiteAction domain  = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
		ibztestsuiteactionService.update(domain );
        if(!ibztestsuiteactionRuntime.test(ibztestsuiteaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(ibztestsuiteaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'DELETE')")
    @ApiOperation(value = "删除套件日志", tags = {"套件日志" },  notes = "删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.remove(ibztestsuiteaction_id));
    }


    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'READ')")
    @ApiOperation(value = "获取套件日志", tags = {"套件日志" },  notes = "获取套件日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> get(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.get(ibztestsuiteaction_id);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(ibztestsuiteaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取套件日志草稿", tags = {"套件日志" },  notes = "获取套件日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/getdraft")
    public ResponseEntity<IBZTestSuiteActionDTO> getDraft(IBZTestSuiteActionDTO dto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(ibztestsuiteactionService.getDraft(domain)));
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查套件日志", tags = {"套件日志" },  notes = "检查套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.checkKey(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto)));
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"套件日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/comment")
    public ResponseEntity<IBZTestSuiteActionDTO> comment(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.comment(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"套件日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/createhis")
    public ResponseEntity<IBZTestSuiteActionDTO> createHis(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.createHis(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"套件日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/editcomment")
    public ResponseEntity<IBZTestSuiteActionDTO> editComment(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.editComment(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @ApiOperation(value = "Pms企业专用", tags = {"套件日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/managepmsee")
    public ResponseEntity<IBZTestSuiteActionDTO> managePmsEe(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.managePmsEe(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @ApiOperation(value = "保存套件日志", tags = {"套件日志" },  notes = "保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/save")
    public ResponseEntity<IBZTestSuiteActionDTO> save(@RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        ibztestsuiteactionService.save(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "已读", tags = {"套件日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/sendmarkdone")
    public ResponseEntity<IBZTestSuiteActionDTO> sendMarkDone(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.sendMarkDone(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @ApiOperation(value = "发送待办", tags = {"套件日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/sendtodo")
    public ResponseEntity<IBZTestSuiteActionDTO> sendTodo(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.sendTodo(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @ApiOperation(value = "发送待阅", tags = {"套件日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/sendtoread")
    public ResponseEntity<IBZTestSuiteActionDTO> sendToread(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
        domain = ibztestsuiteactionService.sendToread(domain);
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        ibztestsuiteactiondto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }


    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"套件日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/fetchdefault")
	public ResponseEntity<List<IBZTestSuiteActionDTO>> fetchdefault(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
        List<IBZTestSuiteActionDTO> list = ibztestsuiteactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"套件日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/searchdefault")
	public ResponseEntity<Page<IBZTestSuiteActionDTO>> searchDefault(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuiteactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"套件日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/fetchtype")
	public ResponseEntity<List<IBZTestSuiteActionDTO>> fetchtype(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchType(context) ;
        List<IBZTestSuiteActionDTO> list = ibztestsuiteactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"套件日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/searchtype")
	public ResponseEntity<Page<IBZTestSuiteActionDTO>> searchType(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuiteactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/{action}")
    public ResponseEntity<IBZTestSuiteActionDTO> dynamicCall(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id , @PathVariable("action") String action , @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.dynamicCall(ibztestsuiteaction_id, action, ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto));
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }
}

