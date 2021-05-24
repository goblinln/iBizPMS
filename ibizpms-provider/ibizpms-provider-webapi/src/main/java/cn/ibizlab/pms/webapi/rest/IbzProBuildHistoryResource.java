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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildHistory;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBuildHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProBuildHistoryRuntime;

@Slf4j
@Api(tags = {"版本操作历史" })
@RestController("WebApi-ibzprobuildhistory")
@RequestMapping("")
public class IbzProBuildHistoryResource {

    @Autowired
    public IIbzProBuildHistoryService ibzprobuildhistoryService;

    @Autowired
    public IbzProBuildHistoryRuntime ibzprobuildhistoryRuntime;

    @Autowired
    @Lazy
    public IbzProBuildHistoryMapping ibzprobuildhistoryMapping;

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建版本操作历史", tags = {"版本操作历史" },  notes = "新建版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories")
    @Transactional
    public ResponseEntity<IbzProBuildHistoryDTO> create(@Validated @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
		ibzprobuildhistoryService.create(domain);
        if(!ibzprobuildhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建版本操作历史", tags = {"版本操作历史" },  notes = "批量新建版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        ibzprobuildhistoryService.createBatch(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id,'UPDATE')")
    @ApiOperation(value = "更新版本操作历史", tags = {"版本操作历史" },  notes = "更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    @Transactional
    public ResponseEntity<IbzProBuildHistoryDTO> update(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
		IbzProBuildHistory domain  = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain );
        if(!ibzprobuildhistoryRuntime.test(ibzprobuildhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(ibzprobuildhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新版本操作历史", tags = {"版本操作历史" },  notes = "批量更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        ibzprobuildhistoryService.updateBatch(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id,'DELETE')")
    @ApiOperation(value = "删除版本操作历史", tags = {"版本操作历史" },  notes = "删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除版本操作历史", tags = {"版本操作历史" },  notes = "批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id,'READ')")
    @ApiOperation(value = "获取版本操作历史", tags = {"版本操作历史" },  notes = "获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> get(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(ibzprobuildhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "获取版本操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraft(IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查版本操作历史", tags = {"版本操作历史" },  notes = "检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @ApiOperation(value = "保存版本操作历史", tags = {"版本操作历史" },  notes = "保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> save(@RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        ibzprobuildhistoryService.save(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存版本操作历史", tags = {"版本操作历史" },  notes = "批量保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        ibzprobuildhistoryService.saveBatch(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"版本操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchdefault(@RequestBody IbzProBuildHistorySearchContext context) {
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"版本操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildhistories/searchdefault")
	public ResponseEntity<Page<IbzProBuildHistoryDTO>> searchDefault(@RequestBody IbzProBuildHistorySearchContext context) {
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}/{action}")
    public ResponseEntity<IbzProBuildHistoryDTO> dynamicCall(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id , @PathVariable("action") String action , @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.dynamicCall(ibzprobuildhistory_id, action, ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto));
        ibzprobuildhistorydto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistorydto);
    }
    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'CREATE')")
    @ApiOperation(value = "根据版本日志建立版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories")
    public ResponseEntity<IbzProBuildHistoryDTO> createByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
		ibzprobuildhistoryService.create(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'CREATE')")
    @ApiOperation(value = "根据版本日志批量建立版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志批量建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> createBatchByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'UPDATE')")
    @ApiOperation(value = "根据版本日志更新版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> updateByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'UPDATE')")
    @ApiOperation(value = "根据版本日志批量更新版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志批量更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'DELETE')")
    @ApiOperation(value = "根据版本日志删除版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> removeByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'DELETE')")
    @ApiOperation(value = "根据版本日志批量删除版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzProBuildAction(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'READ')")
    @ApiOperation(value = "根据版本日志获取版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> getByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据版本日志获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "根据版本日志获取版本操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraftByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        domain.setAction(ibzprobuildaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据版本日志检查版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @ApiOperation(value = "根据版本日志保存版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> saveByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        ibzprobuildhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据版本日志批量保存版本操作历史", tags = {"版本操作历史" },  notes = "根据版本日志批量保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
             domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'READ')")
	@ApiOperation(value = "根据版本日志获取数据集", tags = {"版本操作历史" } ,notes = "根据版本日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchIbzProBuildHistoryDefaultByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id,@RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'READ')")
	@ApiOperation(value = "根据版本日志查询数据集", tags = {"版本操作历史" } ,notes = "根据版本日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/searchdefault")
	public ResponseEntity<Page<IbzProBuildHistoryDTO>> searchIbzProBuildHistoryDefaultByIbzProBuildAction(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本版本日志建立版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories")
    public ResponseEntity<IbzProBuildHistoryDTO> createByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
		ibzprobuildhistoryService.create(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本版本日志批量建立版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志批量建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> createBatchByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本版本日志更新版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> updateByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本版本日志批量更新版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志批量更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> updateBatchByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本版本日志删除版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> removeByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本版本日志批量删除版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatchByBuildIbzProBuildAction(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本版本日志获取版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> getByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据版本版本日志获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "根据版本版本日志获取版本操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraftByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        domain.setAction(ibzprobuildaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据版本版本日志检查版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @ApiOperation(value = "根据版本版本日志保存版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> saveByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        ibzprobuildhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据版本版本日志批量保存版本操作历史", tags = {"版本操作历史" },  notes = "根据版本版本日志批量保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
             domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本版本日志获取数据集", tags = {"版本操作历史" } ,notes = "根据版本版本日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchIbzProBuildHistoryDefaultByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id,@RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本版本日志查询数据集", tags = {"版本操作历史" } ,notes = "根据版本版本日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/searchdefault")
	public ResponseEntity<Page<IbzProBuildHistoryDTO>> searchIbzProBuildHistoryDefaultByBuildIbzProBuildAction(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本版本日志建立版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories")
    public ResponseEntity<IbzProBuildHistoryDTO> createByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
		ibzprobuildhistoryService.create(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本版本日志批量建立版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志批量建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本版本日志更新版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> updateByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本版本日志批量更新版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志批量更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本版本日志删除版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> removeByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本版本日志批量删除版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductBuildIbzProBuildAction(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本版本日志获取版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> getByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品版本版本日志获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "根据产品版本版本日志获取版本操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraftByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        domain.setAction(ibzprobuildaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品版本版本日志检查版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @ApiOperation(value = "根据产品版本版本日志保存版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> saveByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        ibzprobuildhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品版本版本日志批量保存版本操作历史", tags = {"版本操作历史" },  notes = "根据产品版本版本日志批量保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
             domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本版本日志获取数据集", tags = {"版本操作历史" } ,notes = "根据产品版本版本日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchIbzProBuildHistoryDefaultByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id,@RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本版本日志查询数据集", tags = {"版本操作历史" } ,notes = "根据产品版本版本日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/searchdefault")
	public ResponseEntity<Page<IbzProBuildHistoryDTO>> searchIbzProBuildHistoryDefaultByProductBuildIbzProBuildAction(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本版本日志建立版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories")
    public ResponseEntity<IbzProBuildHistoryDTO> createByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
		ibzprobuildhistoryService.create(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本版本日志批量建立版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志批量建立版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> createBatchByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本版本日志更新版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> updateByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本版本日志批量更新版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志批量更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
            domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本版本日志删除版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> removeByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本版本日志批量删除版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBuildIbzProBuildAction(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本版本日志获取版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> getByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目版本版本日志获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "根据项目版本版本日志获取版本操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraftByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        domain.setAction(ibzprobuildaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目版本版本日志检查版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @ApiOperation(value = "根据项目版本版本日志保存版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> saveByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setAction(ibzprobuildaction_id);
        ibzprobuildhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目版本版本日志批量保存版本操作历史", tags = {"版本操作历史" },  notes = "根据项目版本版本日志批量保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody List<IbzProBuildHistoryDTO> ibzprobuildhistorydtos) {
        List<IbzProBuildHistory> domainlist=ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydtos);
        for(IbzProBuildHistory domain:domainlist){
             domain.setAction(ibzprobuildaction_id);
        }
        ibzprobuildhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本版本日志获取数据集", tags = {"版本操作历史" } ,notes = "根据项目版本版本日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchIbzProBuildHistoryDefaultByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id,@RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本版本日志查询数据集", tags = {"版本操作历史" } ,notes = "根据项目版本版本日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}/ibzprobuildhistories/searchdefault")
	public ResponseEntity<Page<IbzProBuildHistoryDTO>> searchIbzProBuildHistoryDefaultByProjectBuildIbzProBuildAction(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildHistorySearchContext context) {
        context.setN_action_eq(ibzprobuildaction_id);
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

