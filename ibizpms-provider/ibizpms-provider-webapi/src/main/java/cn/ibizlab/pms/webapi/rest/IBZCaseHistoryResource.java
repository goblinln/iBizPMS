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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZCaseHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZCaseHistoryRuntime;

@Slf4j
@Api(tags = {"测试操作历史" })
@RestController("WebApi-ibzcasehistory")
@RequestMapping("")
public class IBZCaseHistoryResource {

    @Autowired
    public IIBZCaseHistoryService ibzcasehistoryService;

    @Autowired
    public IBZCaseHistoryRuntime ibzcasehistoryRuntime;

    @Autowired
    @Lazy
    public IBZCaseHistoryMapping ibzcasehistoryMapping;

    @PreAuthorize("@IBZCaseHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试操作历史", tags = {"测试操作历史" },  notes = "新建测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories")
    @Transactional
    public ResponseEntity<IBZCaseHistoryDTO> create(@Validated @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
		ibzcasehistoryService.create(domain);
        if(!ibzcasehistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcasehistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试操作历史", tags = {"测试操作历史" },  notes = "批量新建测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        ibzcasehistoryService.createBatch(ibzcasehistoryMapping.toDomain(ibzcasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.test(#ibzcasehistory_id,'UPDATE')")
    @ApiOperation(value = "更新测试操作历史", tags = {"测试操作历史" },  notes = "更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcasehistories/{ibzcasehistory_id}")
    @Transactional
    public ResponseEntity<IBZCaseHistoryDTO> update(@PathVariable("ibzcasehistory_id") Long ibzcasehistory_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
		IBZCaseHistory domain  = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setId(ibzcasehistory_id);
		ibzcasehistoryService.update(domain );
        if(!ibzcasehistoryRuntime.test(ibzcasehistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcasehistoryRuntime.getOPPrivs(ibzcasehistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试操作历史", tags = {"测试操作历史" },  notes = "批量更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcasehistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        ibzcasehistoryService.updateBatch(ibzcasehistoryMapping.toDomain(ibzcasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.test(#ibzcasehistory_id,'DELETE')")
    @ApiOperation(value = "删除测试操作历史", tags = {"测试操作历史" },  notes = "删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.remove(ibzcasehistory_id));
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试操作历史", tags = {"测试操作历史" },  notes = "批量删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcasehistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzcasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.test(#ibzcasehistory_id,'READ')")
    @ApiOperation(value = "获取测试操作历史", tags = {"测试操作历史" },  notes = "获取测试操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> get(@PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
        IBZCaseHistory domain = ibzcasehistoryService.get(ibzcasehistory_id);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcasehistoryRuntime.getOPPrivs(ibzcasehistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试操作历史草稿", tags = {"测试操作历史" },  notes = "获取测试操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcasehistories/getdraft")
    public ResponseEntity<IBZCaseHistoryDTO> getDraft(IBZCaseHistoryDTO dto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(ibzcasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试操作历史", tags = {"测试操作历史" },  notes = "检查测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.checkKey(ibzcasehistoryMapping.toDomain(ibzcasehistorydto)));
    }

    @ApiOperation(value = "保存测试操作历史", tags = {"测试操作历史" },  notes = "保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories/save")
    public ResponseEntity<IBZCaseHistoryDTO> save(@RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        ibzcasehistoryService.save(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzcasehistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试操作历史", tags = {"测试操作历史" },  notes = "批量保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        ibzcasehistoryService.saveBatch(ibzcasehistoryMapping.toDomain(ibzcasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcasehistories/fetchdefault")
	public ResponseEntity<List<IBZCaseHistoryDTO>> fetchdefault(@RequestBody IBZCaseHistorySearchContext context) {
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
        List<IBZCaseHistoryDTO> list = ibzcasehistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZCaseHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcasehistories/searchdefault")
	public ResponseEntity<Page<IBZCaseHistoryDTO>> searchDefault(@RequestBody IBZCaseHistorySearchContext context) {
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzcasehistories/{ibzcasehistory_id}/{action}")
    public ResponseEntity<IBZCaseHistoryDTO> dynamicCall(@PathVariable("ibzcasehistory_id") Long ibzcasehistory_id , @PathVariable("action") String action , @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryService.dynamicCall(ibzcasehistory_id, action, ibzcasehistoryMapping.toDomain(ibzcasehistorydto));
        ibzcasehistorydto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistorydto);
    }
    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'CREATE')")
    @ApiOperation(value = "根据测试用例日志建立测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories")
    public ResponseEntity<IBZCaseHistoryDTO> createByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
		ibzcasehistoryService.create(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'CREATE')")
    @ApiOperation(value = "根据测试用例日志批量建立测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志批量建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例日志更新测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> updateByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        domain.setId(ibzcasehistory_id);
		ibzcasehistoryService.update(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例日志批量更新测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志批量更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'DELETE')")
    @ApiOperation(value = "根据测试用例日志删除测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<Boolean> removeByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.remove(ibzcasehistory_id));
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'DELETE')")
    @ApiOperation(value = "根据测试用例日志批量删除测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志批量删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZCaseAction(@RequestBody List<Long> ids) {
        ibzcasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'READ')")
    @ApiOperation(value = "根据测试用例日志获取测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志获取测试操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> getByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
        IBZCaseHistory domain = ibzcasehistoryService.get(ibzcasehistory_id);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试用例日志获取测试操作历史草稿", tags = {"测试操作历史" },  notes = "根据测试用例日志获取测试操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/getdraft")
    public ResponseEntity<IBZCaseHistoryDTO> getDraftByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, IBZCaseHistoryDTO dto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(dto);
        domain.setAction(ibzcaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(ibzcasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试用例日志检查测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志检查测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.checkKey(ibzcasehistoryMapping.toDomain(ibzcasehistorydto)));
    }

    @ApiOperation(value = "根据测试用例日志保存测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/save")
    public ResponseEntity<IBZCaseHistoryDTO> saveByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        ibzcasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试用例日志批量保存测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例日志批量保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
             domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'READ')")
	@ApiOperation(value = "根据测试用例日志获取数据集", tags = {"测试操作历史" } ,notes = "根据测试用例日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/fetchdefault")
	public ResponseEntity<List<IBZCaseHistoryDTO>> fetchIBZCaseHistoryDefaultByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id,@RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
        List<IBZCaseHistoryDTO> list = ibzcasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZCaseActionRuntime.test(#ibzcaseaction_id,'READ')")
	@ApiOperation(value = "根据测试用例日志查询数据集", tags = {"测试操作历史" } ,notes = "根据测试用例日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/searchdefault")
	public ResponseEntity<Page<IBZCaseHistoryDTO>> searchIBZCaseHistoryDefaultByIBZCaseAction(@PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例测试用例日志建立测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories")
    public ResponseEntity<IBZCaseHistoryDTO> createByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
		ibzcasehistoryService.create(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例测试用例日志批量建立测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志批量建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> createBatchByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例测试用例日志更新测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> updateByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        domain.setId(ibzcasehistory_id);
		ibzcasehistoryService.update(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例测试用例日志批量更新测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志批量更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例测试用例日志删除测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<Boolean> removeByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.remove(ibzcasehistory_id));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例测试用例日志批量删除测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志批量删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByCaseIBZCaseAction(@RequestBody List<Long> ids) {
        ibzcasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例测试用例日志获取测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志获取测试操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> getByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
        IBZCaseHistory domain = ibzcasehistoryService.get(ibzcasehistory_id);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试用例测试用例日志获取测试操作历史草稿", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志获取测试操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/getdraft")
    public ResponseEntity<IBZCaseHistoryDTO> getDraftByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, IBZCaseHistoryDTO dto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(dto);
        domain.setAction(ibzcaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(ibzcasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试用例测试用例日志检查测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志检查测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.checkKey(ibzcasehistoryMapping.toDomain(ibzcasehistorydto)));
    }

    @ApiOperation(value = "根据测试用例测试用例日志保存测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/save")
    public ResponseEntity<IBZCaseHistoryDTO> saveByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        ibzcasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试用例测试用例日志批量保存测试操作历史", tags = {"测试操作历史" },  notes = "根据测试用例测试用例日志批量保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
             domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例测试用例日志获取数据集", tags = {"测试操作历史" } ,notes = "根据测试用例测试用例日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/fetchdefault")
	public ResponseEntity<List<IBZCaseHistoryDTO>> fetchIBZCaseHistoryDefaultByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id,@RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
        List<IBZCaseHistoryDTO> list = ibzcasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例测试用例日志查询数据集", tags = {"测试操作历史" } ,notes = "根据测试用例测试用例日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/searchdefault")
	public ResponseEntity<Page<IBZCaseHistoryDTO>> searchIBZCaseHistoryDefaultByCaseIBZCaseAction(@PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志建立测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories")
    public ResponseEntity<IBZCaseHistoryDTO> createByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
		ibzcasehistoryService.create(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志批量建立测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志批量建立测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> createBatchByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志更新测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> updateByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        domain.setId(ibzcasehistory_id);
		ibzcasehistoryService.update(domain);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志批量更新测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志批量更新测试操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
            domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志删除测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<Boolean> removeByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.remove(ibzcasehistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例测试用例日志批量删除测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志批量删除测试操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductCaseIBZCaseAction(@RequestBody List<Long> ids) {
        ibzcasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例测试用例日志获取测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志获取测试操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/{ibzcasehistory_id}")
    public ResponseEntity<IBZCaseHistoryDTO> getByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @PathVariable("ibzcasehistory_id") Long ibzcasehistory_id) {
        IBZCaseHistory domain = ibzcasehistoryService.get(ibzcasehistory_id);
        IBZCaseHistoryDTO dto = ibzcasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试用例测试用例日志获取测试操作历史草稿", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志获取测试操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/getdraft")
    public ResponseEntity<IBZCaseHistoryDTO> getDraftByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, IBZCaseHistoryDTO dto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(dto);
        domain.setAction(ibzcaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(ibzcasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试用例测试用例日志检查测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志检查测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryService.checkKey(ibzcasehistoryMapping.toDomain(ibzcasehistorydto)));
    }

    @ApiOperation(value = "根据产品测试用例测试用例日志保存测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/save")
    public ResponseEntity<IBZCaseHistoryDTO> saveByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistoryDTO ibzcasehistorydto) {
        IBZCaseHistory domain = ibzcasehistoryMapping.toDomain(ibzcasehistorydto);
        domain.setAction(ibzcaseaction_id);
        ibzcasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzcasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试用例测试用例日志批量保存测试操作历史", tags = {"测试操作历史" },  notes = "根据产品测试用例测试用例日志批量保存测试操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody List<IBZCaseHistoryDTO> ibzcasehistorydtos) {
        List<IBZCaseHistory> domainlist=ibzcasehistoryMapping.toDomain(ibzcasehistorydtos);
        for(IBZCaseHistory domain:domainlist){
             domain.setAction(ibzcaseaction_id);
        }
        ibzcasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例测试用例日志获取数据集", tags = {"测试操作历史" } ,notes = "根据产品测试用例测试用例日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/fetchdefault")
	public ResponseEntity<List<IBZCaseHistoryDTO>> fetchIBZCaseHistoryDefaultByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id,@RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
        List<IBZCaseHistoryDTO> list = ibzcasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例测试用例日志查询数据集", tags = {"测试操作历史" } ,notes = "根据产品测试用例测试用例日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/ibzcaseactions/{ibzcaseaction_id}/ibzcasehistories/searchdefault")
	public ResponseEntity<Page<IBZCaseHistoryDTO>> searchIBZCaseHistoryDefaultByProductCaseIBZCaseAction(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("ibzcaseaction_id") Long ibzcaseaction_id, @RequestBody IBZCaseHistorySearchContext context) {
        context.setN_action_eq(ibzcaseaction_id);
        Page<IBZCaseHistory> domains = ibzcasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzcasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

