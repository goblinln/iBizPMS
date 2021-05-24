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
import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProReportlyActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProReportlyActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProReportlyActionRuntime;

@Slf4j
@Api(tags = {"汇报日志" })
@RestController("WebApi-ibzproreportlyaction")
@RequestMapping("")
public class IbzProReportlyActionResource {

    @Autowired
    public IIbzProReportlyActionService ibzproreportlyactionService;

    @Autowired
    public IbzProReportlyActionRuntime ibzproreportlyactionRuntime;

    @Autowired
    @Lazy
    public IbzProReportlyActionMapping ibzproreportlyactionMapping;

    @PreAuthorize("@IbzProReportlyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建汇报日志", tags = {"汇报日志" },  notes = "新建汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions")
    @Transactional
    public ResponseEntity<IbzProReportlyActionDTO> create(@Validated @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
		ibzproreportlyactionService.create(domain);
        if(!ibzproreportlyactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建汇报日志", tags = {"汇报日志" },  notes = "批量新建汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        ibzproreportlyactionService.createBatch(ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'UPDATE')")
    @ApiOperation(value = "更新汇报日志", tags = {"汇报日志" },  notes = "更新汇报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}")
    @Transactional
    public ResponseEntity<IbzProReportlyActionDTO> update(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
		IbzProReportlyAction domain  = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
        domain.setId(ibzproreportlyaction_id);
		ibzproreportlyactionService.update(domain );
        if(!ibzproreportlyactionRuntime.test(ibzproreportlyaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyactionRuntime.getOPPrivs(ibzproreportlyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新汇报日志", tags = {"汇报日志" },  notes = "批量更新汇报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        ibzproreportlyactionService.updateBatch(ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'DELETE')")
    @ApiOperation(value = "删除汇报日志", tags = {"汇报日志" },  notes = "删除汇报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionService.remove(ibzproreportlyaction_id));
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除汇报日志", tags = {"汇报日志" },  notes = "批量删除汇报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproreportlyactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'READ')")
    @ApiOperation(value = "获取汇报日志", tags = {"汇报日志" },  notes = "获取汇报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}")
    public ResponseEntity<IbzProReportlyActionDTO> get(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id) {
        IbzProReportlyAction domain = ibzproreportlyactionService.get(ibzproreportlyaction_id);
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyactionRuntime.getOPPrivs(ibzproreportlyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取汇报日志草稿", tags = {"汇报日志" },  notes = "获取汇报日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyactions/getdraft")
    public ResponseEntity<IbzProReportlyActionDTO> getDraft(IbzProReportlyActionDTO dto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionMapping.toDto(ibzproreportlyactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查汇报日志", tags = {"汇报日志" },  notes = "检查汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionService.checkKey(ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto)));
    }

    @ApiOperation(value = "保存汇报日志", tags = {"汇报日志" },  notes = "保存汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/save")
    public ResponseEntity<IbzProReportlyActionDTO> save(@RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
        ibzproreportlyactionService.save(domain);
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存汇报日志", tags = {"汇报日志" },  notes = "批量保存汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        ibzproreportlyactionService.saveBatch(ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"汇报日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyactions/fetchdefault")
	public ResponseEntity<List<IbzProReportlyActionDTO>> fetchdefault(@RequestBody IbzProReportlyActionSearchContext context) {
        Page<IbzProReportlyAction> domains = ibzproreportlyactionService.searchDefault(context) ;
        List<IbzProReportlyActionDTO> list = ibzproreportlyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProReportlyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"汇报日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyactions/searchdefault")
	public ResponseEntity<Page<IbzProReportlyActionDTO>> searchDefault(@RequestBody IbzProReportlyActionSearchContext context) {
        Page<IbzProReportlyAction> domains = ibzproreportlyactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreportlyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/{action}")
    public ResponseEntity<IbzProReportlyActionDTO> dynamicCall(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id , @PathVariable("action") String action , @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionService.dynamicCall(ibzproreportlyaction_id, action, ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto));
        ibzproreportlyactiondto = ibzproreportlyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactiondto);
    }
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报建立汇报日志", tags = {"汇报日志" },  notes = "根据汇报建立汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions")
    public ResponseEntity<IbzProReportlyActionDTO> createByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
        domain.setObjectid(ibzreportly_id);
		ibzproreportlyactionService.create(domain);
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报批量建立汇报日志", tags = {"汇报日志" },  notes = "根据汇报批量建立汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> createBatchByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        List<IbzProReportlyAction> domainlist=ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos);
        for(IbzProReportlyAction domain:domainlist){
            domain.setObjectid(ibzreportly_id);
        }
        ibzproreportlyactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报更新汇报日志", tags = {"汇报日志" },  notes = "根据汇报更新汇报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}")
    public ResponseEntity<IbzProReportlyActionDTO> updateByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
        domain.setObjectid(ibzreportly_id);
        domain.setId(ibzproreportlyaction_id);
		ibzproreportlyactionService.update(domain);
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报批量更新汇报日志", tags = {"汇报日志" },  notes = "根据汇报批量更新汇报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> updateBatchByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        List<IbzProReportlyAction> domainlist=ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos);
        for(IbzProReportlyAction domain:domainlist){
            domain.setObjectid(ibzreportly_id);
        }
        ibzproreportlyactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报删除汇报日志", tags = {"汇报日志" },  notes = "根据汇报删除汇报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}")
    public ResponseEntity<Boolean> removeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionService.remove(ibzproreportlyaction_id));
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报批量删除汇报日志", tags = {"汇报日志" },  notes = "根据汇报批量删除汇报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/batch")
    public ResponseEntity<Boolean> removeBatchByIbzReportly(@RequestBody List<Long> ids) {
        ibzproreportlyactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报获取汇报日志", tags = {"汇报日志" },  notes = "根据汇报获取汇报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}")
    public ResponseEntity<IbzProReportlyActionDTO> getByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id) {
        IbzProReportlyAction domain = ibzproreportlyactionService.get(ibzproreportlyaction_id);
        IbzProReportlyActionDTO dto = ibzproreportlyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据汇报获取汇报日志草稿", tags = {"汇报日志" },  notes = "根据汇报获取汇报日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/getdraft")
    public ResponseEntity<IbzProReportlyActionDTO> getDraftByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, IbzProReportlyActionDTO dto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(dto);
        domain.setObjectid(ibzreportly_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionMapping.toDto(ibzproreportlyactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据汇报检查汇报日志", tags = {"汇报日志" },  notes = "根据汇报检查汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionService.checkKey(ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto)));
    }

    @ApiOperation(value = "根据汇报保存汇报日志", tags = {"汇报日志" },  notes = "根据汇报保存汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/save")
    public ResponseEntity<IbzProReportlyActionDTO> saveByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzProReportlyActionDTO ibzproreportlyactiondto) {
        IbzProReportlyAction domain = ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondto);
        domain.setObjectid(ibzreportly_id);
        ibzproreportlyactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据汇报批量保存汇报日志", tags = {"汇报日志" },  notes = "根据汇报批量保存汇报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody List<IbzProReportlyActionDTO> ibzproreportlyactiondtos) {
        List<IbzProReportlyAction> domainlist=ibzproreportlyactionMapping.toDomain(ibzproreportlyactiondtos);
        for(IbzProReportlyAction domain:domainlist){
             domain.setObjectid(ibzreportly_id);
        }
        ibzproreportlyactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报获取数据集", tags = {"汇报日志" } ,notes = "根据汇报获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/fetchdefault")
	public ResponseEntity<List<IbzProReportlyActionDTO>> fetchIbzProReportlyActionDefaultByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody IbzProReportlyActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);
        Page<IbzProReportlyAction> domains = ibzproreportlyactionService.searchDefault(context) ;
        List<IbzProReportlyActionDTO> list = ibzproreportlyactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报查询数据集", tags = {"汇报日志" } ,notes = "根据汇报查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/searchdefault")
	public ResponseEntity<Page<IbzProReportlyActionDTO>> searchIbzProReportlyActionDefaultByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzProReportlyActionSearchContext context) {
        context.setN_objectid_eq(ibzreportly_id);
        Page<IbzProReportlyAction> domains = ibzproreportlyactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreportlyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

