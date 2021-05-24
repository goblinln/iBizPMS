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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuitHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestSuitHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuitHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestSuitHistoryRuntime;

@Slf4j
@Api(tags = {"套件操作历史" })
@RestController("WebApi-ibztestsuithistory")
@RequestMapping("")
public class IBZTestSuitHistoryResource {

    @Autowired
    public IIBZTestSuitHistoryService ibztestsuithistoryService;

    @Autowired
    public IBZTestSuitHistoryRuntime ibztestsuithistoryRuntime;

    @Autowired
    @Lazy
    public IBZTestSuitHistoryMapping ibztestsuithistoryMapping;

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建套件操作历史", tags = {"套件操作历史" },  notes = "新建套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories")
    @Transactional
    public ResponseEntity<IBZTestSuitHistoryDTO> create(@Validated @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
		ibztestsuithistoryService.create(domain);
        if(!ibztestsuithistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建套件操作历史", tags = {"套件操作历史" },  notes = "批量新建套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        ibztestsuithistoryService.createBatch(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'UPDATE')")
    @ApiOperation(value = "更新套件操作历史", tags = {"套件操作历史" },  notes = "更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    @Transactional
    public ResponseEntity<IBZTestSuitHistoryDTO> update(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
		IBZTestSuitHistory domain  = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setId(ibztestsuithistory_id);
		ibztestsuithistoryService.update(domain );
        if(!ibztestsuithistoryRuntime.test(ibztestsuithistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(ibztestsuithistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新套件操作历史", tags = {"套件操作历史" },  notes = "批量更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        ibztestsuithistoryService.updateBatch(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'DELETE')")
    @ApiOperation(value = "删除套件操作历史", tags = {"套件操作历史" },  notes = "删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.remove(ibztestsuithistory_id));
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除套件操作历史", tags = {"套件操作历史" },  notes = "批量删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztestsuithistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'READ')")
    @ApiOperation(value = "获取套件操作历史", tags = {"套件操作历史" },  notes = "获取套件操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> get(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.get(ibztestsuithistory_id);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(ibztestsuithistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取套件操作历史草稿", tags = {"套件操作历史" },  notes = "获取套件操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuithistories/getdraft")
    public ResponseEntity<IBZTestSuitHistoryDTO> getDraft(IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(ibztestsuithistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查套件操作历史", tags = {"套件操作历史" },  notes = "检查套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.checkKey(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto)));
    }

    @ApiOperation(value = "保存套件操作历史", tags = {"套件操作历史" },  notes = "保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/save")
    public ResponseEntity<IBZTestSuitHistoryDTO> save(@RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        ibztestsuithistoryService.save(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存套件操作历史", tags = {"套件操作历史" },  notes = "批量保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        ibztestsuithistoryService.saveBatch(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"套件操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuithistories/fetchdefault")
	public ResponseEntity<List<IBZTestSuitHistoryDTO>> fetchdefault(@RequestBody IBZTestSuitHistorySearchContext context) {
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
        List<IBZTestSuitHistoryDTO> list = ibztestsuithistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"套件操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuithistories/searchdefault")
	public ResponseEntity<Page<IBZTestSuitHistoryDTO>> searchDefault(@RequestBody IBZTestSuitHistorySearchContext context) {
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuithistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/{ibztestsuithistory_id}/{action}")
    public ResponseEntity<IBZTestSuitHistoryDTO> dynamicCall(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id , @PathVariable("action") String action , @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.dynamicCall(ibztestsuithistory_id, action, ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto));
        ibztestsuithistorydto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistorydto);
    }
    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'CREATE')")
    @ApiOperation(value = "根据套件日志建立套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories")
    public ResponseEntity<IBZTestSuitHistoryDTO> createByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
		ibztestsuithistoryService.create(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'CREATE')")
    @ApiOperation(value = "根据套件日志批量建立套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志批量建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'UPDATE')")
    @ApiOperation(value = "根据套件日志更新套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> updateByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        domain.setId(ibztestsuithistory_id);
		ibztestsuithistoryService.update(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'UPDATE')")
    @ApiOperation(value = "根据套件日志批量更新套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志批量更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'DELETE')")
    @ApiOperation(value = "根据套件日志删除套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<Boolean> removeByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.remove(ibztestsuithistory_id));
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'DELETE')")
    @ApiOperation(value = "根据套件日志批量删除套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志批量删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZTestSuiteAction(@RequestBody List<Long> ids) {
        ibztestsuithistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'READ')")
    @ApiOperation(value = "根据套件日志获取套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志获取套件操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> getByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.get(ibztestsuithistory_id);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据套件日志获取套件操作历史草稿", tags = {"套件操作历史" },  notes = "根据套件日志获取套件操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/getdraft")
    public ResponseEntity<IBZTestSuitHistoryDTO> getDraftByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(dto);
        domain.setAction(ibztestsuiteaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(ibztestsuithistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据套件日志检查套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志检查套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.checkKey(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto)));
    }

    @ApiOperation(value = "根据套件日志保存套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/save")
    public ResponseEntity<IBZTestSuitHistoryDTO> saveByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        ibztestsuithistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据套件日志批量保存套件操作历史", tags = {"套件操作历史" },  notes = "根据套件日志批量保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
             domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'READ')")
	@ApiOperation(value = "根据套件日志获取数据集", tags = {"套件操作历史" } ,notes = "根据套件日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/fetchdefault")
	public ResponseEntity<List<IBZTestSuitHistoryDTO>> fetchIBZTestSuitHistoryDefaultByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id,@RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
        List<IBZTestSuitHistoryDTO> list = ibztestsuithistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'READ')")
	@ApiOperation(value = "根据套件日志查询数据集", tags = {"套件操作历史" } ,notes = "根据套件日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/searchdefault")
	public ResponseEntity<Page<IBZTestSuitHistoryDTO>> searchIBZTestSuitHistoryDefaultByIBZTestSuiteAction(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuithistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件套件日志建立套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories")
    public ResponseEntity<IBZTestSuitHistoryDTO> createByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
		ibztestsuithistoryService.create(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件套件日志批量建立套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志批量建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> createBatchByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件套件日志更新套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> updateByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        domain.setId(ibztestsuithistory_id);
		ibztestsuithistoryService.update(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件套件日志批量更新套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志批量更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> updateBatchByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件套件日志删除套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<Boolean> removeByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.remove(ibztestsuithistory_id));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件套件日志批量删除套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志批量删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> removeBatchByTestSuiteIBZTestSuiteAction(@RequestBody List<Long> ids) {
        ibztestsuithistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件套件日志获取套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志获取套件操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> getByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.get(ibztestsuithistory_id);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试套件套件日志获取套件操作历史草稿", tags = {"套件操作历史" },  notes = "根据测试套件套件日志获取套件操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/getdraft")
    public ResponseEntity<IBZTestSuitHistoryDTO> getDraftByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(dto);
        domain.setAction(ibztestsuiteaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(ibztestsuithistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试套件套件日志检查套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志检查套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.checkKey(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto)));
    }

    @ApiOperation(value = "根据测试套件套件日志保存套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/save")
    public ResponseEntity<IBZTestSuitHistoryDTO> saveByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        ibztestsuithistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试套件套件日志批量保存套件操作历史", tags = {"套件操作历史" },  notes = "根据测试套件套件日志批量保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
             domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件套件日志获取数据集", tags = {"套件操作历史" } ,notes = "根据测试套件套件日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/fetchdefault")
	public ResponseEntity<List<IBZTestSuitHistoryDTO>> fetchIBZTestSuitHistoryDefaultByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id,@RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
        List<IBZTestSuitHistoryDTO> list = ibztestsuithistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件套件日志查询数据集", tags = {"套件操作历史" } ,notes = "根据测试套件套件日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/searchdefault")
	public ResponseEntity<Page<IBZTestSuitHistoryDTO>> searchIBZTestSuitHistoryDefaultByTestSuiteIBZTestSuiteAction(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuithistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件套件日志建立套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories")
    public ResponseEntity<IBZTestSuitHistoryDTO> createByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
		ibztestsuithistoryService.create(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件套件日志批量建立套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志批量建立套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> createBatchByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件套件日志更新套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> updateByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        domain.setId(ibztestsuithistory_id);
		ibztestsuithistoryService.update(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件套件日志批量更新套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志批量更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
            domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件套件日志删除套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<Boolean> removeByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.remove(ibztestsuithistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件套件日志批量删除套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志批量删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestSuiteIBZTestSuiteAction(@RequestBody List<Long> ids) {
        ibztestsuithistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件套件日志获取套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志获取套件操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> getByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.get(ibztestsuithistory_id);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试套件套件日志获取套件操作历史草稿", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志获取套件操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/getdraft")
    public ResponseEntity<IBZTestSuitHistoryDTO> getDraftByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(dto);
        domain.setAction(ibztestsuiteaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(ibztestsuithistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试套件套件日志检查套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志检查套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.checkKey(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto)));
    }

    @ApiOperation(value = "根据产品测试套件套件日志保存套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/save")
    public ResponseEntity<IBZTestSuitHistoryDTO> saveByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setAction(ibztestsuiteaction_id);
        ibztestsuithistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试套件套件日志批量保存套件操作历史", tags = {"套件操作历史" },  notes = "根据产品测试套件套件日志批量保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody List<IBZTestSuitHistoryDTO> ibztestsuithistorydtos) {
        List<IBZTestSuitHistory> domainlist=ibztestsuithistoryMapping.toDomain(ibztestsuithistorydtos);
        for(IBZTestSuitHistory domain:domainlist){
             domain.setAction(ibztestsuiteaction_id);
        }
        ibztestsuithistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件套件日志获取数据集", tags = {"套件操作历史" } ,notes = "根据产品测试套件套件日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/fetchdefault")
	public ResponseEntity<List<IBZTestSuitHistoryDTO>> fetchIBZTestSuitHistoryDefaultByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id,@RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
        List<IBZTestSuitHistoryDTO> list = ibztestsuithistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件套件日志查询数据集", tags = {"套件操作历史" } ,notes = "根据产品测试套件套件日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}/ibztestsuithistories/searchdefault")
	public ResponseEntity<Page<IBZTestSuitHistoryDTO>> searchIBZTestSuitHistoryDefaultByProductTestSuiteIBZTestSuiteAction(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuitHistorySearchContext context) {
        context.setN_action_eq(ibztestsuiteaction_id);
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuithistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

