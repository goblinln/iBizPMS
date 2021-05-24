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
import cn.ibizlab.pms.core.ibiz.domain.IBZStoryAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZStoryActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZStoryActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZStoryActionRuntime;

@Slf4j
@Api(tags = {"需求日志" })
@RestController("WebApi-ibzstoryaction")
@RequestMapping("")
public class IBZStoryActionResource {

    @Autowired
    public IIBZStoryActionService ibzstoryactionService;

    @Autowired
    public IBZStoryActionRuntime ibzstoryactionRuntime;

    @Autowired
    @Lazy
    public IBZStoryActionMapping ibzstoryactionMapping;

    @PreAuthorize("@IBZStoryActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求日志", tags = {"需求日志" },  notes = "新建需求日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions")
    @Transactional
    public ResponseEntity<IBZStoryActionDTO> create(@Validated @RequestBody IBZStoryActionDTO ibzstoryactiondto) {
        IBZStoryAction domain = ibzstoryactionMapping.toDomain(ibzstoryactiondto);
		ibzstoryactionService.create(domain);
        if(!ibzstoryactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZStoryActionDTO dto = ibzstoryactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZStoryActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建需求日志", tags = {"需求日志" },  notes = "批量新建需求日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZStoryActionDTO> ibzstoryactiondtos) {
        ibzstoryactionService.createBatch(ibzstoryactionMapping.toDomain(ibzstoryactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'UPDATE')")
    @ApiOperation(value = "更新需求日志", tags = {"需求日志" },  notes = "更新需求日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzstoryactions/{ibzstoryaction_id}")
    @Transactional
    public ResponseEntity<IBZStoryActionDTO> update(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZStoryActionDTO ibzstoryactiondto) {
		IBZStoryAction domain  = ibzstoryactionMapping.toDomain(ibzstoryactiondto);
        domain.setId(ibzstoryaction_id);
		ibzstoryactionService.update(domain );
        if(!ibzstoryactionRuntime.test(ibzstoryaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZStoryActionDTO dto = ibzstoryactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZStoryActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新需求日志", tags = {"需求日志" },  notes = "批量更新需求日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzstoryactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZStoryActionDTO> ibzstoryactiondtos) {
        ibzstoryactionService.updateBatch(ibzstoryactionMapping.toDomain(ibzstoryactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'DELETE')")
    @ApiOperation(value = "删除需求日志", tags = {"需求日志" },  notes = "删除需求日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzstoryactions/{ibzstoryaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzstoryactionService.remove(ibzstoryaction_id));
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除需求日志", tags = {"需求日志" },  notes = "批量删除需求日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzstoryactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzstoryactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'READ')")
    @ApiOperation(value = "获取需求日志", tags = {"需求日志" },  notes = "获取需求日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzstoryactions/{ibzstoryaction_id}")
    public ResponseEntity<IBZStoryActionDTO> get(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id) {
        IBZStoryAction domain = ibzstoryactionService.get(ibzstoryaction_id);
        IBZStoryActionDTO dto = ibzstoryactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzstoryactionRuntime.getOPPrivs(ibzstoryaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取需求日志草稿", tags = {"需求日志" },  notes = "获取需求日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzstoryactions/getdraft")
    public ResponseEntity<IBZStoryActionDTO> getDraft(IBZStoryActionDTO dto) {
        IBZStoryAction domain = ibzstoryactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzstoryactionMapping.toDto(ibzstoryactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查需求日志", tags = {"需求日志" },  notes = "检查需求日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZStoryActionDTO ibzstoryactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzstoryactionService.checkKey(ibzstoryactionMapping.toDomain(ibzstoryactiondto)));
    }

    @ApiOperation(value = "保存需求日志", tags = {"需求日志" },  notes = "保存需求日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/save")
    public ResponseEntity<IBZStoryActionDTO> save(@RequestBody IBZStoryActionDTO ibzstoryactiondto) {
        IBZStoryAction domain = ibzstoryactionMapping.toDomain(ibzstoryactiondto);
        ibzstoryactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzstoryactionMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存需求日志", tags = {"需求日志" },  notes = "批量保存需求日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZStoryActionDTO> ibzstoryactiondtos) {
        ibzstoryactionService.saveBatch(ibzstoryactionMapping.toDomain(ibzstoryactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzstoryactions/fetchdefault")
	public ResponseEntity<List<IBZStoryActionDTO>> fetchdefault(@RequestBody IBZStoryActionSearchContext context) {
        ibzstoryactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZStoryAction> domains = ibzstoryactionService.searchDefault(context) ;
        List<IBZStoryActionDTO> list = ibzstoryactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZStoryActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"需求日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzstoryactions/searchdefault")
	public ResponseEntity<Page<IBZStoryActionDTO>> searchDefault(@RequestBody IBZStoryActionSearchContext context) {
        ibzstoryactionRuntime.addAuthorityConditions(context,"READ");
        Page<IBZStoryAction> domains = ibzstoryactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzstoryactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/{action}")
    public ResponseEntity<IBZStoryActionDTO> dynamicCall(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id , @PathVariable("action") String action , @RequestBody IBZStoryActionDTO ibzstoryactiondto) {
        IBZStoryAction domain = ibzstoryactionService.dynamicCall(ibzstoryaction_id, action, ibzstoryactionMapping.toDomain(ibzstoryactiondto));
        ibzstoryactiondto = ibzstoryactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzstoryactiondto);
    }
}

