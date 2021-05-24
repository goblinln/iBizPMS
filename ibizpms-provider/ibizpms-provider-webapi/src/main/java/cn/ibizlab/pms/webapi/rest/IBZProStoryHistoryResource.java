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
import cn.ibizlab.pms.core.ibiz.domain.IBZProStoryHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProStoryHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProStoryHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProStoryHistoryRuntime;

@Slf4j
@Api(tags = {"需求操作历史" })
@RestController("WebApi-ibzprostoryhistory")
@RequestMapping("")
public class IBZProStoryHistoryResource {

    @Autowired
    public IIBZProStoryHistoryService ibzprostoryhistoryService;

    @Autowired
    public IBZProStoryHistoryRuntime ibzprostoryhistoryRuntime;

    @Autowired
    @Lazy
    public IBZProStoryHistoryMapping ibzprostoryhistoryMapping;

    @PreAuthorize("@IBZProStoryHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求操作历史", tags = {"需求操作历史" },  notes = "新建需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories")
    @Transactional
    public ResponseEntity<IBZProStoryHistoryDTO> create(@Validated @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
		ibzprostoryhistoryService.create(domain);
        if(!ibzprostoryhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostoryhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建需求操作历史", tags = {"需求操作历史" },  notes = "批量新建需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        ibzprostoryhistoryService.createBatch(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.test(#ibzprostoryhistory_id,'UPDATE')")
    @ApiOperation(value = "更新需求操作历史", tags = {"需求操作历史" },  notes = "更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostoryhistories/{ibzprostoryhistory_id}")
    @Transactional
    public ResponseEntity<IBZProStoryHistoryDTO> update(@PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
		IBZProStoryHistory domain  = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setId(ibzprostoryhistory_id);
		ibzprostoryhistoryService.update(domain );
        if(!ibzprostoryhistoryRuntime.test(ibzprostoryhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostoryhistoryRuntime.getOPPrivs(ibzprostoryhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新需求操作历史", tags = {"需求操作历史" },  notes = "批量更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        ibzprostoryhistoryService.updateBatch(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.test(#ibzprostoryhistory_id,'DELETE')")
    @ApiOperation(value = "删除需求操作历史", tags = {"需求操作历史" },  notes = "删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.remove(ibzprostoryhistory_id));
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除需求操作历史", tags = {"需求操作历史" },  notes = "批量删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprostoryhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.test(#ibzprostoryhistory_id,'READ')")
    @ApiOperation(value = "获取需求操作历史", tags = {"需求操作历史" },  notes = "获取需求操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> get(@PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
        IBZProStoryHistory domain = ibzprostoryhistoryService.get(ibzprostoryhistory_id);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostoryhistoryRuntime.getOPPrivs(ibzprostoryhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取需求操作历史草稿", tags = {"需求操作历史" },  notes = "获取需求操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostoryhistories/getdraft")
    public ResponseEntity<IBZProStoryHistoryDTO> getDraft(IBZProStoryHistoryDTO dto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(ibzprostoryhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查需求操作历史", tags = {"需求操作历史" },  notes = "检查需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.checkKey(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto)));
    }

    @ApiOperation(value = "保存需求操作历史", tags = {"需求操作历史" },  notes = "保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories/save")
    public ResponseEntity<IBZProStoryHistoryDTO> save(@RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        ibzprostoryhistoryService.save(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostoryhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存需求操作历史", tags = {"需求操作历史" },  notes = "批量保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        ibzprostoryhistoryService.saveBatch(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostoryhistories/fetchdefault")
	public ResponseEntity<List<IBZProStoryHistoryDTO>> fetchdefault(@RequestBody IBZProStoryHistorySearchContext context) {
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
        List<IBZProStoryHistoryDTO> list = ibzprostoryhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProStoryHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"需求操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostoryhistories/searchdefault")
	public ResponseEntity<Page<IBZProStoryHistoryDTO>> searchDefault(@RequestBody IBZProStoryHistorySearchContext context) {
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprostoryhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprostoryhistories/{ibzprostoryhistory_id}/{action}")
    public ResponseEntity<IBZProStoryHistoryDTO> dynamicCall(@PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id , @PathVariable("action") String action , @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryService.dynamicCall(ibzprostoryhistory_id, action, ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto));
        ibzprostoryhistorydto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistorydto);
    }
    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'CREATE')")
    @ApiOperation(value = "根据需求日志建立需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories")
    public ResponseEntity<IBZProStoryHistoryDTO> createByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
		ibzprostoryhistoryService.create(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'CREATE')")
    @ApiOperation(value = "根据需求日志批量建立需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志批量建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> createBatchByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'UPDATE')")
    @ApiOperation(value = "根据需求日志更新需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> updateByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        domain.setId(ibzprostoryhistory_id);
		ibzprostoryhistoryService.update(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'UPDATE')")
    @ApiOperation(value = "根据需求日志批量更新需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志批量更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> updateBatchByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'DELETE')")
    @ApiOperation(value = "根据需求日志删除需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<Boolean> removeByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.remove(ibzprostoryhistory_id));
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'DELETE')")
    @ApiOperation(value = "根据需求日志批量删除需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志批量删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> removeBatchByIBZStoryAction(@RequestBody List<Long> ids) {
        ibzprostoryhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'READ')")
    @ApiOperation(value = "根据需求日志获取需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志获取需求操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> getByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
        IBZProStoryHistory domain = ibzprostoryhistoryService.get(ibzprostoryhistory_id);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求日志获取需求操作历史草稿", tags = {"需求操作历史" },  notes = "根据需求日志获取需求操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/getdraft")
    public ResponseEntity<IBZProStoryHistoryDTO> getDraftByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, IBZProStoryHistoryDTO dto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(dto);
        domain.setAction(ibzstoryaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(ibzprostoryhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求日志检查需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志检查需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.checkKey(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto)));
    }

    @ApiOperation(value = "根据需求日志保存需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/save")
    public ResponseEntity<IBZProStoryHistoryDTO> saveByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        ibzprostoryhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求日志批量保存需求操作历史", tags = {"需求操作历史" },  notes = "根据需求日志批量保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
             domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'READ')")
	@ApiOperation(value = "根据需求日志获取数据集", tags = {"需求操作历史" } ,notes = "根据需求日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/fetchdefault")
	public ResponseEntity<List<IBZProStoryHistoryDTO>> fetchIBZProStoryHistoryDefaultByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id,@RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
        List<IBZProStoryHistoryDTO> list = ibzprostoryhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZStoryActionRuntime.test(#ibzstoryaction_id,'READ')")
	@ApiOperation(value = "根据需求日志查询数据集", tags = {"需求操作历史" } ,notes = "根据需求日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/searchdefault")
	public ResponseEntity<Page<IBZProStoryHistoryDTO>> searchIBZProStoryHistoryDefaultByIBZStoryAction(@PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprostoryhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求需求日志建立需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories")
    public ResponseEntity<IBZProStoryHistoryDTO> createByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
		ibzprostoryhistoryService.create(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求需求日志批量建立需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志批量建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> createBatchByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求需求日志更新需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> updateByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        domain.setId(ibzprostoryhistory_id);
		ibzprostoryhistoryService.update(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求需求日志批量更新需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志批量更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> updateBatchByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求需求日志删除需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<Boolean> removeByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.remove(ibzprostoryhistory_id));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求需求日志批量删除需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志批量删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> removeBatchByStoryIBZStoryAction(@RequestBody List<Long> ids) {
        ibzprostoryhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求需求日志获取需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志获取需求操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> getByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
        IBZProStoryHistory domain = ibzprostoryhistoryService.get(ibzprostoryhistory_id);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求需求日志获取需求操作历史草稿", tags = {"需求操作历史" },  notes = "根据需求需求日志获取需求操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/getdraft")
    public ResponseEntity<IBZProStoryHistoryDTO> getDraftByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, IBZProStoryHistoryDTO dto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(dto);
        domain.setAction(ibzstoryaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(ibzprostoryhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求需求日志检查需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志检查需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.checkKey(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto)));
    }

    @ApiOperation(value = "根据需求需求日志保存需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/save")
    public ResponseEntity<IBZProStoryHistoryDTO> saveByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        ibzprostoryhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求需求日志批量保存需求操作历史", tags = {"需求操作历史" },  notes = "根据需求需求日志批量保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
             domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求需求日志获取数据集", tags = {"需求操作历史" } ,notes = "根据需求需求日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/fetchdefault")
	public ResponseEntity<List<IBZProStoryHistoryDTO>> fetchIBZProStoryHistoryDefaultByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id,@RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
        List<IBZProStoryHistoryDTO> list = ibzprostoryhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求需求日志查询数据集", tags = {"需求操作历史" } ,notes = "根据需求需求日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/searchdefault")
	public ResponseEntity<Page<IBZProStoryHistoryDTO>> searchIBZProStoryHistoryDefaultByStoryIBZStoryAction(@PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprostoryhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求需求日志建立需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories")
    public ResponseEntity<IBZProStoryHistoryDTO> createByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
		ibzprostoryhistoryService.create(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求需求日志批量建立需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志批量建立需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求需求日志更新需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> updateByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        domain.setId(ibzprostoryhistory_id);
		ibzprostoryhistoryService.update(domain);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求需求日志批量更新需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志批量更新需求操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
            domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求需求日志删除需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<Boolean> removeByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.remove(ibzprostoryhistory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求需求日志批量删除需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志批量删除需求操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryIBZStoryAction(@RequestBody List<Long> ids) {
        ibzprostoryhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求需求日志获取需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志获取需求操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/{ibzprostoryhistory_id}")
    public ResponseEntity<IBZProStoryHistoryDTO> getByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @PathVariable("ibzprostoryhistory_id") Long ibzprostoryhistory_id) {
        IBZProStoryHistory domain = ibzprostoryhistoryService.get(ibzprostoryhistory_id);
        IBZProStoryHistoryDTO dto = ibzprostoryhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求需求日志获取需求操作历史草稿", tags = {"需求操作历史" },  notes = "根据产品需求需求日志获取需求操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/getdraft")
    public ResponseEntity<IBZProStoryHistoryDTO> getDraftByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, IBZProStoryHistoryDTO dto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(dto);
        domain.setAction(ibzstoryaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(ibzprostoryhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求需求日志检查需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志检查需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryService.checkKey(ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto)));
    }

    @ApiOperation(value = "根据产品需求需求日志保存需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/save")
    public ResponseEntity<IBZProStoryHistoryDTO> saveByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistoryDTO ibzprostoryhistorydto) {
        IBZProStoryHistory domain = ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydto);
        domain.setAction(ibzstoryaction_id);
        ibzprostoryhistoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryhistoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求需求日志批量保存需求操作历史", tags = {"需求操作历史" },  notes = "根据产品需求需求日志批量保存需求操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody List<IBZProStoryHistoryDTO> ibzprostoryhistorydtos) {
        List<IBZProStoryHistory> domainlist=ibzprostoryhistoryMapping.toDomain(ibzprostoryhistorydtos);
        for(IBZProStoryHistory domain:domainlist){
             domain.setAction(ibzstoryaction_id);
        }
        ibzprostoryhistoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求需求日志获取数据集", tags = {"需求操作历史" } ,notes = "根据产品需求需求日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/fetchdefault")
	public ResponseEntity<List<IBZProStoryHistoryDTO>> fetchIBZProStoryHistoryDefaultByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id,@RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
        List<IBZProStoryHistoryDTO> list = ibzprostoryhistoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求需求日志查询数据集", tags = {"需求操作历史" } ,notes = "根据产品需求需求日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/ibzstoryactions/{ibzstoryaction_id}/ibzprostoryhistories/searchdefault")
	public ResponseEntity<Page<IBZProStoryHistoryDTO>> searchIBZProStoryHistoryDefaultByProductStoryIBZStoryAction(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("ibzstoryaction_id") Long ibzstoryaction_id, @RequestBody IBZProStoryHistorySearchContext context) {
        context.setN_action_eq(ibzstoryaction_id);
        Page<IBZProStoryHistory> domains = ibzprostoryhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprostoryhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

