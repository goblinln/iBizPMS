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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTaskActionRuntime;

@Slf4j
@Api(tags = {"任务日志" })
@RestController("WebApi-ibztaskaction")
@RequestMapping("")
public class IBZTaskActionResource {

    @Autowired
    public IIBZTaskActionService ibztaskactionService;

    @Autowired
    public IBZTaskActionRuntime ibztaskactionRuntime;

    @Autowired
    @Lazy
    public IBZTaskActionMapping ibztaskactionMapping;

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务日志", tags = {"任务日志" },  notes = "新建任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> create(@Validated @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
		ibztaskactionService.create(domain);
        if(!ibztaskactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建任务日志", tags = {"任务日志" },  notes = "批量新建任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.createBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'UPDATE')")
    @ApiOperation(value = "更新任务日志", tags = {"任务日志" },  notes = "更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/{ibztaskaction_id}")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> update(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
		IBZTaskAction domain  = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain );
        if(!ibztaskactionRuntime.test(ibztaskaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新任务日志", tags = {"任务日志" },  notes = "批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.updateBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'DELETE')")
    @ApiOperation(value = "删除任务日志", tags = {"任务日志" },  notes = "删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除任务日志", tags = {"任务日志" },  notes = "批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
    @ApiOperation(value = "获取任务日志", tags = {"任务日志" },  notes = "获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> get(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(ibztaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务日志草稿", tags = {"任务日志" },  notes = "获取任务日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraft(IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查任务日志", tags = {"任务日志" },  notes = "检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "保存任务日志", tags = {"任务日志" },  notes = "保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> save(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存任务日志", tags = {"任务日志" },  notes = "批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.saveBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchdefault(@RequestBody IBZTaskActionSearchContext context) {
        ibztaskactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"任务日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchDefault(@RequestBody IBZTaskActionSearchContext context) {
        ibztaskactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/{action}")
    public ResponseEntity<IBZTaskActionDTO> dynamicCall(@PathVariable("ibztaskaction_id") Long ibztaskaction_id , @PathVariable("action") String action , @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionService.dynamicCall(ibztaskaction_id, action, ibztaskactionMapping.toDomain(ibztaskactiondto));
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }
}

