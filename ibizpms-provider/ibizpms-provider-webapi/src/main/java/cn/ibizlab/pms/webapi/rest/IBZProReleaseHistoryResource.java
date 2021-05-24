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
import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProReleaseHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProReleaseHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProReleaseHistoryRuntime;

@Slf4j
@Api(tags = {"发布操作历史" })
@RestController("WebApi-ibzproreleasehistory")
@RequestMapping("")
public class IBZProReleaseHistoryResource {

    @Autowired
    public IIBZProReleaseHistoryService ibzproreleasehistoryService;

    @Autowired
    public IBZProReleaseHistoryRuntime ibzproreleasehistoryRuntime;

    @Autowired
    @Lazy
    public IBZProReleaseHistoryMapping ibzproreleasehistoryMapping;

    @PreAuthorize("@IBZProReleaseHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建发布操作历史", tags = {"发布操作历史" },  notes = "新建发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories")
    @Transactional
    public ResponseEntity<IBZProReleaseHistoryDTO> create(@Validated @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
		ibzproreleasehistoryService.create(domain);
        if(!ibzproreleasehistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleasehistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建发布操作历史", tags = {"发布操作历史" },  notes = "批量新建发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        ibzproreleasehistoryService.createBatch(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.test(#ibzproreleasehistory_id,'UPDATE')")
    @ApiOperation(value = "更新发布操作历史", tags = {"发布操作历史" },  notes = "更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreleasehistories/{ibzproreleasehistory_id}")
    @Transactional
    public ResponseEntity<IBZProReleaseHistoryDTO> update(@PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
		IBZProReleaseHistory domain  = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setId(ibzproreleasehistory_id);
		ibzproreleasehistoryService.update(domain );
        if(!ibzproreleasehistoryRuntime.test(ibzproreleasehistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleasehistoryRuntime.getOPPrivs(ibzproreleasehistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新发布操作历史", tags = {"发布操作历史" },  notes = "批量更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        ibzproreleasehistoryService.updateBatch(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.test(#ibzproreleasehistory_id,'DELETE')")
    @ApiOperation(value = "删除发布操作历史", tags = {"发布操作历史" },  notes = "删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.remove(ibzproreleasehistory_id));
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除发布操作历史", tags = {"发布操作历史" },  notes = "批量删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproreleasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.test(#ibzproreleasehistory_id,'READ')")
    @ApiOperation(value = "获取发布操作历史", tags = {"发布操作历史" },  notes = "获取发布操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> get(@PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
        IBZProReleaseHistory domain = ibzproreleasehistoryService.get(ibzproreleasehistory_id);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleasehistoryRuntime.getOPPrivs(ibzproreleasehistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取发布操作历史草稿", tags = {"发布操作历史" },  notes = "获取发布操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreleasehistories/getdraft")
    public ResponseEntity<IBZProReleaseHistoryDTO> getDraft(IBZProReleaseHistoryDTO dto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(ibzproreleasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查发布操作历史", tags = {"发布操作历史" },  notes = "检查发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.checkKey(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto)));
    }

    @ApiOperation(value = "保存发布操作历史", tags = {"发布操作历史" },  notes = "保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories/save")
    public ResponseEntity<IBZProReleaseHistoryDTO> save(@RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        ibzproreleasehistoryService.save(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproreleasehistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存发布操作历史", tags = {"发布操作历史" },  notes = "批量保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        ibzproreleasehistoryService.saveBatch(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"发布操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleasehistories/fetchdefault")
	public ResponseEntity<List<IBZProReleaseHistoryDTO>> fetchdefault(@RequestBody IBZProReleaseHistorySearchContext context) {
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
        List<IBZProReleaseHistoryDTO> list = ibzproreleasehistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProReleaseHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"发布操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleasehistories/searchdefault")
	public ResponseEntity<Page<IBZProReleaseHistoryDTO>> searchDefault(@RequestBody IBZProReleaseHistorySearchContext context) {
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreleasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproreleasehistories/{ibzproreleasehistory_id}/{action}")
    public ResponseEntity<IBZProReleaseHistoryDTO> dynamicCall(@PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id , @PathVariable("action") String action , @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryService.dynamicCall(ibzproreleasehistory_id, action, ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto));
        ibzproreleasehistorydto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistorydto);
    }
    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'CREATE')")
    @ApiOperation(value = "根据发布日志建立发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories")
    public ResponseEntity<IBZProReleaseHistoryDTO> createByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
		ibzproreleasehistoryService.create(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'CREATE')")
    @ApiOperation(value = "根据发布日志批量建立发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志批量建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'UPDATE')")
    @ApiOperation(value = "根据发布日志更新发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> updateByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        domain.setId(ibzproreleasehistory_id);
		ibzproreleasehistoryService.update(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'UPDATE')")
    @ApiOperation(value = "根据发布日志批量更新发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志批量更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'DELETE')")
    @ApiOperation(value = "根据发布日志删除发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<Boolean> removeByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.remove(ibzproreleasehistory_id));
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'DELETE')")
    @ApiOperation(value = "根据发布日志批量删除发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志批量删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZProReleaseAction(@RequestBody List<Long> ids) {
        ibzproreleasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'READ')")
    @ApiOperation(value = "根据发布日志获取发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志获取发布操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> getByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
        IBZProReleaseHistory domain = ibzproreleasehistoryService.get(ibzproreleasehistory_id);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据发布日志获取发布操作历史草稿", tags = {"发布操作历史" },  notes = "根据发布日志获取发布操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/getdraft")
    public ResponseEntity<IBZProReleaseHistoryDTO> getDraftByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, IBZProReleaseHistoryDTO dto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(dto);
        domain.setAction(ibzproreleaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(ibzproreleasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据发布日志检查发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志检查发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.checkKey(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto)));
    }

    @ApiOperation(value = "根据发布日志保存发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/save")
    public ResponseEntity<IBZProReleaseHistoryDTO> saveByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        ibzproreleasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据发布日志批量保存发布操作历史", tags = {"发布操作历史" },  notes = "根据发布日志批量保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
             domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'READ')")
	@ApiOperation(value = "根据发布日志获取数据集", tags = {"发布操作历史" } ,notes = "根据发布日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/fetchdefault")
	public ResponseEntity<List<IBZProReleaseHistoryDTO>> fetchIBZProReleaseHistoryDefaultByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id,@RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
        List<IBZProReleaseHistoryDTO> list = ibzproreleasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProReleaseActionRuntime.test(#ibzproreleaseaction_id,'READ')")
	@ApiOperation(value = "根据发布日志查询数据集", tags = {"发布操作历史" } ,notes = "根据发布日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/searchdefault")
	public ResponseEntity<Page<IBZProReleaseHistoryDTO>> searchIBZProReleaseHistoryDefaultByIBZProReleaseAction(@PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreleasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布发布日志建立发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories")
    public ResponseEntity<IBZProReleaseHistoryDTO> createByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
		ibzproreleasehistoryService.create(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'CREATE')")
    @ApiOperation(value = "根据发布发布日志批量建立发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志批量建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> createBatchByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据发布发布日志更新发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> updateByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        domain.setId(ibzproreleasehistory_id);
		ibzproreleasehistoryService.update(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'UPDATE')")
    @ApiOperation(value = "根据发布发布日志批量更新发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志批量更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "根据发布发布日志删除发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<Boolean> removeByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.remove(ibzproreleasehistory_id));
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'DELETE')")
    @ApiOperation(value = "根据发布发布日志批量删除发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志批量删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByReleaseIBZProReleaseAction(@RequestBody List<Long> ids) {
        ibzproreleasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
    @ApiOperation(value = "根据发布发布日志获取发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志获取发布操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> getByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
        IBZProReleaseHistory domain = ibzproreleasehistoryService.get(ibzproreleasehistory_id);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据发布发布日志获取发布操作历史草稿", tags = {"发布操作历史" },  notes = "根据发布发布日志获取发布操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/getdraft")
    public ResponseEntity<IBZProReleaseHistoryDTO> getDraftByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, IBZProReleaseHistoryDTO dto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(dto);
        domain.setAction(ibzproreleaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(ibzproreleasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据发布发布日志检查发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志检查发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.checkKey(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto)));
    }

    @ApiOperation(value = "根据发布发布日志保存发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/save")
    public ResponseEntity<IBZProReleaseHistoryDTO> saveByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        ibzproreleasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据发布发布日志批量保存发布操作历史", tags = {"发布操作历史" },  notes = "根据发布发布日志批量保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
             domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布发布日志获取数据集", tags = {"发布操作历史" } ,notes = "根据发布发布日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/fetchdefault")
	public ResponseEntity<List<IBZProReleaseHistoryDTO>> fetchIBZProReleaseHistoryDefaultByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id,@RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
        List<IBZProReleaseHistoryDTO> list = ibzproreleasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ReleaseRuntime.test(#release_id,'READ')")
	@ApiOperation(value = "根据发布发布日志查询数据集", tags = {"发布操作历史" } ,notes = "根据发布发布日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/searchdefault")
	public ResponseEntity<Page<IBZProReleaseHistoryDTO>> searchIBZProReleaseHistoryDefaultByReleaseIBZProReleaseAction(@PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreleasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布发布日志建立发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories")
    public ResponseEntity<IBZProReleaseHistoryDTO> createByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
		ibzproreleasehistoryService.create(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布发布日志批量建立发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志批量建立发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> createBatchByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布发布日志更新发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> updateByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        domain.setId(ibzproreleasehistory_id);
		ibzproreleasehistoryService.update(domain);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品发布发布日志批量更新发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志批量更新发布操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
            domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品发布发布日志删除发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<Boolean> removeByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.remove(ibzproreleasehistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品发布发布日志批量删除发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志批量删除发布操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductReleaseIBZProReleaseAction(@RequestBody List<Long> ids) {
        ibzproreleasehistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品发布发布日志获取发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志获取发布操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/{ibzproreleasehistory_id}")
    public ResponseEntity<IBZProReleaseHistoryDTO> getByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @PathVariable("ibzproreleasehistory_id") Long ibzproreleasehistory_id) {
        IBZProReleaseHistory domain = ibzproreleasehistoryService.get(ibzproreleasehistory_id);
        IBZProReleaseHistoryDTO dto = ibzproreleasehistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品发布发布日志获取发布操作历史草稿", tags = {"发布操作历史" },  notes = "根据产品发布发布日志获取发布操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/getdraft")
    public ResponseEntity<IBZProReleaseHistoryDTO> getDraftByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, IBZProReleaseHistoryDTO dto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(dto);
        domain.setAction(ibzproreleaseaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(ibzproreleasehistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品发布发布日志检查发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志检查发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryService.checkKey(ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto)));
    }

    @ApiOperation(value = "根据产品发布发布日志保存发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/save")
    public ResponseEntity<IBZProReleaseHistoryDTO> saveByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistoryDTO ibzproreleasehistorydto) {
        IBZProReleaseHistory domain = ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydto);
        domain.setAction(ibzproreleaseaction_id);
        ibzproreleasehistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproreleasehistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品发布发布日志批量保存发布操作历史", tags = {"发布操作历史" },  notes = "根据产品发布发布日志批量保存发布操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody List<IBZProReleaseHistoryDTO> ibzproreleasehistorydtos) {
        List<IBZProReleaseHistory> domainlist=ibzproreleasehistoryMapping.toDomain(ibzproreleasehistorydtos);
        for(IBZProReleaseHistory domain:domainlist){
             domain.setAction(ibzproreleaseaction_id);
        }
        ibzproreleasehistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布发布日志获取数据集", tags = {"发布操作历史" } ,notes = "根据产品发布发布日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/fetchdefault")
	public ResponseEntity<List<IBZProReleaseHistoryDTO>> fetchIBZProReleaseHistoryDefaultByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id,@RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
        List<IBZProReleaseHistoryDTO> list = ibzproreleasehistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布发布日志查询数据集", tags = {"发布操作历史" } ,notes = "根据产品发布发布日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/releases/{release_id}/ibzproreleaseactions/{ibzproreleaseaction_id}/ibzproreleasehistories/searchdefault")
	public ResponseEntity<Page<IBZProReleaseHistoryDTO>> searchIBZProReleaseHistoryDefaultByProductReleaseIBZProReleaseAction(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("ibzproreleaseaction_id") Long ibzproreleaseaction_id, @RequestBody IBZProReleaseHistorySearchContext context) {
        context.setN_action_eq(ibzproreleaseaction_id);
        Page<IBZProReleaseHistory> domains = ibzproreleasehistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproreleasehistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

