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
import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProBugHostoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProBugHostorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProBugHostoryRuntime;

@Slf4j
@Api(tags = {"Bug操作历史" })
@RestController("WebApi-ibzprobughostory")
@RequestMapping("")
public class IBZProBugHostoryResource {

    @Autowired
    public IIBZProBugHostoryService ibzprobughostoryService;

    @Autowired
    public IBZProBugHostoryRuntime ibzprobughostoryRuntime;

    @Autowired
    @Lazy
    public IBZProBugHostoryMapping ibzprobughostoryMapping;

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建Bug操作历史", tags = {"Bug操作历史" },  notes = "新建Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories")
    @Transactional
    public ResponseEntity<IBZProBugHostoryDTO> create(@Validated @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
		ibzprobughostoryService.create(domain);
        if(!ibzprobughostoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建Bug操作历史", tags = {"Bug操作历史" },  notes = "批量新建Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        ibzprobughostoryService.createBatch(ibzprobughostoryMapping.toDomain(ibzprobughostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'UPDATE')")
    @ApiOperation(value = "更新Bug操作历史", tags = {"Bug操作历史" },  notes = "更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobughostories/{ibzprobughostory_id}")
    @Transactional
    public ResponseEntity<IBZProBugHostoryDTO> update(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
		IBZProBugHostory domain  = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setId(ibzprobughostory_id);
		ibzprobughostoryService.update(domain );
        if(!ibzprobughostoryRuntime.test(ibzprobughostory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(ibzprobughostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新Bug操作历史", tags = {"Bug操作历史" },  notes = "批量更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobughostories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        ibzprobughostoryService.updateBatch(ibzprobughostoryMapping.toDomain(ibzprobughostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'DELETE')")
    @ApiOperation(value = "删除Bug操作历史", tags = {"Bug操作历史" },  notes = "删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.remove(ibzprobughostory_id));
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除Bug操作历史", tags = {"Bug操作历史" },  notes = "批量删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobughostories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobughostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'READ')")
    @ApiOperation(value = "获取Bug操作历史", tags = {"Bug操作历史" },  notes = "获取Bug操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> get(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
        IBZProBugHostory domain = ibzprobughostoryService.get(ibzprobughostory_id);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(ibzprobughostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取Bug操作历史草稿", tags = {"Bug操作历史" },  notes = "获取Bug操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobughostories/getdraft")
    public ResponseEntity<IBZProBugHostoryDTO> getDraft(IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(ibzprobughostoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查Bug操作历史", tags = {"Bug操作历史" },  notes = "检查Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.checkKey(ibzprobughostoryMapping.toDomain(ibzprobughostorydto)));
    }

    @ApiOperation(value = "保存Bug操作历史", tags = {"Bug操作历史" },  notes = "保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/save")
    public ResponseEntity<IBZProBugHostoryDTO> save(@RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        ibzprobughostoryService.save(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存Bug操作历史", tags = {"Bug操作历史" },  notes = "批量保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        ibzprobughostoryService.saveBatch(ibzprobughostoryMapping.toDomain(ibzprobughostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"Bug操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobughostories/fetchdefault")
	public ResponseEntity<List<IBZProBugHostoryDTO>> fetchdefault(@RequestBody IBZProBugHostorySearchContext context) {
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
        List<IBZProBugHostoryDTO> list = ibzprobughostoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"Bug操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobughostories/searchdefault")
	public ResponseEntity<Page<IBZProBugHostoryDTO>> searchDefault(@RequestBody IBZProBugHostorySearchContext context) {
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobughostoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/{ibzprobughostory_id}/{action}")
    public ResponseEntity<IBZProBugHostoryDTO> dynamicCall(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id , @PathVariable("action") String action , @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryService.dynamicCall(ibzprobughostory_id, action, ibzprobughostoryMapping.toDomain(ibzprobughostorydto));
        ibzprobughostorydto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostorydto);
    }
    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'CREATE')")
    @ApiOperation(value = "根据Bug日志建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories")
    public ResponseEntity<IBZProBugHostoryDTO> createByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
		ibzprobughostoryService.create(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'CREATE')")
    @ApiOperation(value = "根据Bug日志批量建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志批量建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> createBatchByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'UPDATE')")
    @ApiOperation(value = "根据Bug日志更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> updateByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        domain.setId(ibzprobughostory_id);
		ibzprobughostoryService.update(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'UPDATE')")
    @ApiOperation(value = "根据Bug日志批量更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志批量更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> updateBatchByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'DELETE')")
    @ApiOperation(value = "根据Bug日志删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<Boolean> removeByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.remove(ibzprobughostory_id));
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'DELETE')")
    @ApiOperation(value = "根据Bug日志批量删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志批量删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> removeBatchByIbzProBugAction(@RequestBody List<Long> ids) {
        ibzprobughostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'READ')")
    @ApiOperation(value = "根据Bug日志获取Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志获取Bug操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> getByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
        IBZProBugHostory domain = ibzprobughostoryService.get(ibzprobughostory_id);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据Bug日志获取Bug操作历史草稿", tags = {"Bug操作历史" },  notes = "根据Bug日志获取Bug操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/getdraft")
    public ResponseEntity<IBZProBugHostoryDTO> getDraftByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(dto);
        domain.setAction(ibzprobugaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(ibzprobughostoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据Bug日志检查Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志检查Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.checkKey(ibzprobughostoryMapping.toDomain(ibzprobughostorydto)));
    }

    @ApiOperation(value = "根据Bug日志保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/save")
    public ResponseEntity<IBZProBugHostoryDTO> saveByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        ibzprobughostoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据Bug日志批量保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据Bug日志批量保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
             domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'READ')")
	@ApiOperation(value = "根据Bug日志获取数据集", tags = {"Bug操作历史" } ,notes = "根据Bug日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/fetchdefault")
	public ResponseEntity<List<IBZProBugHostoryDTO>> fetchIBZProBugHostoryDefaultByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id,@RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
        List<IBZProBugHostoryDTO> list = ibzprobughostoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBugActionRuntime.test(#ibzprobugaction_id,'READ')")
	@ApiOperation(value = "根据Bug日志查询数据集", tags = {"Bug操作历史" } ,notes = "根据Bug日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/searchdefault")
	public ResponseEntity<Page<IBZProBugHostoryDTO>> searchIBZProBugHostoryDefaultByIbzProBugAction(@PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobughostoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据BugBug日志建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories")
    public ResponseEntity<IBZProBugHostoryDTO> createByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
		ibzprobughostoryService.create(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'CREATE')")
    @ApiOperation(value = "根据BugBug日志批量建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志批量建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> createBatchByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据BugBug日志更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> updateByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        domain.setId(ibzprobughostory_id);
		ibzprobughostoryService.update(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'UPDATE')")
    @ApiOperation(value = "根据BugBug日志批量更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志批量更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> updateBatchByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据BugBug日志删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<Boolean> removeByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.remove(ibzprobughostory_id));
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'DELETE')")
    @ApiOperation(value = "根据BugBug日志批量删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志批量删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> removeBatchByBugIbzProBugAction(@RequestBody List<Long> ids) {
        ibzprobughostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
    @ApiOperation(value = "根据BugBug日志获取Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志获取Bug操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> getByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
        IBZProBugHostory domain = ibzprobughostoryService.get(ibzprobughostory_id);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据BugBug日志获取Bug操作历史草稿", tags = {"Bug操作历史" },  notes = "根据BugBug日志获取Bug操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/getdraft")
    public ResponseEntity<IBZProBugHostoryDTO> getDraftByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(dto);
        domain.setAction(ibzprobugaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(ibzprobughostoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据BugBug日志检查Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志检查Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/checkkey")
    public ResponseEntity<Boolean> checkKeyByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.checkKey(ibzprobughostoryMapping.toDomain(ibzprobughostorydto)));
    }

    @ApiOperation(value = "根据BugBug日志保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/save")
    public ResponseEntity<IBZProBugHostoryDTO> saveByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        ibzprobughostoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据BugBug日志批量保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据BugBug日志批量保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/savebatch")
    public ResponseEntity<Boolean> saveBatchByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
             domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据BugBug日志获取数据集", tags = {"Bug操作历史" } ,notes = "根据BugBug日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/fetchdefault")
	public ResponseEntity<List<IBZProBugHostoryDTO>> fetchIBZProBugHostoryDefaultByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id,@RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
        List<IBZProBugHostoryDTO> list = ibzprobughostoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BugRuntime.test(#bug_id,'READ')")
	@ApiOperation(value = "根据BugBug日志查询数据集", tags = {"Bug操作历史" } ,notes = "根据BugBug日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/searchdefault")
	public ResponseEntity<Page<IBZProBugHostoryDTO>> searchIBZProBugHostoryDefaultByBugIbzProBugAction(@PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobughostoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品BugBug日志建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories")
    public ResponseEntity<IBZProBugHostoryDTO> createByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
		ibzprobughostoryService.create(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品BugBug日志批量建立Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志批量建立Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> createBatchByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品BugBug日志更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> updateByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        domain.setId(ibzprobughostory_id);
		ibzprobughostoryService.update(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品BugBug日志批量更新Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志批量更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> updateBatchByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
            domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品BugBug日志删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<Boolean> removeByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.remove(ibzprobughostory_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品BugBug日志批量删除Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志批量删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/batch")
    public ResponseEntity<Boolean> removeBatchByProductBugIbzProBugAction(@RequestBody List<Long> ids) {
        ibzprobughostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品BugBug日志获取Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志获取Bug操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> getByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
        IBZProBugHostory domain = ibzprobughostoryService.get(ibzprobughostory_id);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品BugBug日志获取Bug操作历史草稿", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志获取Bug操作历史草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/getdraft")
    public ResponseEntity<IBZProBugHostoryDTO> getDraftByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(dto);
        domain.setAction(ibzprobugaction_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(ibzprobughostoryService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品BugBug日志检查Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志检查Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.checkKey(ibzprobughostoryMapping.toDomain(ibzprobughostorydto)));
    }

    @ApiOperation(value = "根据产品BugBug日志保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/save")
    public ResponseEntity<IBZProBugHostoryDTO> saveByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setAction(ibzprobugaction_id);
        ibzprobughostoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品BugBug日志批量保存Bug操作历史", tags = {"Bug操作历史" },  notes = "根据产品BugBug日志批量保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody List<IBZProBugHostoryDTO> ibzprobughostorydtos) {
        List<IBZProBugHostory> domainlist=ibzprobughostoryMapping.toDomain(ibzprobughostorydtos);
        for(IBZProBugHostory domain:domainlist){
             domain.setAction(ibzprobugaction_id);
        }
        ibzprobughostoryService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品BugBug日志获取数据集", tags = {"Bug操作历史" } ,notes = "根据产品BugBug日志获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/fetchdefault")
	public ResponseEntity<List<IBZProBugHostoryDTO>> fetchIBZProBugHostoryDefaultByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id,@RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
        List<IBZProBugHostoryDTO> list = ibzprobughostoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品BugBug日志查询数据集", tags = {"Bug操作历史" } ,notes = "根据产品BugBug日志查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/bugs/{bug_id}/ibzprobugactions/{ibzprobugaction_id}/ibzprobughostories/searchdefault")
	public ResponseEntity<Page<IBZProBugHostoryDTO>> searchIBZProBugHostoryDefaultByProductBugIbzProBugAction(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("ibzprobugaction_id") Long ibzprobugaction_id, @RequestBody IBZProBugHostorySearchContext context) {
        context.setN_action_eq(ibzprobugaction_id);
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobughostoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

