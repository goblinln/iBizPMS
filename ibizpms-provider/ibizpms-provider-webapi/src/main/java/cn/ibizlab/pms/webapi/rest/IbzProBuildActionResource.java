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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBuildActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProBuildActionRuntime;

@Slf4j
@Api(tags = {"版本日志" })
@RestController("WebApi-ibzprobuildaction")
@RequestMapping("")
public class IbzProBuildActionResource {

    @Autowired
    public IIbzProBuildActionService ibzprobuildactionService;

    @Autowired
    public IbzProBuildActionRuntime ibzprobuildactionRuntime;

    @Autowired
    @Lazy
    public IbzProBuildActionMapping ibzprobuildactionMapping;

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建版本日志", tags = {"版本日志" },  notes = "新建版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions")
    @Transactional
    public ResponseEntity<IbzProBuildActionDTO> create(@Validated @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
		ibzprobuildactionService.create(domain);
        if(!ibzprobuildactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建版本日志", tags = {"版本日志" },  notes = "批量新建版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.createBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'UPDATE')")
    @ApiOperation(value = "更新版本日志", tags = {"版本日志" },  notes = "更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    @Transactional
    public ResponseEntity<IbzProBuildActionDTO> update(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
		IbzProBuildAction domain  = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setId(ibzprobuildaction_id);
		ibzprobuildactionService.update(domain );
        if(!ibzprobuildactionRuntime.test(ibzprobuildaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(ibzprobuildaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新版本日志", tags = {"版本日志" },  notes = "批量更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.updateBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'DELETE')")
    @ApiOperation(value = "删除版本日志", tags = {"版本日志" },  notes = "删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.remove(ibzprobuildaction_id));
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除版本日志", tags = {"版本日志" },  notes = "批量删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobuildactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'READ')")
    @ApiOperation(value = "获取版本日志", tags = {"版本日志" },  notes = "获取版本日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> get(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
        IbzProBuildAction domain = ibzprobuildactionService.get(ibzprobuildaction_id);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(ibzprobuildaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取版本日志草稿", tags = {"版本日志" },  notes = "获取版本日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/getdraft")
    public ResponseEntity<IbzProBuildActionDTO> getDraft(IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(ibzprobuildactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查版本日志", tags = {"版本日志" },  notes = "检查版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.checkKey(ibzprobuildactionMapping.toDomain(ibzprobuildactiondto)));
    }

    @ApiOperation(value = "保存版本日志", tags = {"版本日志" },  notes = "保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/save")
    public ResponseEntity<IbzProBuildActionDTO> save(@RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        ibzprobuildactionService.save(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存版本日志", tags = {"版本日志" },  notes = "批量保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.saveBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"版本日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/fetchdefault")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchdefault(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"版本日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/searchdefault")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchDefault(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/{action}")
    public ResponseEntity<IbzProBuildActionDTO> dynamicCall(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id , @PathVariable("action") String action , @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionService.dynamicCall(ibzprobuildaction_id, action, ibzprobuildactionMapping.toDomain(ibzprobuildactiondto));
        ibzprobuildactiondto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactiondto);
    }
}

