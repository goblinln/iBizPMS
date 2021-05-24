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
import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyHistory;
import cn.ibizlab.pms.core.ibiz.service.IIbzProReportlyHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProReportlyHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProReportlyHistoryRuntime;

@Slf4j
@Api(tags = {"汇报操作历史" })
@RestController("WebApi-ibzproreportlyhistory")
@RequestMapping("")
public class IbzProReportlyHistoryResource {

    @Autowired
    public IIbzProReportlyHistoryService ibzproreportlyhistoryService;

    @Autowired
    public IbzProReportlyHistoryRuntime ibzproreportlyhistoryRuntime;

    @Autowired
    @Lazy
    public IbzProReportlyHistoryMapping ibzproreportlyhistoryMapping;

    @PreAuthorize("@IbzProReportlyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建汇报操作历史", tags = {"汇报操作历史" },  notes = "新建汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories")
    @Transactional
    public ResponseEntity<IbzProReportlyHistoryDTO> create(@Validated @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
		ibzproreportlyhistoryService.create(domain);
        if(!ibzproreportlyhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建汇报操作历史", tags = {"汇报操作历史" },  notes = "批量新建汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        ibzproreportlyhistoryService.createBatch(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.test(#ibzproreportlyhistory_id,'UPDATE')")
    @ApiOperation(value = "更新汇报操作历史", tags = {"汇报操作历史" },  notes = "更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    @Transactional
    public ResponseEntity<IbzProReportlyHistoryDTO> update(@PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
		IbzProReportlyHistory domain  = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setId(ibzproreportlyhistory_id);
		ibzproreportlyhistoryService.update(domain );
        if(!ibzproreportlyhistoryRuntime.test(ibzproreportlyhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyhistoryRuntime.getOPPrivs(ibzproreportlyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新汇报操作历史", tags = {"汇报操作历史" },  notes = "批量更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        ibzproreportlyhistoryService.updateBatch(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.test(#ibzproreportlyhistory_id,'DELETE')")
    @ApiOperation(value = "删除汇报操作历史", tags = {"汇报操作历史" },  notes = "删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.remove(ibzproreportlyhistory_id));
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除汇报操作历史", tags = {"汇报操作历史" },  notes = "批量删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproreportlyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.test(#ibzproreportlyhistory_id,'READ')")
    @ApiOperation(value = "获取汇报操作历史", tags = {"汇报操作历史" },  notes = "获取汇报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<IbzProReportlyHistoryDTO> get(@PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryService.get(ibzproreportlyhistory_id);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyhistoryRuntime.getOPPrivs(ibzproreportlyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取汇报操作历史草稿", tags = {"汇报操作历史" },  notes = "获取汇报操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyhistories/getdraft")
    public ResponseEntity<IbzProReportlyHistoryDTO> getDraft(IbzProReportlyHistoryDTO dto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryMapping.toDto(ibzproreportlyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查汇报操作历史", tags = {"汇报操作历史" },  notes = "检查汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.checkKey(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto)));
    }

    @ApiOperation(value = "保存汇报操作历史", tags = {"汇报操作历史" },  notes = "保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories/save")
    public ResponseEntity<IbzProReportlyHistoryDTO> save(@RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        ibzproreportlyhistoryService.save(domain);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreportlyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存汇报操作历史", tags = {"汇报操作历史" },  notes = "批量保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        ibzproreportlyhistoryService.saveBatch(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"汇报操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyhistories/fetchdefault")
	public ResponseEntity<List<IbzProReportlyHistoryDTO>> fetchdefault(@RequestBody IbzProReportlyHistorySearchContext context) {
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
        List<IbzProReportlyHistoryDTO> list = ibzproreportlyhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProReportlyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"汇报操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyhistories/searchdefault")
	public ResponseEntity<Page<IbzProReportlyHistoryDTO>> searchDefault(@RequestBody IbzProReportlyHistorySearchContext context) {
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreportlyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyhistories/{ibzproreportlyhistory_id}/{action}")
    public ResponseEntity<IbzProReportlyHistoryDTO> dynamicCall(@PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id , @PathVariable("action") String action , @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryService.dynamicCall(ibzproreportlyhistory_id, action, ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto));
        ibzproreportlyhistorydto = ibzproreportlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistorydto);
    }
    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'CREATE')")
    @ApiOperation(value = "根据汇报日志建立汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志建立汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories")
    public ResponseEntity<IbzProReportlyHistoryDTO> createByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
		ibzproreportlyhistoryService.create(domain);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'CREATE')")
    @ApiOperation(value = "根据汇报日志批量建立汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志批量建立汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> createBatchByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
            domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'UPDATE')")
    @ApiOperation(value = "根据汇报日志更新汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<IbzProReportlyHistoryDTO> updateByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
        domain.setId(ibzproreportlyhistory_id);
		ibzproreportlyhistoryService.update(domain);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'UPDATE')")
    @ApiOperation(value = "根据汇报日志批量更新汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志批量更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
            domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'DELETE')")
    @ApiOperation(value = "根据汇报日志删除汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<Boolean> removeByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.remove(ibzproreportlyhistory_id));
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'DELETE')")
    @ApiOperation(value = "根据汇报日志批量删除汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志批量删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzProReportlyAction(@RequestBody List<Long> ids) {
        ibzproreportlyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'READ')")
    @ApiOperation(value = "根据汇报日志获取汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志获取汇报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<IbzProReportlyHistoryDTO> getByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryService.get(ibzproreportlyhistory_id);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据汇报日志获取汇报操作历史草稿", tags = {"汇报操作历史" },  notes = "根据汇报日志获取汇报操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/getdraft")
    public ResponseEntity<IbzProReportlyHistoryDTO> getDraftByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, IbzProReportlyHistoryDTO dto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(dto);
        domain.setAction(ibzproreportlyaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryMapping.toDto(ibzproreportlyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据汇报日志检查汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志检查汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.checkKey(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto)));
    }

    @ApiOperation(value = "根据汇报日志保存汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/save")
    public ResponseEntity<IbzProReportlyHistoryDTO> saveByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
        ibzproreportlyhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据汇报日志批量保存汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报日志批量保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
             domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'READ')")
	@ApiOperation(value = "根据汇报日志获取数据集", tags = {"汇报操作历史" } ,notes = "根据汇报日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/fetchdefault")
	public ResponseEntity<List<IbzProReportlyHistoryDTO>> fetchIbzProReportlyHistoryDefaultByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id,@RequestBody IbzProReportlyHistorySearchContext context) {
        context.setN_action_eq(ibzproreportlyaction_id);
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
        List<IbzProReportlyHistoryDTO> list = ibzproreportlyhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProReportlyActionRuntime.test(#ibzproreportlyaction_id,'READ')")
	@ApiOperation(value = "根据汇报日志查询数据集", tags = {"汇报操作历史" } ,notes = "根据汇报日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/searchdefault")
	public ResponseEntity<Page<IbzProReportlyHistoryDTO>> searchIbzProReportlyHistoryDefaultByIbzProReportlyAction(@PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistorySearchContext context) {
        context.setN_action_eq(ibzproreportlyaction_id);
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreportlyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报汇报日志建立汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志建立汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories")
    public ResponseEntity<IbzProReportlyHistoryDTO> createByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
		ibzproreportlyhistoryService.create(domain);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报汇报日志批量建立汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志批量建立汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> createBatchByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
            domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报汇报日志更新汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<IbzProReportlyHistoryDTO> updateByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
        domain.setId(ibzproreportlyhistory_id);
		ibzproreportlyhistoryService.update(domain);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报汇报日志批量更新汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志批量更新汇报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
            domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报汇报日志删除汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<Boolean> removeByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.remove(ibzproreportlyhistory_id));
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报汇报日志批量删除汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志批量删除汇报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzReportlyIbzProReportlyAction(@RequestBody List<Long> ids) {
        ibzproreportlyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报汇报日志获取汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志获取汇报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/{ibzproreportlyhistory_id}")
    public ResponseEntity<IbzProReportlyHistoryDTO> getByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @PathVariable("ibzproreportlyhistory_id") Long ibzproreportlyhistory_id) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryService.get(ibzproreportlyhistory_id);
        IbzProReportlyHistoryDTO dto = ibzproreportlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据汇报汇报日志获取汇报操作历史草稿", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志获取汇报操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/getdraft")
    public ResponseEntity<IbzProReportlyHistoryDTO> getDraftByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, IbzProReportlyHistoryDTO dto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(dto);
        domain.setAction(ibzproreportlyaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryMapping.toDto(ibzproreportlyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据汇报汇报日志检查汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志检查汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryService.checkKey(ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto)));
    }

    @ApiOperation(value = "根据汇报汇报日志保存汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/save")
    public ResponseEntity<IbzProReportlyHistoryDTO> saveByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistoryDTO ibzproreportlyhistorydto) {
        IbzProReportlyHistory domain = ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydto);
        domain.setAction(ibzproreportlyaction_id);
        ibzproreportlyhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreportlyhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据汇报汇报日志批量保存汇报操作历史", tags = {"汇报操作历史" },  notes = "根据汇报汇报日志批量保存汇报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody List<IbzProReportlyHistoryDTO> ibzproreportlyhistorydtos) {
        List<IbzProReportlyHistory> domainlist=ibzproreportlyhistoryMapping.toDomain(ibzproreportlyhistorydtos);
        for(IbzProReportlyHistory domain:domainlist){
             domain.setAction(ibzproreportlyaction_id);
        }
        ibzproreportlyhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报汇报日志获取数据集", tags = {"汇报操作历史" } ,notes = "根据汇报汇报日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/fetchdefault")
	public ResponseEntity<List<IbzProReportlyHistoryDTO>> fetchIbzProReportlyHistoryDefaultByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id,@RequestBody IbzProReportlyHistorySearchContext context) {
        context.setN_action_eq(ibzproreportlyaction_id);
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
        List<IbzProReportlyHistoryDTO> list = ibzproreportlyhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报汇报日志查询数据集", tags = {"汇报操作历史" } ,notes = "根据汇报汇报日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/ibzproreportlyactions/{ibzproreportlyaction_id}/ibzproreportlyhistories/searchdefault")
	public ResponseEntity<Page<IbzProReportlyHistoryDTO>> searchIbzProReportlyHistoryDefaultByIbzReportlyIbzProReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("ibzproreportlyaction_id") Long ibzproreportlyaction_id, @RequestBody IbzProReportlyHistorySearchContext context) {
        context.setN_action_eq(ibzproreportlyaction_id);
        Page<IbzProReportlyHistory> domains = ibzproreportlyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreportlyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

