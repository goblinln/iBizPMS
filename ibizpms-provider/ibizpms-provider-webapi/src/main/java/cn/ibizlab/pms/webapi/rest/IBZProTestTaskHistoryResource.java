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
import cn.ibizlab.pms.core.ibiz.domain.IBZProTestTaskHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProTestTaskHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProTestTaskHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProTestTaskHistoryRuntime;

@Slf4j
@Api(tags = {"测试单操作历史" })
@RestController("WebApi-ibzprotesttaskhistory")
@RequestMapping("")
public class IBZProTestTaskHistoryResource {

    @Autowired
    public IIBZProTestTaskHistoryService ibzprotesttaskhistoryService;

    @Autowired
    public IBZProTestTaskHistoryRuntime ibzprotesttaskhistoryRuntime;

    @Autowired
    @Lazy
    public IBZProTestTaskHistoryMapping ibzprotesttaskhistoryMapping;

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试单操作历史", tags = {"测试单操作历史" },  notes = "新建测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories")
    @Transactional
    public ResponseEntity<IBZProTestTaskHistoryDTO> create(@Validated @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
		ibzprotesttaskhistoryService.create(domain);
        if(!ibzprotesttaskhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试单操作历史", tags = {"测试单操作历史" },  notes = "批量新建测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        ibzprotesttaskhistoryService.createBatch(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'UPDATE')")
    @ApiOperation(value = "更新测试单操作历史", tags = {"测试单操作历史" },  notes = "更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    @Transactional
    public ResponseEntity<IBZProTestTaskHistoryDTO> update(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
		IBZProTestTaskHistory domain  = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain );
        if(!ibzprotesttaskhistoryRuntime.test(ibzprotesttaskhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(ibzprotesttaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试单操作历史", tags = {"测试单操作历史" },  notes = "批量更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        ibzprotesttaskhistoryService.updateBatch(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'DELETE')")
    @ApiOperation(value = "删除测试单操作历史", tags = {"测试单操作历史" },  notes = "删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试单操作历史", tags = {"测试单操作历史" },  notes = "批量删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotesttaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'READ')")
    @ApiOperation(value = "获取测试单操作历史", tags = {"测试单操作历史" },  notes = "获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> get(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(ibzprotesttaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "获取测试单操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraft(IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试单操作历史", tags = {"测试单操作历史" },  notes = "检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "保存测试单操作历史", tags = {"测试单操作历史" },  notes = "保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> save(@RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        ibzprotesttaskhistoryService.save(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试单操作历史", tags = {"测试单操作历史" },  notes = "批量保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        ibzprotesttaskhistoryService.saveBatch(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试单操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchdefault(@RequestBody IBZProTestTaskHistorySearchContext context) {
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试单操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskhistories/searchdefault")
	public ResponseEntity<Page<IBZProTestTaskHistoryDTO>> searchDefault(@RequestBody IBZProTestTaskHistorySearchContext context) {
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}/{action}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> dynamicCall(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id , @PathVariable("action") String action , @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.dynamicCall(ibzprotesttaskhistory_id, action, ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto));
        ibzprotesttaskhistorydto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistorydto);
    }
    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'CREATE')")
    @ApiOperation(value = "根据测试单日志建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories")
    public ResponseEntity<IBZProTestTaskHistoryDTO> createByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
		ibzprotesttaskhistoryService.create(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'CREATE')")
    @ApiOperation(value = "根据测试单日志批量建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志批量建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'UPDATE')")
    @ApiOperation(value = "根据测试单日志更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> updateByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'UPDATE')")
    @ApiOperation(value = "根据测试单日志批量更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志批量更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'DELETE')")
    @ApiOperation(value = "根据测试单日志删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> removeByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'DELETE')")
    @ApiOperation(value = "根据测试单日志批量删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志批量删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzProTestTaskAction(@RequestBody List<Long> ids) {
        ibzprotesttaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'READ')")
    @ApiOperation(value = "根据测试单日志获取测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试单日志获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "根据测试单日志获取测试单操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraftByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        domain.setAction(ibzprotesttaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试单日志检查测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "根据测试单日志保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> saveByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        ibzprotesttaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试单日志批量保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试单日志批量保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
             domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'READ')")
	@ApiOperation(value = "根据测试单日志获取数据集", tags = {"测试单操作历史" } ,notes = "根据测试单日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchIBZProTestTaskHistoryDefaultByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id,@RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'READ')")
	@ApiOperation(value = "根据测试单日志查询数据集", tags = {"测试单操作历史" } ,notes = "根据测试单日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/searchdefault")
	public ResponseEntity<Page<IBZProTestTaskHistoryDTO>> searchIBZProTestTaskHistoryDefaultByIbzProTestTaskAction(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试单日志建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories")
    public ResponseEntity<IBZProTestTaskHistoryDTO> createByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
		ibzprotesttaskhistoryService.create(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本测试单日志批量建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志批量建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本测试单日志更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> updateByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本测试单日志批量更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志批量更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本测试单日志删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> removeByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本测试单日志批量删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志批量删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByTestTaskIbzProTestTaskAction(@RequestBody List<Long> ids) {
        ibzprotesttaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本测试单日志获取测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试版本测试单日志获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志获取测试单操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraftByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        domain.setAction(ibzprotesttaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试版本测试单日志检查测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "根据测试版本测试单日志保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> saveByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        ibzprotesttaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试版本测试单日志批量保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据测试版本测试单日志批量保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
             domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试单日志获取数据集", tags = {"测试单操作历史" } ,notes = "根据测试版本测试单日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchIBZProTestTaskHistoryDefaultByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id,@RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本测试单日志查询数据集", tags = {"测试单操作历史" } ,notes = "根据测试版本测试单日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/searchdefault")
	public ResponseEntity<Page<IBZProTestTaskHistoryDTO>> searchIBZProTestTaskHistoryDefaultByTestTaskIbzProTestTaskAction(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试单日志建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories")
    public ResponseEntity<IBZProTestTaskHistoryDTO> createByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
		ibzprotesttaskhistoryService.create(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本测试单日志批量建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志批量建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本测试单日志更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> updateByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本测试单日志批量更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志批量更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本测试单日志删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> removeByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本测试单日志批量删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志批量删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestTaskIbzProTestTaskAction(@RequestBody List<Long> ids) {
        ibzprotesttaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本测试单日志获取测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试版本测试单日志获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志获取测试单操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraftByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        domain.setAction(ibzprotesttaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试版本测试单日志检查测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "根据产品测试版本测试单日志保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> saveByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        ibzprotesttaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试版本测试单日志批量保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据产品测试版本测试单日志批量保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
             domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试单日志获取数据集", tags = {"测试单操作历史" } ,notes = "根据产品测试版本测试单日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchIBZProTestTaskHistoryDefaultByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id,@RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本测试单日志查询数据集", tags = {"测试单操作历史" } ,notes = "根据产品测试版本测试单日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/searchdefault")
	public ResponseEntity<Page<IBZProTestTaskHistoryDTO>> searchIBZProTestTaskHistoryDefaultByProductTestTaskIbzProTestTaskAction(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试单日志建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories")
    public ResponseEntity<IBZProTestTaskHistoryDTO> createByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
		ibzprotesttaskhistoryService.create(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本测试单日志批量建立测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志批量建立测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> createBatchByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本测试单日志更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> updateByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本测试单日志批量更新测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志批量更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
            domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本测试单日志删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> removeByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本测试单日志批量删除测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志批量删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestTaskIbzProTestTaskAction(@RequestBody List<Long> ids) {
        ibzprotesttaskhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本测试单日志获取测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目测试版本测试单日志获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志获取测试单操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraftByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        domain.setAction(ibzprotesttaskaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目测试版本测试单日志检查测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "根据项目测试版本测试单日志保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> saveByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setAction(ibzprotesttaskaction_id);
        ibzprotesttaskhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目测试版本测试单日志批量保存测试单操作历史", tags = {"测试单操作历史" },  notes = "根据项目测试版本测试单日志批量保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody List<IBZProTestTaskHistoryDTO> ibzprotesttaskhistorydtos) {
        List<IBZProTestTaskHistory> domainlist=ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydtos);
        for(IBZProTestTaskHistory domain:domainlist){
             domain.setAction(ibzprotesttaskaction_id);
        }
        ibzprotesttaskhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试单日志获取数据集", tags = {"测试单操作历史" } ,notes = "根据项目测试版本测试单日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchIBZProTestTaskHistoryDefaultByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id,@RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本测试单日志查询数据集", tags = {"测试单操作历史" } ,notes = "根据项目测试版本测试单日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}/ibzprotesttaskhistories/searchdefault")
	public ResponseEntity<Page<IBZProTestTaskHistoryDTO>> searchIBZProTestTaskHistoryDefaultByProjectTestTaskIbzProTestTaskAction(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IBZProTestTaskHistorySearchContext context) {
        context.setN_action_eq(ibzprotesttaskaction_id);
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

