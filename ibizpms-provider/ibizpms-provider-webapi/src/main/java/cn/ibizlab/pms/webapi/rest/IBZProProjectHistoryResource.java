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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProjectHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProProjectHistoryRuntime;

@Slf4j
@Api(tags = {"项目操作历史" })
@RestController("WebApi-ibzproprojecthistory")
@RequestMapping("")
public class IBZProProjectHistoryResource {

    @Autowired
    public IIBZProProjectHistoryService ibzproprojecthistoryService;

    @Autowired
    public IBZProProjectHistoryRuntime ibzproprojecthistoryRuntime;

    @Autowired
    @Lazy
    public IBZProProjectHistoryMapping ibzproprojecthistoryMapping;

    @PreAuthorize("@IBZProProjectHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目操作历史", tags = {"项目操作历史" },  notes = "新建项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories")
    @Transactional
    public ResponseEntity<IBZProProjectHistoryDTO> create(@Validated @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
		ibzproprojecthistoryService.create(domain);
        if(!ibzproprojecthistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojecthistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建项目操作历史", tags = {"项目操作历史" },  notes = "批量新建项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        ibzproprojecthistoryService.createBatch(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.test(#ibzproprojecthistory_id,'UPDATE')")
    @ApiOperation(value = "更新项目操作历史", tags = {"项目操作历史" },  notes = "更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojecthistories/{ibzproprojecthistory_id}")
    @Transactional
    public ResponseEntity<IBZProProjectHistoryDTO> update(@PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
		IBZProProjectHistory domain  = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setId(ibzproprojecthistory_id);
		ibzproprojecthistoryService.update(domain );
        if(!ibzproprojecthistoryRuntime.test(ibzproprojecthistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojecthistoryRuntime.getOPPrivs(ibzproprojecthistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新项目操作历史", tags = {"项目操作历史" },  notes = "批量更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        ibzproprojecthistoryService.updateBatch(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.test(#ibzproprojecthistory_id,'DELETE')")
    @ApiOperation(value = "删除项目操作历史", tags = {"项目操作历史" },  notes = "删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.remove(ibzproprojecthistory_id));
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除项目操作历史", tags = {"项目操作历史" },  notes = "批量删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproprojecthistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.test(#ibzproprojecthistory_id,'READ')")
    @ApiOperation(value = "获取项目操作历史", tags = {"项目操作历史" },  notes = "获取项目操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<IBZProProjectHistoryDTO> get(@PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
        IBZProProjectHistory domain = ibzproprojecthistoryService.get(ibzproprojecthistory_id);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojecthistoryRuntime.getOPPrivs(ibzproprojecthistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目操作历史草稿", tags = {"项目操作历史" },  notes = "获取项目操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojecthistories/getdraft")
    public ResponseEntity<IBZProProjectHistoryDTO> getDraft(IBZProProjectHistoryDTO dto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryMapping.toDto(ibzproprojecthistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目操作历史", tags = {"项目操作历史" },  notes = "检查项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.checkKey(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto)));
    }

    @ApiOperation(value = "保存项目操作历史", tags = {"项目操作历史" },  notes = "保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories/save")
    public ResponseEntity<IBZProProjectHistoryDTO> save(@RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        ibzproprojecthistoryService.save(domain);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojecthistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存项目操作历史", tags = {"项目操作历史" },  notes = "批量保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        ibzproprojecthistoryService.saveBatch(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"项目操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojecthistories/fetchdefault")
	public ResponseEntity<List<IBZProProjectHistoryDTO>> fetchdefault(@RequestBody IBZProProjectHistorySearchContext context) {
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
        List<IBZProProjectHistoryDTO> list = ibzproprojecthistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProjectHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"项目操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojecthistories/searchdefault")
	public ResponseEntity<Page<IBZProProjectHistoryDTO>> searchDefault(@RequestBody IBZProProjectHistorySearchContext context) {
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojecthistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproprojecthistories/{ibzproprojecthistory_id}/{action}")
    public ResponseEntity<IBZProProjectHistoryDTO> dynamicCall(@PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id , @PathVariable("action") String action , @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryService.dynamicCall(ibzproprojecthistory_id, action, ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto));
        ibzproprojecthistorydto = ibzproprojecthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistorydto);
    }
    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'CREATE')")
    @ApiOperation(value = "根据项目日志建立项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志建立项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories")
    public ResponseEntity<IBZProProjectHistoryDTO> createByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
		ibzproprojecthistoryService.create(domain);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'CREATE')")
    @ApiOperation(value = "根据项目日志批量建立项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志批量建立项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
            domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'UPDATE')")
    @ApiOperation(value = "根据项目日志更新项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<IBZProProjectHistoryDTO> updateByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
        domain.setId(ibzproprojecthistory_id);
		ibzproprojecthistoryService.update(domain);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'UPDATE')")
    @ApiOperation(value = "根据项目日志批量更新项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志批量更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
            domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'DELETE')")
    @ApiOperation(value = "根据项目日志删除项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<Boolean> removeByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.remove(ibzproprojecthistory_id));
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'DELETE')")
    @ApiOperation(value = "根据项目日志批量删除项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志批量删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZProProjectAction(@RequestBody List<Long> ids) {
        ibzproprojecthistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'READ')")
    @ApiOperation(value = "根据项目日志获取项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志获取项目操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<IBZProProjectHistoryDTO> getByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
        IBZProProjectHistory domain = ibzproprojecthistoryService.get(ibzproprojecthistory_id);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目日志获取项目操作历史草稿", tags = {"项目操作历史" },  notes = "根据项目日志获取项目操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/getdraft")
    public ResponseEntity<IBZProProjectHistoryDTO> getDraftByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, IBZProProjectHistoryDTO dto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(dto);
        domain.setAction(ibzproprojectaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryMapping.toDto(ibzproprojecthistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目日志检查项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志检查项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.checkKey(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto)));
    }

    @ApiOperation(value = "根据项目日志保存项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/save")
    public ResponseEntity<IBZProProjectHistoryDTO> saveByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
        ibzproprojecthistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目日志批量保存项目操作历史", tags = {"项目操作历史" },  notes = "根据项目日志批量保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
             domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'READ')")
	@ApiOperation(value = "根据项目日志获取数据集", tags = {"项目操作历史" } ,notes = "根据项目日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/fetchdefault")
	public ResponseEntity<List<IBZProProjectHistoryDTO>> fetchIBZProProjectHistoryDefaultByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id,@RequestBody IBZProProjectHistorySearchContext context) {
        context.setN_action_eq(ibzproprojectaction_id);
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
        List<IBZProProjectHistoryDTO> list = ibzproprojecthistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProjectActionRuntime.test(#ibzproprojectaction_id,'READ')")
	@ApiOperation(value = "根据项目日志查询数据集", tags = {"项目操作历史" } ,notes = "根据项目日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/searchdefault")
	public ResponseEntity<Page<IBZProProjectHistoryDTO>> searchIBZProProjectHistoryDefaultByIBZProProjectAction(@PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistorySearchContext context) {
        context.setN_action_eq(ibzproprojectaction_id);
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojecthistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目项目日志建立项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志建立项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories")
    public ResponseEntity<IBZProProjectHistoryDTO> createByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
		ibzproprojecthistoryService.create(domain);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目项目日志批量建立项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志批量建立项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> createBatchByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
            domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目项目日志更新项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<IBZProProjectHistoryDTO> updateByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
        domain.setId(ibzproprojecthistory_id);
		ibzproprojecthistoryService.update(domain);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目项目日志批量更新项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志批量更新项目操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> updateBatchByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
            domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目项目日志删除项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<Boolean> removeByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.remove(ibzproprojecthistory_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目项目日志批量删除项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志批量删除项目操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/batch")
    public ResponseEntity<Boolean> removeBatchByProjectIBZProProjectAction(@RequestBody List<Long> ids) {
        ibzproprojecthistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目项目日志获取项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志获取项目操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/{ibzproprojecthistory_id}")
    public ResponseEntity<IBZProProjectHistoryDTO> getByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @PathVariable("ibzproprojecthistory_id") Long ibzproprojecthistory_id) {
        IBZProProjectHistory domain = ibzproprojecthistoryService.get(ibzproprojecthistory_id);
        IBZProProjectHistoryDTO dto = ibzproprojecthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目项目日志获取项目操作历史草稿", tags = {"项目操作历史" },  notes = "根据项目项目日志获取项目操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/getdraft")
    public ResponseEntity<IBZProProjectHistoryDTO> getDraftByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, IBZProProjectHistoryDTO dto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(dto);
        domain.setAction(ibzproprojectaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryMapping.toDto(ibzproprojecthistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目项目日志检查项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志检查项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryService.checkKey(ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto)));
    }

    @ApiOperation(value = "根据项目项目日志保存项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/save")
    public ResponseEntity<IBZProProjectHistoryDTO> saveByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistoryDTO ibzproprojecthistorydto) {
        IBZProProjectHistory domain = ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydto);
        domain.setAction(ibzproprojectaction_id);
        ibzproprojecthistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojecthistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目项目日志批量保存项目操作历史", tags = {"项目操作历史" },  notes = "根据项目项目日志批量保存项目操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody List<IBZProProjectHistoryDTO> ibzproprojecthistorydtos) {
        List<IBZProProjectHistory> domainlist=ibzproprojecthistoryMapping.toDomain(ibzproprojecthistorydtos);
        for(IBZProProjectHistory domain:domainlist){
             domain.setAction(ibzproprojectaction_id);
        }
        ibzproprojecthistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目项目日志获取数据集", tags = {"项目操作历史" } ,notes = "根据项目项目日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/fetchdefault")
	public ResponseEntity<List<IBZProProjectHistoryDTO>> fetchIBZProProjectHistoryDefaultByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id,@RequestBody IBZProProjectHistorySearchContext context) {
        context.setN_action_eq(ibzproprojectaction_id);
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
        List<IBZProProjectHistoryDTO> list = ibzproprojecthistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目项目日志查询数据集", tags = {"项目操作历史" } ,notes = "根据项目项目日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/ibzproprojectactions/{ibzproprojectaction_id}/ibzproprojecthistories/searchdefault")
	public ResponseEntity<Page<IBZProProjectHistoryDTO>> searchIBZProProjectHistoryDefaultByProjectIBZProProjectAction(@PathVariable("project_id") Long project_id, @PathVariable("ibzproprojectaction_id") Long ibzproprojectaction_id, @RequestBody IBZProProjectHistorySearchContext context) {
        context.setN_action_eq(ibzproprojectaction_id);
        Page<IBZProProjectHistory> domains = ibzproprojecthistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojecthistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

