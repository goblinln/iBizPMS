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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTaskActionRuntime;

@Slf4j
@Api(tags = {"任务日志" })
@RestController("WebApi-ibztaskaction")
@RequestMapping("")
public class IBZTaskActionResource {

    @Autowired
    public IIBZTaskActionService ibztaskactionService;

    @Autowired
    public IBZTaskActionRuntime ibztaskactionRuntime;

    @Autowired
    @Lazy
    public IBZTaskActionMapping ibztaskactionMapping;

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务日志", tags = {"任务日志" },  notes = "新建任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> create(@Validated @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
		ibztaskactionService.create(domain);
        if(!ibztaskactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建任务日志", tags = {"任务日志" },  notes = "批量新建任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.createBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'UPDATE')")
    @ApiOperation(value = "更新任务日志", tags = {"任务日志" },  notes = "更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/{ibztaskaction_id}")
    @Transactional
    public ResponseEntity<IBZTaskActionDTO> update(@PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
		IBZTaskAction domain  = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain );
        if(!ibztaskactionRuntime.test(ibztaskaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(ibztaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新任务日志", tags = {"任务日志" },  notes = "批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.updateBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'DELETE')")
    @ApiOperation(value = "删除任务日志", tags = {"任务日志" },  notes = "删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除任务日志", tags = {"任务日志" },  notes = "批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.test(#ibztaskaction_id,'READ')")
    @ApiOperation(value = "获取任务日志", tags = {"任务日志" },  notes = "获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> get(@PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(ibztaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取任务日志草稿", tags = {"任务日志" },  notes = "获取任务日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraft(IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查任务日志", tags = {"任务日志" },  notes = "检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "保存任务日志", tags = {"任务日志" },  notes = "保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> save(@RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        ibztaskactionService.save(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存任务日志", tags = {"任务日志" },  notes = "批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        ibztaskactionService.saveBatch(ibztaskactionMapping.toDomain(ibztaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchdefault(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"任务日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchDefault(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchtype(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchType(@RequestBody IBZTaskActionSearchContext context) {
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztaskactions/{ibztaskaction_id}/{action}")
    public ResponseEntity<IBZTaskActionDTO> dynamicCall(@PathVariable("ibztaskaction_id") Long ibztaskaction_id , @PathVariable("action") String action , @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionService.dynamicCall(ibztaskaction_id, action, ibztaskactionMapping.toDomain(ibztaskactiondto));
        ibztaskactiondto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactiondto);
    }
    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务建立任务日志", tags = {"任务日志" },  notes = "根据任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByTask(@PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'CREATE')")
    @ApiOperation(value = "根据任务批量建立任务日志", tags = {"任务日志" },  notes = "根据任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务更新任务日志", tags = {"任务日志" },  notes = "根据任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByTask(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'UPDATE')")
    @ApiOperation(value = "根据任务批量更新任务日志", tags = {"任务日志" },  notes = "根据任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务删除任务日志", tags = {"任务日志" },  notes = "根据任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByTask(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'DELETE')")
    @ApiOperation(value = "根据任务批量删除任务日志", tags = {"任务日志" },  notes = "根据任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
    @ApiOperation(value = "根据任务获取任务日志", tags = {"任务日志" },  notes = "根据任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByTask(@PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByTask(@PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务检查任务日志", tags = {"任务日志" },  notes = "根据任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTask(@PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据任务保存任务日志", tags = {"任务日志" },  notes = "根据任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByTask(@PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务批量保存任务日志", tags = {"任务日志" },  notes = "根据任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByTask(@PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取数据集", tags = {"任务日志" } ,notes = "根据任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByTask(@PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询数据集", tags = {"任务日志" } ,notes = "根据任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByTask(@PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByTask(@PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TaskRuntime.test(#task_id,'READ')")
	@ApiOperation(value = "根据任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByTask(@PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务建立任务日志", tags = {"任务日志" },  notes = "根据任务模块任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'CREATE')")
    @ApiOperation(value = "根据任务模块任务批量建立任务日志", tags = {"任务日志" },  notes = "根据任务模块任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务更新任务日志", tags = {"任务日志" },  notes = "根据任务模块任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'UPDATE')")
    @ApiOperation(value = "根据任务模块任务批量更新任务日志", tags = {"任务日志" },  notes = "根据任务模块任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块任务删除任务日志", tags = {"任务日志" },  notes = "根据任务模块任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'DELETE')")
    @ApiOperation(value = "根据任务模块任务批量删除任务日志", tags = {"任务日志" },  notes = "根据任务模块任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectModuleTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
    @ApiOperation(value = "根据任务模块任务获取任务日志", tags = {"任务日志" },  notes = "根据任务模块任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据任务模块任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据任务模块任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据任务模块任务检查任务日志", tags = {"任务日志" },  notes = "根据任务模块任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据任务模块任务保存任务日志", tags = {"任务日志" },  notes = "根据任务模块任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据任务模块任务批量保存任务日志", tags = {"任务日志" },  notes = "根据任务模块任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务获取数据集", tags = {"任务日志" } ,notes = "根据任务模块任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务查询数据集", tags = {"任务日志" } ,notes = "根据任务模块任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据任务模块任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectModuleRuntime.test(#projectmodule_id,'READ')")
	@ApiOperation(value = "根据任务模块任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据任务模块任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProjectModuleTask(@PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务建立任务日志", tags = {"任务日志" },  notes = "根据产品计划任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'CREATE')")
    @ApiOperation(value = "根据产品计划任务批量建立任务日志", tags = {"任务日志" },  notes = "根据产品计划任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务更新任务日志", tags = {"任务日志" },  notes = "根据产品计划任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'UPDATE')")
    @ApiOperation(value = "根据产品计划任务批量更新任务日志", tags = {"任务日志" },  notes = "根据产品计划任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划任务删除任务日志", tags = {"任务日志" },  notes = "根据产品计划任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'DELETE')")
    @ApiOperation(value = "根据产品计划任务批量删除任务日志", tags = {"任务日志" },  notes = "根据产品计划任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductPlanTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
    @ApiOperation(value = "根据产品计划任务获取任务日志", tags = {"任务日志" },  notes = "根据产品计划任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品计划任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据产品计划任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品计划任务检查任务日志", tags = {"任务日志" },  notes = "根据产品计划任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据产品计划任务保存任务日志", tags = {"任务日志" },  notes = "根据产品计划任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品计划任务批量保存任务日志", tags = {"任务日志" },  notes = "根据产品计划任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务获取数据集", tags = {"任务日志" } ,notes = "根据产品计划任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务查询数据集", tags = {"任务日志" } ,notes = "根据产品计划任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品计划任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductPlanRuntime.test(#productplan_id,'READ')")
	@ApiOperation(value = "根据产品计划任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品计划任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProductPlanTask(@PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务建立任务日志", tags = {"任务日志" },  notes = "根据需求任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'CREATE')")
    @ApiOperation(value = "根据需求任务批量建立任务日志", tags = {"任务日志" },  notes = "根据需求任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务更新任务日志", tags = {"任务日志" },  notes = "根据需求任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'UPDATE')")
    @ApiOperation(value = "根据需求任务批量更新任务日志", tags = {"任务日志" },  notes = "根据需求任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求任务删除任务日志", tags = {"任务日志" },  notes = "根据需求任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'DELETE')")
    @ApiOperation(value = "根据需求任务批量删除任务日志", tags = {"任务日志" },  notes = "根据需求任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByStoryTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
    @ApiOperation(value = "根据需求任务获取任务日志", tags = {"任务日志" },  notes = "根据需求任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据需求任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据需求任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据需求任务检查任务日志", tags = {"任务日志" },  notes = "根据需求任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据需求任务保存任务日志", tags = {"任务日志" },  notes = "根据需求任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据需求任务批量保存任务日志", tags = {"任务日志" },  notes = "根据需求任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/stories/{story_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务获取数据集", tags = {"任务日志" } ,notes = "根据需求任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务查询数据集", tags = {"任务日志" } ,notes = "根据需求任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据需求任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@StoryRuntime.test(#story_id,'READ')")
	@ApiOperation(value = "根据需求任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据需求任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/stories/{story_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByStoryTask(@PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务建立任务日志", tags = {"任务日志" },  notes = "根据项目任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务批量建立任务日志", tags = {"任务日志" },  notes = "根据项目任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务更新任务日志", tags = {"任务日志" },  notes = "根据项目任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务批量更新任务日志", tags = {"任务日志" },  notes = "根据项目任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务删除任务日志", tags = {"任务日志" },  notes = "根据项目任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务批量删除任务日志", tags = {"任务日志" },  notes = "根据项目任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务获取任务日志", tags = {"任务日志" },  notes = "根据项目任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据项目任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目任务检查任务日志", tags = {"任务日志" },  notes = "根据项目任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据项目任务保存任务日志", tags = {"任务日志" },  notes = "根据项目任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务批量保存任务日志", tags = {"任务日志" },  notes = "根据项目任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取数据集", tags = {"任务日志" } ,notes = "根据项目任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询数据集", tags = {"任务日志" } ,notes = "根据项目任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据项目任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据项目任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务建立任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品计划任务批量建立任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务更新任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品产品计划任务批量更新任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划任务删除任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品产品计划任务批量删除任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlanTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品产品计划任务获取任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品产品计划任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据产品产品计划任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品产品计划任务检查任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据产品产品计划任务保存任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品产品计划任务批量保存任务日志", tags = {"任务日志" },  notes = "根据产品产品计划任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务获取数据集", tags = {"任务日志" } ,notes = "根据产品产品计划任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务查询数据集", tags = {"任务日志" } ,notes = "根据产品产品计划任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品产品计划任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品产品计划任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品产品计划任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProductProductPlanTask(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务建立任务日志", tags = {"任务日志" },  notes = "根据产品需求任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品需求任务批量建立任务日志", tags = {"任务日志" },  notes = "根据产品需求任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务更新任务日志", tags = {"任务日志" },  notes = "根据产品需求任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品需求任务批量更新任务日志", tags = {"任务日志" },  notes = "根据产品需求任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求任务删除任务日志", tags = {"任务日志" },  notes = "根据产品需求任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品需求任务批量删除任务日志", tags = {"任务日志" },  notes = "根据产品需求任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductStoryTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品需求任务获取任务日志", tags = {"任务日志" },  notes = "根据产品需求任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品需求任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据产品需求任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品需求任务检查任务日志", tags = {"任务日志" },  notes = "根据产品需求任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据产品需求任务保存任务日志", tags = {"任务日志" },  notes = "根据产品需求任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品需求任务批量保存任务日志", tags = {"任务日志" },  notes = "根据产品需求任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务获取数据集", tags = {"任务日志" } ,notes = "根据产品需求任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务查询数据集", tags = {"任务日志" } ,notes = "根据产品需求任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品需求任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品需求任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据产品需求任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProductStoryTask(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务建立任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions")
    public ResponseEntity<IBZTaskActionDTO> createByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
		ibztaskactionService.create(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目任务模块任务批量建立任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务批量建立任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务更新任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> updateByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        domain.setId(ibztaskaction_id);
		ibztaskactionService.update(domain);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目任务模块任务批量更新任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务批量更新任务日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
            domain.setObjectid(task_id);
        }
        ibztaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块任务删除任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<Boolean> removeByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.remove(ibztaskaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目任务模块任务批量删除任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务批量删除任务日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProjectModuleTask(@RequestBody List<Long> ids) {
        ibztaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目任务模块任务获取任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务获取任务日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/{ibztaskaction_id}")
    public ResponseEntity<IBZTaskActionDTO> getByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @PathVariable("ibztaskaction_id") Long ibztaskaction_id) {
        IBZTaskAction domain = ibztaskactionService.get(ibztaskaction_id);
        IBZTaskActionDTO dto = ibztaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目任务模块任务获取任务日志草稿", tags = {"任务日志" },  notes = "根据项目任务模块任务获取任务日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/getdraft")
    public ResponseEntity<IBZTaskActionDTO> getDraftByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, IBZTaskActionDTO dto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(dto);
        domain.setObjectid(task_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(ibztaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目任务模块任务检查任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务检查任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskactionService.checkKey(ibztaskactionMapping.toDomain(ibztaskactiondto)));
    }

    @ApiOperation(value = "根据项目任务模块任务保存任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/save")
    public ResponseEntity<IBZTaskActionDTO> saveByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionDTO ibztaskactiondto) {
        IBZTaskAction domain = ibztaskactionMapping.toDomain(ibztaskactiondto);
        domain.setObjectid(task_id);
        ibztaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目任务模块任务批量保存任务日志", tags = {"任务日志" },  notes = "根据项目任务模块任务批量保存任务日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody List<IBZTaskActionDTO> ibztaskactiondtos) {
        List<IBZTaskAction> domainlist=ibztaskactionMapping.toDomain(ibztaskactiondtos);
        for(IBZTaskAction domain:domainlist){
             domain.setObjectid(task_id);
        }
        ibztaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务获取数据集", tags = {"任务日志" } ,notes = "根据项目任务模块任务获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/fetchdefault")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务查询数据集", tags = {"任务日志" } ,notes = "根据项目任务模块任务查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/searchdefault")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionDefaultByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务获取动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据项目任务模块任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/fetchtype")
	public ResponseEntity<List<IBZTaskActionDTO>> fetchIBZTaskActionTypeByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id,@RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
        List<IBZTaskActionDTO> list = ibztaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目任务模块任务查询动态(根据类型过滤)", tags = {"任务日志" } ,notes = "根据项目任务模块任务查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectmodules/{projectmodule_id}/tasks/{task_id}/ibztaskactions/searchtype")
	public ResponseEntity<Page<IBZTaskActionDTO>> searchIBZTaskActionTypeByProjectProjectModuleTask(@PathVariable("project_id") Long project_id, @PathVariable("projectmodule_id") Long projectmodule_id, @PathVariable("task_id") Long task_id, @RequestBody IBZTaskActionSearchContext context) {
        context.setN_objectid_eq(task_id);
        Page<IBZTaskAction> domains = ibztaskactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

