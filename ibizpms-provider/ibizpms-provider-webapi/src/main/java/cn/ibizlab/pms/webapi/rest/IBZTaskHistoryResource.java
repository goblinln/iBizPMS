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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTaskHistoryRuntime;

@Slf4j
@Api(tags = {"任务操作历史" })
@RestController("WebApi-ibztaskhistory")
@RequestMapping("")
public class IBZTaskHistoryResource {

    @Autowired
    public IIBZTaskHistoryService ibztaskhistoryService;

    @Autowired
    public IBZTaskHistoryRuntime ibztaskhistoryRuntime;

    @Autowired
    @Lazy
    public IBZTaskHistoryMapping ibztaskhistoryMapping;

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务操作历史", tags = {"任务操作历史" },  notes = "新建任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories")
    @Transactional
    public ResponseEntity<IBZTaskHistoryDTO> create(@Validated @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
		ibztaskhistoryService.create(domain);
        if(!ibztaskhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建任务操作历史", tags = {"任务操作历史" },  notes = "批量新建任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        ibztaskhistoryService.createBatch(ibztaskhistoryMapping.toDomain(ibztaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'UPDATE')")
    @ApiOperation(value = "更新任务操作历史", tags = {"任务操作历史" },  notes = "更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskhistories/{ibztaskhistory_id}")
    @Transactional
    public ResponseEntity<IBZTaskHistoryDTO> update(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
		IBZTaskHistory domain  = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setId(ibztaskhistory_id);
		ibztaskhistoryService.update(domain );
        if(!ibztaskhistoryRuntime.test(ibztaskhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(ibztaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新任务操作历史", tags = {"任务操作历史" },  notes = "批量更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        ibztaskhistoryService.updateBatch(ibztaskhistoryMapping.toDomain(ibztaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'DELETE')")
    @ApiOperation(value = "删除任务操作历史", tags = {"任务操作历史" },  notes = "删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.remove(ibztaskhistory_id));
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除任务操作历史", tags = {"任务操作历史" },  notes = "批量删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'READ')")
    @ApiOperation(value = "获取任务操作历史", tags = {"任务操作历史" },  notes = "获取任务操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> get(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
        IBZTaskHistory domain = ibztaskhistoryService.get(ibztaskhistory_id);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(ibztaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务操作历史草稿", tags = {"任务操作历史" },  notes = "获取任务操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskhistories/getdraft")
    public ResponseEntity<IBZTaskHistoryDTO> getDraft(IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(ibztaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查任务操作历史", tags = {"任务操作历史" },  notes = "检查任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.checkKey(ibztaskhistoryMapping.toDomain(ibztaskhistorydto)));
    }

    @ApiOperation(value = "保存任务操作历史", tags = {"任务操作历史" },  notes = "保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/save")
    public ResponseEntity<IBZTaskHistoryDTO> save(@RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        ibztaskhistoryService.save(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存任务操作历史", tags = {"任务操作历史" },  notes = "批量保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        ibztaskhistoryService.saveBatch(ibztaskhistoryMapping.toDomain(ibztaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskhistories/fetchdefault")
	public ResponseEntity<List<IBZTaskHistoryDTO>> fetchdefault(@RequestBody IBZTaskHistorySearchContext context) {
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
        List<IBZTaskHistoryDTO> list = ibztaskhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"任务操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskhistories/searchdefault")
	public ResponseEntity<Page<IBZTaskHistoryDTO>> searchDefault(@RequestBody IBZTaskHistorySearchContext context) {
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/{ibztaskhistory_id}/{action}")
    public ResponseEntity<IBZTaskHistoryDTO> dynamicCall(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id , @PathVariable("action") String action , @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryService.dynamicCall(ibztaskhistory_id, action, ibztaskhistoryMapping.toDomain(ibztaskhistorydto));
        ibztaskhistorydto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistorydto);
    }
    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'CREATE')")
    @ApiOperation(value = "根据任务日志建立任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories")
    public ResponseEntity<IBZTaskHistoryDTO> createByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
		ibztaskhistoryService.create(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'CREATE')")
    @ApiOperation(value = "根据任务日志批量建立任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志批量建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'UPDATE')")
    @ApiOperation(value = "根据任务日志更新任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> updateByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        domain.setId(ibztaskhistory_id);
		ibztaskhistoryService.update(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'UPDATE')")
    @ApiOperation(value = "根据任务日志批量更新任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志批量更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'DELETE')")
    @ApiOperation(value = "根据任务日志删除任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<Boolean> removeByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.remove(ibztaskhistory_id));
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'DELETE')")
    @ApiOperation(value = "根据任务日志批量删除任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志批量删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZTaskAction(@RequestBody List<Long> ids) {
        ibztaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
    @ApiOperation(value = "根据任务日志获取任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志获取任务操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> getByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
        IBZTaskHistory domain = ibztaskhistoryService.get(ibztaskhistory_id);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务日志获取任务操作历史草稿", tags = {"任务操作历史" },  notes = "根据任务日志获取任务操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/getdraft")
    public ResponseEntity<IBZTaskHistoryDTO> getDraftByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(dto);
        domain.setAction(ibztaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(ibztaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务日志检查任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志检查任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.checkKey(ibztaskhistoryMapping.toDomain(ibztaskhistorydto)));
    }

    @ApiOperation(value = "根据任务日志保存任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/save")
    public ResponseEntity<IBZTaskHistoryDTO> saveByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        ibztaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务日志批量保存任务操作历史", tags = {"任务操作历史" },  notes = "根据任务日志批量保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
             domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
	@ApiOperation(value = "根据任务日志获取数据集", tags = {"任务操作历史" } ,notes = "根据任务日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/fetchdefault")
	public ResponseEntity<List<IBZTaskHistoryDTO>> fetchIBZTaskHistoryDefaultByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id,@RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
        List<IBZTaskHistoryDTO> list = ibztaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
	@ApiOperation(value = "根据任务日志查询数据集", tags = {"任务操作历史" } ,notes = "根据任务日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/searchdefault")
	public ResponseEntity<Page<IBZTaskHistoryDTO>> searchIBZTaskHistoryDefaultByIBZTaskAction(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务日志建立任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories")
    public ResponseEntity<IBZTaskHistoryDTO> createByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
		ibztaskhistoryService.create(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务任务日志批量建立任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志批量建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务任务日志更新任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> updateByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        domain.setId(ibztaskhistory_id);
		ibztaskhistoryService.update(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务任务日志批量更新任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志批量更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务任务日志删除任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<Boolean> removeByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.remove(ibztaskhistory_id));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务任务日志批量删除任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志批量删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByTaskIBZTaskAction(@RequestBody List<Long> ids) {
        ibztaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务任务日志获取任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志获取任务操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> getByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
        IBZTaskHistory domain = ibztaskhistoryService.get(ibztaskhistory_id);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务任务日志获取任务操作历史草稿", tags = {"任务操作历史" },  notes = "根据任务任务日志获取任务操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/getdraft")
    public ResponseEntity<IBZTaskHistoryDTO> getDraftByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(dto);
        domain.setAction(ibztaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(ibztaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务任务日志检查任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志检查任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.checkKey(ibztaskhistoryMapping.toDomain(ibztaskhistorydto)));
    }

    @ApiOperation(value = "根据任务任务日志保存任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/save")
    public ResponseEntity<IBZTaskHistoryDTO> saveByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        ibztaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务任务日志批量保存任务操作历史", tags = {"任务操作历史" },  notes = "根据任务任务日志批量保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
             domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务任务日志获取数据集", tags = {"任务操作历史" } ,notes = "根据任务任务日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/fetchdefault")
	public ResponseEntity<List<IBZTaskHistoryDTO>> fetchIBZTaskHistoryDefaultByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id,@RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
        List<IBZTaskHistoryDTO> list = ibztaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务任务日志查询数据集", tags = {"任务操作历史" } ,notes = "根据任务任务日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/searchdefault")
	public ResponseEntity<Page<IBZTaskHistoryDTO>> searchIBZTaskHistoryDefaultByTaskIBZTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务日志建立任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories")
    public ResponseEntity<IBZTaskHistoryDTO> createByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
		ibztaskhistoryService.create(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务任务日志批量建立任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志批量建立任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务任务日志更新任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> updateByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        domain.setId(ibztaskhistory_id);
		ibztaskhistoryService.update(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务任务日志批量更新任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志批量更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
            domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务任务日志删除任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<Boolean> removeByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.remove(ibztaskhistory_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务任务日志批量删除任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志批量删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTaskIBZTaskAction(@RequestBody List<Long> ids) {
        ibztaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务任务日志获取任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志获取任务操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> getByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
        IBZTaskHistory domain = ibztaskhistoryService.get(ibztaskhistory_id);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务任务日志获取任务操作历史草稿", tags = {"任务操作历史" },  notes = "根据项目任务任务日志获取任务操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/getdraft")
    public ResponseEntity<IBZTaskHistoryDTO> getDraftByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(dto);
        domain.setAction(ibztaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(ibztaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目任务任务日志检查任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志检查任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.checkKey(ibztaskhistoryMapping.toDomain(ibztaskhistorydto)));
    }

    @ApiOperation(value = "根据项目任务任务日志保存任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/save")
    public ResponseEntity<IBZTaskHistoryDTO> saveByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setAction(ibztaskaction_id);
        ibztaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务任务日志批量保存任务操作历史", tags = {"任务操作历史" },  notes = "根据项目任务任务日志批量保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody List<IBZTaskHistoryDTO> ibztaskhistorydtos) {
        List<IBZTaskHistory> domainlist=ibztaskhistoryMapping.toDomain(ibztaskhistorydtos);
        for(IBZTaskHistory domain:domainlist){
             domain.setAction(ibztaskaction_id);
        }
        ibztaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务任务日志获取数据集", tags = {"任务操作历史" } ,notes = "根据项目任务任务日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/fetchdefault")
	public ResponseEntity<List<IBZTaskHistoryDTO>> fetchIBZTaskHistoryDefaultByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id,@RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
        List<IBZTaskHistoryDTO> list = ibztaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务任务日志查询数据集", tags = {"任务操作历史" } ,notes = "根据项目任务任务日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}/ibztaskhistories/searchdefault")
	public ResponseEntity<Page<IBZTaskHistoryDTO>> searchIBZTaskHistoryDefaultByProjectTaskIBZTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskHistorySearchContext context) {
        context.setN_action_eq(ibztaskaction_id);
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

