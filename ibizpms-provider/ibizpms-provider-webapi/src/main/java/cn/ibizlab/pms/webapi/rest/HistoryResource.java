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
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.HistoryRuntime;

@Slf4j
@Api(tags = {"操作历史" })
@RestController("WebApi-history")
@RequestMapping("")
public class HistoryResource {

    @Autowired
    public IHistoryService historyService;

    @Autowired
    public HistoryRuntime historyRuntime;

    @Autowired
    @Lazy
    public HistoryMapping historyMapping;

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建操作历史", tags = {"操作历史" },  notes = "新建操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/histories")
    @Transactional
    public ResponseEntity<HistoryDTO> create(@Validated @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
		historyService.create(domain);
        if(!historyRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryRuntime.test(#history_id,'UPDATE')")
    @ApiOperation(value = "更新操作历史", tags = {"操作历史" },  notes = "更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/histories/{history_id}")
    @Transactional
    public ResponseEntity<HistoryDTO> update(@PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
		History domain  = historyMapping.toDomain(historydto);
        domain.setId(history_id);
		historyService.update(domain );
        if(!historyRuntime.test(history_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(history_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@HistoryRuntime.test(#history_id,'DELETE')")
    @ApiOperation(value = "删除操作历史", tags = {"操作历史" },  notes = "删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/histories/{history_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("history_id") Long history_id) {
         return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@HistoryRuntime.test(#history_id,'READ')")
    @ApiOperation(value = "获取操作历史", tags = {"操作历史" },  notes = "获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/{history_id}")
    public ResponseEntity<HistoryDTO> get(@PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(history_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取操作历史草稿", tags = {"操作历史" },  notes = "获取操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraft(HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@HistoryRuntime.quickTest('CREATE')")
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
        HistoryDTO dto = historyMapping.toDto(domain);
        Map<String,Integer> opprivs = historyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"操作历史" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchdefault(@RequestBody HistorySearchContext context) {
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@HistoryRuntime.quickTest('READ')")
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
    @PreAuthorize("@ActionRuntime.test(#action_id,'CREATE')")
    @ApiOperation(value = "根据系统日志建立操作历史", tags = {"操作历史" },  notes = "根据系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByAction(@PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ActionRuntime.test(#action_id,'UPDATE')")
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


    @PreAuthorize("@ActionRuntime.test(#action_id,'DELETE')")
    @ApiOperation(value = "根据系统日志删除操作历史", tags = {"操作历史" },  notes = "根据系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByAction(@PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ActionRuntime.test(#action_id,'READ')")
    @ApiOperation(value = "根据系统日志获取操作历史", tags = {"操作历史" },  notes = "根据系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByAction(@PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'CREATE')")
    @ApiOperation(value = "根据系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByAction(@PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ActionRuntime.test(#action_id,'CREATE')")
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


    @PreAuthorize("@ActionRuntime.test(#action_id,'READ')")
	@ApiOperation(value = "根据系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByAction(@PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ActionRuntime.test(#action_id,'READ')")
	@ApiOperation(value = "根据系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByAction(@PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug系统日志建立操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据Bug系统日志更新操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据Bug系统日志删除操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据Bug系统日志获取操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据Bug系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据Bug系统日志检查操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据Bug系统日志保存操作历史", tags = {"操作历史" },  notes = "根据Bug系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据Bug系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据Bug系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductAction(@PathVariable("product_id") Long product_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品计划系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品计划系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品计划系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品计划系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductPlanAction(@PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办系统日志建立操作历史", tags = {"操作历史" },  notes = "根据待办系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'UPDATE')")
    @ApiOperation(value = "根据待办系统日志更新操作历史", tags = {"操作历史" },  notes = "根据待办系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{todo_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'DELETE')")
    @ApiOperation(value = "根据待办系统日志删除操作历史", tags = {"操作历史" },  notes = "根据待办系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
    @ApiOperation(value = "根据待办系统日志获取操作历史", tags = {"操作历史" },  notes = "根据待办系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据待办系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{todo_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@TodoRuntime.test(#todo_id,'CREATE')")
    @ApiOperation(value = "根据待办系统日志检查操作历史", tags = {"操作历史" },  notes = "根据待办系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据待办系统日志保存操作历史", tags = {"操作历史" },  notes = "根据待办系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{todo_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据待办系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TodoRuntime.test(#todo_id,'READ')")
	@ApiOperation(value = "根据待办系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据待办系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByTodoAction(@PathVariable("todo_id") Long todo_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务系统日志建立操作历史", tags = {"操作历史" },  notes = "根据任务系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务系统日志更新操作历史", tags = {"操作历史" },  notes = "根据任务系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务系统日志删除操作历史", tags = {"操作历史" },  notes = "根据任务系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务系统日志获取操作历史", tags = {"操作历史" },  notes = "根据任务系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据任务系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务系统日志检查操作历史", tags = {"操作历史" },  notes = "根据任务系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据任务系统日志保存操作历史", tags = {"操作历史" },  notes = "根据任务系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据任务系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByTaskAction(@PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布系统日志建立操作历史", tags = {"操作历史" },  notes = "根据发布系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据发布系统日志更新操作历史", tags = {"操作历史" },  notes = "根据发布系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "根据发布系统日志删除操作历史", tags = {"操作历史" },  notes = "根据发布系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据发布系统日志获取操作历史", tags = {"操作历史" },  notes = "根据发布系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据发布系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布系统日志检查操作历史", tags = {"操作历史" },  notes = "根据发布系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据发布系统日志保存操作历史", tags = {"操作历史" },  notes = "根据发布系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据发布系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报系统日志建立操作历史", tags = {"操作历史" },  notes = "根据周报系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'UPDATE')")
    @ApiOperation(value = "根据周报系统日志更新操作历史", tags = {"操作历史" },  notes = "根据周报系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'DELETE')")
    @ApiOperation(value = "根据周报系统日志删除操作历史", tags = {"操作历史" },  notes = "根据周报系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
    @ApiOperation(value = "根据周报系统日志获取操作历史", tags = {"操作历史" },  notes = "根据周报系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据周报系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报系统日志检查操作历史", tags = {"操作历史" },  notes = "根据周报系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据周报系统日志保存操作历史", tags = {"操作历史" },  notes = "根据周报系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据周报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据周报系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByIbzWeeklyAction(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告系统日志建立操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'UPDATE')")
    @ApiOperation(value = "根据测试报告系统日志更新操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'DELETE')")
    @ApiOperation(value = "根据测试报告系统日志删除操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
    @ApiOperation(value = "根据测试报告系统日志获取操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试报告系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告系统日志检查操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据测试报告系统日志保存操作历史", tags = {"操作历史" },  notes = "根据测试报告系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据测试报告系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByTestReportAction(@PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库系统日志建立操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库系统日志更新操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库系统日志删除操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库系统日志获取操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档库系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库系统日志检查操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据文档库系统日志保存操作历史", tags = {"操作历史" },  notes = "根据文档库系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据文档库系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByDocLibAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档系统日志建立操作历史", tags = {"操作历史" },  notes = "根据文档系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'UPDATE')")
    @ApiOperation(value = "根据文档系统日志更新操作历史", tags = {"操作历史" },  notes = "根据文档系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'DELETE')")
    @ApiOperation(value = "根据文档系统日志删除操作历史", tags = {"操作历史" },  notes = "根据文档系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据文档系统日志获取操作历史", tags = {"操作历史" },  notes = "根据文档系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档系统日志检查操作历史", tags = {"操作历史" },  notes = "根据文档系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据文档系统日志保存操作历史", tags = {"操作历史" },  notes = "根据文档系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据文档系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByDocAction(@PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报系统日志建立操作历史", tags = {"操作历史" },  notes = "根据日报系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'UPDATE')")
    @ApiOperation(value = "根据日报系统日志更新操作历史", tags = {"操作历史" },  notes = "根据日报系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'DELETE')")
    @ApiOperation(value = "根据日报系统日志删除操作历史", tags = {"操作历史" },  notes = "根据日报系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
    @ApiOperation(value = "根据日报系统日志获取操作历史", tags = {"操作历史" },  notes = "根据日报系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据日报系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'CREATE')")
    @ApiOperation(value = "根据日报系统日志检查操作历史", tags = {"操作历史" },  notes = "根据日报系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据日报系统日志保存操作历史", tags = {"操作历史" },  notes = "根据日报系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据日报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id,'READ')")
	@ApiOperation(value = "根据日报系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据日报系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/{ibzdaily_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByIbzDailyAction(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报系统日志建立操作历史", tags = {"操作历史" },  notes = "根据月报系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'UPDATE')")
    @ApiOperation(value = "根据月报系统日志更新操作历史", tags = {"操作历史" },  notes = "根据月报系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'DELETE')")
    @ApiOperation(value = "根据月报系统日志删除操作历史", tags = {"操作历史" },  notes = "根据月报系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
    @ApiOperation(value = "根据月报系统日志获取操作历史", tags = {"操作历史" },  notes = "根据月报系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据月报系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "根据月报系统日志检查操作历史", tags = {"操作历史" },  notes = "根据月报系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据月报系统日志保存操作历史", tags = {"操作历史" },  notes = "根据月报系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据月报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'READ')")
	@ApiOperation(value = "根据月报系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据月报系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/{ibzmonthly_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByIbzMonthlyAction(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报系统日志建立操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'UPDATE')")
    @ApiOperation(value = "根据汇报系统日志更新操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'DELETE')")
    @ApiOperation(value = "根据汇报系统日志删除操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
    @ApiOperation(value = "根据汇报系统日志获取操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据汇报系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'CREATE')")
    @ApiOperation(value = "根据汇报系统日志检查操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据汇报系统日志保存操作历史", tags = {"操作历史" },  notes = "根据汇报系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据汇报系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzReportlyRuntime.test(#ibzreportly_id,'READ')")
	@ApiOperation(value = "根据汇报系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据汇报系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/{ibzreportly_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByIbzReportlyAction(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据测试版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据测试版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据测试版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件系统日志建立操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件系统日志更新操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件系统日志删除操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件系统日志获取操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试套件系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件系统日志检查操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据测试套件系统日志保存操作历史", tags = {"操作历史" },  notes = "根据测试套件系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据测试套件系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例系统日志建立操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例系统日志更新操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例系统日志删除操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例系统日志获取操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据测试用例系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例系统日志检查操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据测试用例系统日志保存操作历史", tags = {"操作历史" },  notes = "根据测试用例系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据测试用例系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库系统日志建立操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'UPDATE')")
    @ApiOperation(value = "根据用例库系统日志更新操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'DELETE')")
    @ApiOperation(value = "根据用例库系统日志删除操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
    @ApiOperation(value = "根据用例库系统日志获取操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据用例库系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "根据用例库系统日志检查操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据用例库系统日志保存操作历史", tags = {"操作历史" },  notes = "根据用例库系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据用例库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibRuntime.test(#ibzlib_id,'READ')")
	@ApiOperation(value = "根据用例库系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据用例库系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/{ibzlib_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByIbzLibAction(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志建立操作历史", tags = {"操作历史" },  notes = "根据需求系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求系统日志更新操作历史", tags = {"操作历史" },  notes = "根据需求系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求系统日志删除操作历史", tags = {"操作历史" },  notes = "根据需求系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求系统日志获取操作历史", tags = {"操作历史" },  notes = "根据需求系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据需求系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求系统日志检查操作历史", tags = {"操作历史" },  notes = "根据需求系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据需求系统日志保存操作历史", tags = {"操作历史" },  notes = "根据需求系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据需求系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品文档库系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品文档库系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品文档库系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductDocLibAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品Bug系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品Bug系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品Bug系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品Bug系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品Bug系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品Bug系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品Bug系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品Bug系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品Bug系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品Bug系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品发布系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品发布系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品发布系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品发布系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品发布系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品发布系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品发布系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试套件系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品测试套件系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品测试套件系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试套件系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试套件系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品测试版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品测试版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试报告系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试报告系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试报告系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试报告系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品测试报告系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品测试报告系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试报告系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductTestReportAction(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品测试用例系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品测试用例系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品测试用例系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试用例系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品测试用例系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品产品计划系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品产品计划系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品产品计划系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品产品计划系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品产品计划系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductProductPlanAction(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品需求系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品需求系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品需求系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品需求系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档系统日志建立操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库文档系统日志更新操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库文档系统日志删除操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档系统日志获取操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据文档库文档系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档系统日志检查操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据文档库文档系统日志保存操作历史", tags = {"操作历史" },  notes = "根据文档库文档系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据文档库文档系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByDocLibDocAction(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目文档库系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目文档库系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目文档库系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectDocLibAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目任务系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目任务系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目任务系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目任务系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试报告系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试报告系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试报告系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目测试报告系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目测试报告系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目测试报告系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试报告系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试报告系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectTestReportAction(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目测试版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目测试版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目测试版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目测试版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目版本系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目版本系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目版本系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目版本系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档系统日志建立操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库文档系统日志更新操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库文档系统日志删除操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档系统日志获取操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档系统日志检查操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据产品文档库文档系统日志保存操作历史", tags = {"操作历史" },  notes = "根据产品文档库文档系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据产品文档库文档系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProductDocLibDocAction(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档系统日志建立操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志建立操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories")
    public ResponseEntity<HistoryDTO> createByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
		historyService.create(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库文档系统日志更新操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志更新操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> updateByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        domain.setId(history_id);
		historyService.update(domain);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库文档系统日志删除操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志删除操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
		return ResponseEntity.status(HttpStatus.OK).body(historyService.remove(history_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档系统日志获取操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志获取操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/{history_id}")
    public ResponseEntity<HistoryDTO> getByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @PathVariable("history_id") Long history_id) {
        History domain = historyService.get(history_id);
        HistoryDTO dto = historyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档系统日志获取操作历史草稿", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志获取操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/getdraft")
    public ResponseEntity<HistoryDTO> getDraftByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, HistoryDTO dto) {
        History domain = historyMapping.toDomain(dto);
        domain.setAction(action_id);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(historyService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档系统日志检查操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志检查操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(historyService.checkKey(historyMapping.toDomain(historydto)));
    }

    @ApiOperation(value = "根据项目文档库文档系统日志保存操作历史", tags = {"操作历史" },  notes = "根据项目文档库文档系统日志保存操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/save")
    public ResponseEntity<HistoryDTO> saveByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistoryDTO historydto) {
        History domain = historyMapping.toDomain(historydto);
        domain.setAction(action_id);
        historyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(historyMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档系统日志获取DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库文档系统日志获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/fetchdefault")
	public ResponseEntity<List<HistoryDTO>> fetchHistoryDefaultByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id,@RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
        List<HistoryDTO> list = historyMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档系统日志查询DEFAULT", tags = {"操作历史" } ,notes = "根据项目文档库文档系统日志查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/actions/{action_id}/histories/searchdefault")
	public ResponseEntity<Page<HistoryDTO>> searchHistoryDefaultByProjectDocLibDocAction(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("action_id") Long action_id, @RequestBody HistorySearchContext context) {
        context.setN_action_eq(action_id);
        Page<History> domains = historyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(historyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

