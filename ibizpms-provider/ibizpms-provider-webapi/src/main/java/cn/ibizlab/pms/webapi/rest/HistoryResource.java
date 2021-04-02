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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("WebApi-history")
@RequestMapping("")
public class HistoryResource {

    @Autowired
    public IHistoryService historyService;

    @Autowired
    @Lazy
    public HistoryMapping historyMapping;

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'CREATE')")
    @ApiOperation(value = "新建操作历史", tags = {"操作历史" },  notes = "新建操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories")
    public ResponseEntity<HistoryDTO> create(@Validated @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'CREATE')")
    @ApiOperation(value = "批量新建操作历史", tags = {"操作历史" },  notes = "批量新建操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<HistoryDTO> historydtos) {
        historyService.createBatch(historyMapping.toDomain(historydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'UPDATE')")
    @ApiOperation(value = "更新操作历史", tags = {"操作历史" },  notes = "更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/histories/{history_id}")
    public ResponseEntity<HistoryDTO> update(@PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
		History domain  = historyMapping.toDomain(historydto);
        domain .setId(history_id);
		historyService.update(domain );
		HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'UPDATE')")
    @ApiOperation(value = "批量更新操作历史", tags = {"操作历史" },  notes = "批量更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/histories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<HistoryDTO> historydtos) {
        historyService.updateBatch(historyMapping.toDomain(historydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'DELETE')")
    @ApiOperation(value = "删除操作历史", tags = {"操作历史" },  notes = "删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/histories/{history_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("history_id") Long history_id) {
         return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'DELETE')")
    @ApiOperation(value = "批量删除操作历史", tags = {"操作历史" },  notes = "批量删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/histories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        historyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@HistoryModelImpl.test(#history_id,'READ')")
    @ApiOperation(value = "获取操作历史", tags = {"操作历史" },  notes = "获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/{history_id}")
    public ResponseEntity<HistoryDTO> get(@PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取操作历史草稿", tags = {"操作历史" },  notes = "获取操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraft(HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @ApiOperation(value = "检查操作历史", tags = {"操作历史" },  notes = "检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "保存操作历史", tags = {"操作历史" },  notes = "保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/save")
    public ResponseEntity<HistoryDTO> save(@RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存操作历史", tags = {"操作历史" },  notes = "批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<HistoryDTO> historydtos) {
        historyService.saveBatch(historyMapping.toDomain(historydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@HistoryModelImpl.test('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"操作历史" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchDefault(HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@HistoryModelImpl.test('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"操作历史" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchDefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/histories/{history_id}/{action}")
    public ResponseEntity<HistoryDTO> dynamicCall(@PathVariable("history_id") Long history_id , @PathVariable("action") String action , @RequestBody HistoryDTO historydto) {
        History domain = historyService.dynamicCall(history_id, action, historyMapping.toDomain(historydto));
        historydto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historydto);
    }

    @ApiOperation(value = "根据系统日志建立操作历史", tags = {"操作历史" },  notes = "根据系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByAction(@PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据系统日志批量建立操作历史", tags = {"操作历史" },  notes = "根据系统日志批量建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories/batch")
    public ResponseEntity<Boolean> createBatchByAction(@PathVariable("action_id") Long action_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
            domain.setAction(action_id);
        }
        historyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据系统日志更新操作历史", tags = {"操作历史" },  notes = "根据系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByAction(@PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据系统日志批量更新操作历史", tags = {"操作历史" },  notes = "根据系统日志批量更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/actions/{action_id}/histories/batch")
    public ResponseEntity<Boolean> updateBatchByAction(@PathVariable("action_id") Long action_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
            domain.setAction(action_id);
        }
        historyService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据系统日志删除操作历史", tags = {"操作历史" },  notes = "根据系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByAction(@PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }

    @ApiOperation(value = "根据系统日志批量删除操作历史", tags = {"操作历史" },  notes = "根据系统日志批量删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/actions/{action_id}/histories/batch")
    public ResponseEntity<Boolean> removeBatchByAction(@RequestBody List<Long> ids) {
        historyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据系统日志获取操作历史", tags = {"操作历史" },  notes = "根据系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByAction(@PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByAction(@PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @ApiOperation(value = "根据系统日志检查操作历史", tags = {"操作历史" },  notes = "根据系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByAction(@PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据系统日志保存操作历史", tags = {"操作历史" },  notes = "根据系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByAction(@PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }

    @ApiOperation(value = "根据系统日志批量保存操作历史", tags = {"操作历史" },  notes = "根据系统日志批量保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories/savebatch")
    public ResponseEntity<Boolean> saveBatchByAction(@PathVariable("action_id") Long action_id, @RequestBody List<HistoryDTO> historydtos) {
        List<History> domainlist=historyMapping.toDomain(historydtos);
        for(History domain:domainlist){
             domain.setAction(action_id);
        }
        historyService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "根据系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByAction(@PathVariable("action_id") Long action_id,HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "根据系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByAction(@PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

