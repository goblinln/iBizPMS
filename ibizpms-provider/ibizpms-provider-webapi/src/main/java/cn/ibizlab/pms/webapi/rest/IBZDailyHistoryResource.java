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
import cn.ibizlab.pms.core.ibiz.domain.IBZDailyHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZDailyHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZDailyHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZDailyHistoryRuntime;

@Slf4j
@Api(tags = {"日报操作历史" })
@RestController("WebApi-ibzdailyhistory")
@RequestMapping("")
public class IBZDailyHistoryResource {

    @Autowired
    public IIBZDailyHistoryService ibzdailyhistoryService;

    @Autowired
    public IBZDailyHistoryRuntime ibzdailyhistoryRuntime;

    @Autowired
    @Lazy
    public IBZDailyHistoryMapping ibzdailyhistoryMapping;

    @PreAuthorize("@IBZDailyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建日报操作历史", tags = {"日报操作历史" },  notes = "新建日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories")
    @Transactional
    public ResponseEntity<IBZDailyHistoryDTO> create(@Validated @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
		ibzdailyhistoryService.create(domain);
        if(!ibzdailyhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建日报操作历史", tags = {"日报操作历史" },  notes = "批量新建日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        ibzdailyhistoryService.createBatch(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.test(#ibzdailyhistory_id,'UPDATE')")
    @ApiOperation(value = "更新日报操作历史", tags = {"日报操作历史" },  notes = "更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailyhistories/{ibzdailyhistory_id}")
    @Transactional
    public ResponseEntity<IBZDailyHistoryDTO> update(@PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
		IBZDailyHistory domain  = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setId(ibzdailyhistory_id);
		ibzdailyhistoryService.update(domain );
        if(!ibzdailyhistoryRuntime.test(ibzdailyhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyhistoryRuntime.getOPPrivs(ibzdailyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新日报操作历史", tags = {"日报操作历史" },  notes = "批量更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        ibzdailyhistoryService.updateBatch(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.test(#ibzdailyhistory_id,'DELETE')")
    @ApiOperation(value = "删除日报操作历史", tags = {"日报操作历史" },  notes = "删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.remove(ibzdailyhistory_id));
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除日报操作历史", tags = {"日报操作历史" },  notes = "批量删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzdailyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.test(#ibzdailyhistory_id,'READ')")
    @ApiOperation(value = "获取日报操作历史", tags = {"日报操作历史" },  notes = "获取日报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<IBZDailyHistoryDTO> get(@PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
        IBZDailyHistory domain = ibzdailyhistoryService.get(ibzdailyhistory_id);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyhistoryRuntime.getOPPrivs(ibzdailyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取日报操作历史草稿", tags = {"日报操作历史" },  notes = "获取日报操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailyhistories/getdraft")
    public ResponseEntity<IBZDailyHistoryDTO> getDraft(IBZDailyHistoryDTO dto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryMapping.toDto(ibzdailyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查日报操作历史", tags = {"日报操作历史" },  notes = "检查日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.checkKey(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto)));
    }

    @ApiOperation(value = "保存日报操作历史", tags = {"日报操作历史" },  notes = "保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories/save")
    public ResponseEntity<IBZDailyHistoryDTO> save(@RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        ibzdailyhistoryService.save(domain);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存日报操作历史", tags = {"日报操作历史" },  notes = "批量保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        ibzdailyhistoryService.saveBatch(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"日报操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyhistories/fetchdefault")
	public ResponseEntity<List<IBZDailyHistoryDTO>> fetchdefault(@RequestBody IBZDailyHistorySearchContext context) {
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
        List<IBZDailyHistoryDTO> list = ibzdailyhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZDailyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"日报操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyhistories/searchdefault")
	public ResponseEntity<Page<IBZDailyHistoryDTO>> searchDefault(@RequestBody IBZDailyHistorySearchContext context) {
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzdailyhistories/{ibzdailyhistory_id}/{action}")
    public ResponseEntity<IBZDailyHistoryDTO> dynamicCall(@PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id , @PathVariable("action") String action , @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryService.dynamicCall(ibzdailyhistory_id, action, ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto));
        ibzdailyhistorydto = ibzdailyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistorydto);
    }
    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'CREATE')")
    @ApiOperation(value = "根据日报日志建立日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志建立日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories")
    public ResponseEntity<IBZDailyHistoryDTO> createByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
		ibzdailyhistoryService.create(domain);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'CREATE')")
    @ApiOperation(value = "根据日报日志批量建立日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志批量建立日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
            domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'UPDATE')")
    @ApiOperation(value = "根据日报日志更新日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<IBZDailyHistoryDTO> updateByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
        domain.setId(ibzdailyhistory_id);
		ibzdailyhistoryService.update(domain);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'UPDATE')")
    @ApiOperation(value = "根据日报日志批量更新日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志批量更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
            domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'DELETE')")
    @ApiOperation(value = "根据日报日志删除日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<Boolean> removeByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.remove(ibzdailyhistory_id));
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'DELETE')")
    @ApiOperation(value = "根据日报日志批量删除日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志批量删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZDailyAction(@RequestBody List<Long> ids) {
        ibzdailyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'READ')")
    @ApiOperation(value = "根据日报日志获取日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志获取日报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<IBZDailyHistoryDTO> getByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
        IBZDailyHistory domain = ibzdailyhistoryService.get(ibzdailyhistory_id);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据日报日志获取日报操作历史草稿", tags = {"日报操作历史" },  notes = "根据日报日志获取日报操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/getdraft")
    public ResponseEntity<IBZDailyHistoryDTO> getDraftByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, IBZDailyHistoryDTO dto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(dto);
        domain.setAction(ibzdailyaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryMapping.toDto(ibzdailyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据日报日志检查日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志检查日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.checkKey(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto)));
    }

    @ApiOperation(value = "根据日报日志保存日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/save")
    public ResponseEntity<IBZDailyHistoryDTO> saveByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
        ibzdailyhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据日报日志批量保存日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日志批量保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
             domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'READ')")
	@ApiOperation(value = "根据日报日志获取数据集", tags = {"日报操作历史" } ,notes = "根据日报日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/fetchdefault")
	public ResponseEntity<List<IBZDailyHistoryDTO>> fetchIBZDailyHistoryDefaultByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id,@RequestBody IBZDailyHistorySearchContext context) {
        context.setN_action_eq(ibzdailyaction_id);
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
        List<IBZDailyHistoryDTO> list = ibzdailyhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZDailyActionRuntime.test(#ibzdailyaction_id,'READ')")
	@ApiOperation(value = "根据日报日志查询数据集", tags = {"日报操作历史" } ,notes = "根据日报日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/searchdefault")
	public ResponseEntity<Page<IBZDailyHistoryDTO>> searchIBZDailyHistoryDefaultByIBZDailyAction(@PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistorySearchContext context) {
        context.setN_action_eq(ibzdailyaction_id);
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报日报日志建立日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志建立日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories")
    public ResponseEntity<IBZDailyHistoryDTO> createByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
		ibzdailyhistoryService.create(domain);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报日报日志批量建立日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志批量建立日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> createBatchByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
            domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'UPDATE')")
    @ApiOperation(value = "根据日报日报日志更新日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<IBZDailyHistoryDTO> updateByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
        domain.setId(ibzdailyhistory_id);
		ibzdailyhistoryService.update(domain);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'UPDATE')")
    @ApiOperation(value = "根据日报日报日志批量更新日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志批量更新日报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
            domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'DELETE')")
    @ApiOperation(value = "根据日报日报日志删除日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<Boolean> removeByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.remove(ibzdailyhistory_id));
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'DELETE')")
    @ApiOperation(value = "根据日报日报日志批量删除日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志批量删除日报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzDailyIBZDailyAction(@RequestBody List<Long> ids) {
        ibzdailyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
    @ApiOperation(value = "根据日报日报日志获取日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志获取日报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/{ibzdailyhistory_id}")
    public ResponseEntity<IBZDailyHistoryDTO> getByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @PathVariable("ibzdailyhistory_id") Long ibzdailyhistory_id) {
        IBZDailyHistory domain = ibzdailyhistoryService.get(ibzdailyhistory_id);
        IBZDailyHistoryDTO dto = ibzdailyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据日报日报日志获取日报操作历史草稿", tags = {"日报操作历史" },  notes = "根据日报日报日志获取日报操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/getdraft")
    public ResponseEntity<IBZDailyHistoryDTO> getDraftByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, IBZDailyHistoryDTO dto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(dto);
        domain.setAction(ibzdailyaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryMapping.toDto(ibzdailyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据日报日报日志检查日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志检查日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryService.checkKey(ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto)));
    }

    @ApiOperation(value = "根据日报日报日志保存日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/save")
    public ResponseEntity<IBZDailyHistoryDTO> saveByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistoryDTO ibzdailyhistorydto) {
        IBZDailyHistory domain = ibzdailyhistoryMapping.toDomain(ibzdailyhistorydto);
        domain.setAction(ibzdailyaction_id);
        ibzdailyhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据日报日报日志批量保存日报操作历史", tags = {"日报操作历史" },  notes = "根据日报日报日志批量保存日报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody List<IBZDailyHistoryDTO> ibzdailyhistorydtos) {
        List<IBZDailyHistory> domainlist=ibzdailyhistoryMapping.toDomain(ibzdailyhistorydtos);
        for(IBZDailyHistory domain:domainlist){
             domain.setAction(ibzdailyaction_id);
        }
        ibzdailyhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报日报日志获取数据集", tags = {"日报操作历史" } ,notes = "根据日报日报日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/fetchdefault")
	public ResponseEntity<List<IBZDailyHistoryDTO>> fetchIBZDailyHistoryDefaultByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id,@RequestBody IBZDailyHistorySearchContext context) {
        context.setN_action_eq(ibzdailyaction_id);
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
        List<IBZDailyHistoryDTO> list = ibzdailyhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报日报日志查询数据集", tags = {"日报操作历史" } ,notes = "根据日报日报日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/ibzdailyactions/{ibzdailyaction_id}/ibzdailyhistories/searchdefault")
	public ResponseEntity<Page<IBZDailyHistoryDTO>> searchIBZDailyHistoryDefaultByIbzDailyIBZDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("ibzdailyaction_id") Long ibzdailyaction_id, @RequestBody IBZDailyHistorySearchContext context) {
        context.setN_action_eq(ibzdailyaction_id);
        Page<IBZDailyHistory> domains = ibzdailyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

