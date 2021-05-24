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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZCaseActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZCaseActionRuntime;

@Slf4j
@Api(tags = {"测试日志" })
@RestController("WebApi-ibzcaseaction")
@RequestMapping("")
public class IBZCaseActionResource {

    @Autowired
    public IIBZCaseActionService ibzcaseactionService;

    @Autowired
    public IBZCaseActionRuntime ibzcaseactionRuntime;

    @Autowired
    @Lazy
    public IBZCaseActionMapping ibzcaseactionMapping;

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试日志", tags = {"测试日志" },  notes = "新建测试日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions")
    @Transactional
    public ResponseEntity<IBZCaseActionDTO> create(@Validated @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
		ibzcaseactionService.create(domain);
        if(!ibzcaseactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试日志", tags = {"测试日志" },  notes = "批量新建测试日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.createBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'UPDATE')")
    @ApiOperation(value = "更新测试日志", tags = {"测试日志" },  notes = "更新测试日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/{ibzcaseaction_id}")
    @Transactional
    public ResponseEntity<IBZCaseActionDTO> update(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
		IBZCaseAction domain  = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        domain.setId(ibzcaseaction_id);
		ibzcaseactionService.update(domain );
        if(!ibzcaseactionRuntime.test(ibzcaseaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(ibzcaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试日志", tags = {"测试日志" },  notes = "批量更新测试日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.updateBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'DELETE')")
    @ApiOperation(value = "删除测试日志", tags = {"测试日志" },  notes = "删除测试日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.remove(ibzcaseaction_id));
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试日志", tags = {"测试日志" },  notes = "批量删除测试日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzcaseactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'READ')")
    @ApiOperation(value = "获取测试日志", tags = {"测试日志" },  notes = "获取测试日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/{ibzcaseaction_id}")
    public ResponseEntity<IBZCaseActionDTO> get(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id) {
        IBZCaseAction domain = ibzcaseactionService.get(ibzcaseaction_id);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(ibzcaseaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试日志草稿", tags = {"测试日志" },  notes = "获取测试日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/getdraft")
    public ResponseEntity<IBZCaseActionDTO> getDraft(IBZCaseActionDTO dto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionMapping.toDto(ibzcaseactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试日志", tags = {"测试日志" },  notes = "检查测试日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcaseactionService.checkKey(ibzcaseactionMapping.toDomain(ibzcaseactiondto)));
    }

    @ApiOperation(value = "保存测试日志", tags = {"测试日志" },  notes = "保存测试日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/save")
    public ResponseEntity<IBZCaseActionDTO> save(@RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionMapping.toDomain(ibzcaseactiondto);
        ibzcaseactionService.save(domain);
        IBZCaseActionDTO dto = ibzcaseactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcaseactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试日志", tags = {"测试日志" },  notes = "批量保存测试日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZCaseActionDTO> ibzcaseactiondtos) {
        ibzcaseactionService.saveBatch(ibzcaseactionMapping.toDomain(ibzcaseactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/fetchdefault")
	public ResponseEntity<List<IBZCaseActionDTO>> fetchdefault(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
        List<IBZCaseActionDTO> list = ibzcaseactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZCaseActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/searchdefault")
	public ResponseEntity<Page<IBZCaseActionDTO>> searchDefault(@RequestBody IBZCaseActionSearchContext context) {
        Page<IBZCaseAction> domains = ibzcaseactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcaseactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/{action}")
    public ResponseEntity<IBZCaseActionDTO> dynamicCall(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id , @PathVariable("action") String action , @RequestBody IBZCaseActionDTO ibzcaseactiondto) {
        IBZCaseAction domain = ibzcaseactionService.dynamicCall(ibzcaseaction_id, action, ibzcaseactionMapping.toDomain(ibzcaseactiondto));
        ibzcaseactiondto = ibzcaseactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcaseactiondto);
    }
}

