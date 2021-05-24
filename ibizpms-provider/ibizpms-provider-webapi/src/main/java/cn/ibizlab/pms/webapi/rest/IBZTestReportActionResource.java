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

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建报告日志", tags = {"报告日志" },  notes = "批量新建报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.createBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新报告日志", tags = {"报告日志" },  notes = "批量更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.updateBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'DELETE')")
    @ApiOperation(value = "删除报告日志", tags = {"报告日志" },  notes = "删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除报告日志", tags = {"报告日志" },  notes = "批量删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztestreportactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @ApiOperation(value = "获取报告日志草稿", tags = {"报告日志" },  notes = "获取报告日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraft(IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查报告日志", tags = {"报告日志" },  notes = "检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
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

    @ApiOperation(value = "批量保存报告日志", tags = {"报告日志" },  notes = "批量保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.saveBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"报告日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchdefault(@RequestBody IBZTestReportActionSearchContext context) {
        ibztestreportactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"报告日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/searchdefault")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchDefault(@RequestBody IBZTestReportActionSearchContext context) {
        ibztestreportactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/{action}")
    public ResponseEntity<IBZTestReportActionDTO> dynamicCall(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id , @PathVariable("action") String action , @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionService.dynamicCall(ibztestreportaction_id, action, ibztestreportactionMapping.toDomain(ibztestreportactiondto));
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }
}

