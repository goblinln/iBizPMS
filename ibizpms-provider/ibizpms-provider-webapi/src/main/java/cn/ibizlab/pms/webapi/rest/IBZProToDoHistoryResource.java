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
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProToDoHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProToDoHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProToDoHistoryRuntime;

@Slf4j
@Api(tags = {"todo操作历史" })
@RestController("WebApi-ibzprotodohistory")
@RequestMapping("")
public class IBZProToDoHistoryResource {

    @Autowired
    public IIBZProToDoHistoryService ibzprotodohistoryService;

    @Autowired
    public IBZProToDoHistoryRuntime ibzprotodohistoryRuntime;

    @Autowired
    @Lazy
    public IBZProToDoHistoryMapping ibzprotodohistoryMapping;

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建todo操作历史", tags = {"todo操作历史" },  notes = "新建todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories")
    @Transactional
    public ResponseEntity<IBZProToDoHistoryDTO> create(@Validated @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
		ibzprotodohistoryService.create(domain);
        if(!ibzprotodohistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建todo操作历史", tags = {"todo操作历史" },  notes = "批量新建todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        ibzprotodohistoryService.createBatch(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id,'UPDATE')")
    @ApiOperation(value = "更新todo操作历史", tags = {"todo操作历史" },  notes = "更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    @Transactional
    public ResponseEntity<IBZProToDoHistoryDTO> update(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
		IBZProToDoHistory domain  = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setId(ibzprotodohistory_id);
		ibzprotodohistoryService.update(domain );
        if(!ibzprotodohistoryRuntime.test(ibzprotodohistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(ibzprotodohistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新todo操作历史", tags = {"todo操作历史" },  notes = "批量更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        ibzprotodohistoryService.updateBatch(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id,'DELETE')")
    @ApiOperation(value = "删除todo操作历史", tags = {"todo操作历史" },  notes = "删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.remove(ibzprotodohistory_id));
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除todo操作历史", tags = {"todo操作历史" },  notes = "批量删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotodohistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id,'READ')")
    @ApiOperation(value = "获取todo操作历史", tags = {"todo操作历史" },  notes = "获取todo操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> get(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
        IBZProToDoHistory domain = ibzprotodohistoryService.get(ibzprotodohistory_id);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(ibzprotodohistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取todo操作历史草稿", tags = {"todo操作历史" },  notes = "获取todo操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodohistories/getdraft")
    public ResponseEntity<IBZProToDoHistoryDTO> getDraft(IBZProToDoHistoryDTO dto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(ibzprotodohistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查todo操作历史", tags = {"todo操作历史" },  notes = "检查todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.checkKey(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto)));
    }

    @ApiOperation(value = "保存todo操作历史", tags = {"todo操作历史" },  notes = "保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/save")
    public ResponseEntity<IBZProToDoHistoryDTO> save(@RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        ibzprotodohistoryService.save(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存todo操作历史", tags = {"todo操作历史" },  notes = "批量保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        ibzprotodohistoryService.saveBatch(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"todo操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodohistories/fetchdefault")
	public ResponseEntity<List<IBZProToDoHistoryDTO>> fetchdefault(@RequestBody IBZProToDoHistorySearchContext context) {
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
        List<IBZProToDoHistoryDTO> list = ibzprotodohistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"todo操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodohistories/searchdefault")
	public ResponseEntity<Page<IBZProToDoHistoryDTO>> searchDefault(@RequestBody IBZProToDoHistorySearchContext context) {
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotodohistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/{ibzprotodohistory_id}/{action}")
    public ResponseEntity<IBZProToDoHistoryDTO> dynamicCall(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id , @PathVariable("action") String action , @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryService.dynamicCall(ibzprotodohistory_id, action, ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto));
        ibzprotodohistorydto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistorydto);
    }
    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'CREATE')")
    @ApiOperation(value = "根据ToDo日志建立todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志建立todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories")
    public ResponseEntity<IBZProToDoHistoryDTO> createByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
		ibzprotodohistoryService.create(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'CREATE')")
    @ApiOperation(value = "根据ToDo日志批量建立todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志批量建立todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
            domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'UPDATE')")
    @ApiOperation(value = "根据ToDo日志更新todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> updateByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
        domain.setId(ibzprotodohistory_id);
		ibzprotodohistoryService.update(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'UPDATE')")
    @ApiOperation(value = "根据ToDo日志批量更新todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志批量更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
            domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'DELETE')")
    @ApiOperation(value = "根据ToDo日志删除todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<Boolean> removeByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.remove(ibzprotodohistory_id));
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'DELETE')")
    @ApiOperation(value = "根据ToDo日志批量删除todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志批量删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZProToDoAction(@RequestBody List<Long> ids) {
        ibzprotodohistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'READ')")
    @ApiOperation(value = "根据ToDo日志获取todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志获取todo操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> getByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
        IBZProToDoHistory domain = ibzprotodohistoryService.get(ibzprotodohistory_id);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据ToDo日志获取todo操作历史草稿", tags = {"todo操作历史" },  notes = "根据ToDo日志获取todo操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/getdraft")
    public ResponseEntity<IBZProToDoHistoryDTO> getDraftByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, IBZProToDoHistoryDTO dto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(dto);
        domain.setAction(ibzprotodoaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(ibzprotodohistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据ToDo日志检查todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志检查todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.checkKey(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto)));
    }

    @ApiOperation(value = "根据ToDo日志保存todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/save")
    public ResponseEntity<IBZProToDoHistoryDTO> saveByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
        ibzprotodohistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据ToDo日志批量保存todo操作历史", tags = {"todo操作历史" },  notes = "根据ToDo日志批量保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
             domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'READ')")
	@ApiOperation(value = "根据ToDo日志获取数据集", tags = {"todo操作历史" } ,notes = "根据ToDo日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/fetchdefault")
	public ResponseEntity<List<IBZProToDoHistoryDTO>> fetchIBZProToDoHistoryDefaultByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id,@RequestBody IBZProToDoHistorySearchContext context) {
        context.setN_action_eq(ibzprotodoaction_id);
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
        List<IBZProToDoHistoryDTO> list = ibzprotodohistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProToDoActionRuntime.test(#ibzprotodoaction_id,'READ')")
	@ApiOperation(value = "根据ToDo日志查询数据集", tags = {"todo操作历史" } ,notes = "根据ToDo日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/searchdefault")
	public ResponseEntity<Page<IBZProToDoHistoryDTO>> searchIBZProToDoHistoryDefaultByIBZProToDoAction(@PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistorySearchContext context) {
        context.setN_action_eq(ibzprotodoaction_id);
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotodohistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办ToDo日志建立todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志建立todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories")
    public ResponseEntity<IBZProToDoHistoryDTO> createByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
		ibzprotodohistoryService.create(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办ToDo日志批量建立todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志批量建立todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> createBatchByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
            domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'UPDATE')")
    @ApiOperation(value = "根据待办ToDo日志更新todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> updateByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
        domain.setId(ibzprotodohistory_id);
		ibzprotodohistoryService.update(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'UPDATE')")
    @ApiOperation(value = "根据待办ToDo日志批量更新todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志批量更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> updateBatchByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
            domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'DELETE')")
    @ApiOperation(value = "根据待办ToDo日志删除todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<Boolean> removeByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.remove(ibzprotodohistory_id));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'DELETE')")
    @ApiOperation(value = "根据待办ToDo日志批量删除todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志批量删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> removeBatchByTodoIBZProToDoAction(@RequestBody List<Long> ids) {
        ibzprotodohistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
    @ApiOperation(value = "根据待办ToDo日志获取todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志获取todo操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> getByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
        IBZProToDoHistory domain = ibzprotodohistoryService.get(ibzprotodohistory_id);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据待办ToDo日志获取todo操作历史草稿", tags = {"todo操作历史" },  notes = "根据待办ToDo日志获取todo操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/getdraft")
    public ResponseEntity<IBZProToDoHistoryDTO> getDraftByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, IBZProToDoHistoryDTO dto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(dto);
        domain.setAction(ibzprotodoaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(ibzprotodohistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据待办ToDo日志检查todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志检查todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.checkKey(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto)));
    }

    @ApiOperation(value = "根据待办ToDo日志保存todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/save")
    public ResponseEntity<IBZProToDoHistoryDTO> saveByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setAction(ibzprotodoaction_id);
        ibzprotodohistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据待办ToDo日志批量保存todo操作历史", tags = {"todo操作历史" },  notes = "根据待办ToDo日志批量保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody List<IBZProToDoHistoryDTO> ibzprotodohistorydtos) {
        List<IBZProToDoHistory> domainlist=ibzprotodohistoryMapping.toDomain(ibzprotodohistorydtos);
        for(IBZProToDoHistory domain:domainlist){
             domain.setAction(ibzprotodoaction_id);
        }
        ibzprotodohistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办ToDo日志获取数据集", tags = {"todo操作历史" } ,notes = "根据待办ToDo日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/fetchdefault")
	public ResponseEntity<List<IBZProToDoHistoryDTO>> fetchIBZProToDoHistoryDefaultByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id,@RequestBody IBZProToDoHistorySearchContext context) {
        context.setN_action_eq(ibzprotodoaction_id);
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
        List<IBZProToDoHistoryDTO> list = ibzprotodohistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办ToDo日志查询数据集", tags = {"todo操作历史" } ,notes = "根据待办ToDo日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/ibzprotodoactions/{ibzprotodoaction_id}/ibzprotodohistories/searchdefault")
	public ResponseEntity<Page<IBZProToDoHistoryDTO>> searchIBZProToDoHistoryDefaultByTodoIBZProToDoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("ibzprotodoaction_id") Long ibzprotodoaction_id, @RequestBody IBZProToDoHistorySearchContext context) {
        context.setN_action_eq(ibzprotodoaction_id);
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotodohistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

