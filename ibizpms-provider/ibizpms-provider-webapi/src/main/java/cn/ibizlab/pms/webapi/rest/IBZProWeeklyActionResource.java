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
import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProWeeklyActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProWeeklyActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProWeeklyActionRuntime;

@Slf4j
@Api(tags = {"周报日志" })
@RestController("WebApi-ibzproweeklyaction")
@RequestMapping("")
public class IBZProWeeklyActionResource {

    @Autowired
    public IIBZProWeeklyActionService ibzproweeklyactionService;

    @Autowired
    public IBZProWeeklyActionRuntime ibzproweeklyactionRuntime;

    @Autowired
    @Lazy
    public IBZProWeeklyActionMapping ibzproweeklyactionMapping;

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建周报日志", tags = {"周报日志" },  notes = "新建周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions")
    @Transactional
    public ResponseEntity<IBZProWeeklyActionDTO> create(@Validated @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
		ibzproweeklyactionService.create(domain);
        if(!ibzproweeklyactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建周报日志", tags = {"周报日志" },  notes = "批量新建周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        ibzproweeklyactionService.createBatch(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id,'UPDATE')")
    @ApiOperation(value = "更新周报日志", tags = {"周报日志" },  notes = "更新周报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    @Transactional
    public ResponseEntity<IBZProWeeklyActionDTO> update(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
		IBZProWeeklyAction domain  = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setId(ibzproweeklyaction_id);
		ibzproweeklyactionService.update(domain );
        if(!ibzproweeklyactionRuntime.test(ibzproweeklyaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(ibzproweeklyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新周报日志", tags = {"周报日志" },  notes = "批量更新周报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        ibzproweeklyactionService.updateBatch(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id,'DELETE')")
    @ApiOperation(value = "删除周报日志", tags = {"周报日志" },  notes = "删除周报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.remove(ibzproweeklyaction_id));
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除周报日志", tags = {"周报日志" },  notes = "批量删除周报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproweeklyactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.test(#ibzproweeklyaction_id,'READ')")
    @ApiOperation(value = "获取周报日志", tags = {"周报日志" },  notes = "获取周报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<IBZProWeeklyActionDTO> get(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
        IBZProWeeklyAction domain = ibzproweeklyactionService.get(ibzproweeklyaction_id);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(ibzproweeklyaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取周报日志草稿", tags = {"周报日志" },  notes = "获取周报日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyactions/getdraft")
    public ResponseEntity<IBZProWeeklyActionDTO> getDraft(IBZProWeeklyActionDTO dto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionMapping.toDto(ibzproweeklyactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查周报日志", tags = {"周报日志" },  notes = "检查周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.checkKey(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto)));
    }

    @ApiOperation(value = "保存周报日志", tags = {"周报日志" },  notes = "保存周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/save")
    public ResponseEntity<IBZProWeeklyActionDTO> save(@RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        ibzproweeklyactionService.save(domain);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存周报日志", tags = {"周报日志" },  notes = "批量保存周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        ibzproweeklyactionService.saveBatch(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"周报日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/fetchdefault")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchdefault(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchDefault(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"周报日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/searchdefault")
	public ResponseEntity<Page<IBZProWeeklyActionDTO>> searchDefault(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproweeklyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"周报日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/fetchtype")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchtype(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchType(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProWeeklyActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"周报日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyactions/searchtype")
	public ResponseEntity<Page<IBZProWeeklyActionDTO>> searchType(@RequestBody IBZProWeeklyActionSearchContext context) {
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproweeklyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyactions/{ibzproweeklyaction_id}/{action}")
    public ResponseEntity<IBZProWeeklyActionDTO> dynamicCall(@PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id , @PathVariable("action") String action , @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionService.dynamicCall(ibzproweeklyaction_id, action, ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto));
        ibzproweeklyactiondto = ibzproweeklyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactiondto);
    }
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报建立周报日志", tags = {"周报日志" },  notes = "根据周报建立周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions")
    public ResponseEntity<IBZProWeeklyActionDTO> createByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setObjectid(ibzweekly_id);
		ibzproweeklyactionService.create(domain);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'CREATE')")
    @ApiOperation(value = "根据周报批量建立周报日志", tags = {"周报日志" },  notes = "根据周报批量建立周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> createBatchByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        List<IBZProWeeklyAction> domainlist=ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos);
        for(IBZProWeeklyAction domain:domainlist){
            domain.setObjectid(ibzweekly_id);
        }
        ibzproweeklyactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'UPDATE')")
    @ApiOperation(value = "根据周报更新周报日志", tags = {"周报日志" },  notes = "根据周报更新周报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<IBZProWeeklyActionDTO> updateByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setObjectid(ibzweekly_id);
        domain.setId(ibzproweeklyaction_id);
		ibzproweeklyactionService.update(domain);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'UPDATE')")
    @ApiOperation(value = "根据周报批量更新周报日志", tags = {"周报日志" },  notes = "根据周报批量更新周报日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> updateBatchByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        List<IBZProWeeklyAction> domainlist=ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos);
        for(IBZProWeeklyAction domain:domainlist){
            domain.setObjectid(ibzweekly_id);
        }
        ibzproweeklyactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'DELETE')")
    @ApiOperation(value = "根据周报删除周报日志", tags = {"周报日志" },  notes = "根据周报删除周报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<Boolean> removeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.remove(ibzproweeklyaction_id));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'DELETE')")
    @ApiOperation(value = "根据周报批量删除周报日志", tags = {"周报日志" },  notes = "根据周报批量删除周报日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/batch")
    public ResponseEntity<Boolean> removeBatchByIbzWeekly(@RequestBody List<Long> ids) {
        ibzproweeklyactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
    @ApiOperation(value = "根据周报获取周报日志", tags = {"周报日志" },  notes = "根据周报获取周报日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/{ibzproweeklyaction_id}")
    public ResponseEntity<IBZProWeeklyActionDTO> getByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("ibzproweeklyaction_id") Long ibzproweeklyaction_id) {
        IBZProWeeklyAction domain = ibzproweeklyactionService.get(ibzproweeklyaction_id);
        IBZProWeeklyActionDTO dto = ibzproweeklyactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据周报获取周报日志草稿", tags = {"周报日志" },  notes = "根据周报获取周报日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/getdraft")
    public ResponseEntity<IBZProWeeklyActionDTO> getDraftByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, IBZProWeeklyActionDTO dto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(dto);
        domain.setObjectid(ibzweekly_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionMapping.toDto(ibzproweeklyactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据周报检查周报日志", tags = {"周报日志" },  notes = "根据周报检查周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionService.checkKey(ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto)));
    }

    @ApiOperation(value = "根据周报保存周报日志", tags = {"周报日志" },  notes = "根据周报保存周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/save")
    public ResponseEntity<IBZProWeeklyActionDTO> saveByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IBZProWeeklyActionDTO ibzproweeklyactiondto) {
        IBZProWeeklyAction domain = ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondto);
        domain.setObjectid(ibzweekly_id);
        ibzproweeklyactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据周报批量保存周报日志", tags = {"周报日志" },  notes = "根据周报批量保存周报日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody List<IBZProWeeklyActionDTO> ibzproweeklyactiondtos) {
        List<IBZProWeeklyAction> domainlist=ibzproweeklyactionMapping.toDomain(ibzproweeklyactiondtos);
        for(IBZProWeeklyAction domain:domainlist){
             domain.setObjectid(ibzweekly_id);
        }
        ibzproweeklyactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取数据集", tags = {"周报日志" } ,notes = "根据周报获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/fetchdefault")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchIBZProWeeklyActionDefaultByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody IBZProWeeklyActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchDefault(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报查询数据集", tags = {"周报日志" } ,notes = "根据周报查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/searchdefault")
	public ResponseEntity<Page<IBZProWeeklyActionDTO>> searchIBZProWeeklyActionDefaultByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IBZProWeeklyActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproweeklyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报获取动态(根据类型过滤)", tags = {"周报日志" } ,notes = "根据周报获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/fetchtype")
	public ResponseEntity<List<IBZProWeeklyActionDTO>> fetchIBZProWeeklyActionTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody IBZProWeeklyActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchType(context) ;
        List<IBZProWeeklyActionDTO> list = ibzproweeklyactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id,'READ')")
	@ApiOperation(value = "根据周报查询动态(根据类型过滤)", tags = {"周报日志" } ,notes = "根据周报查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/{ibzweekly_id}/ibzproweeklyactions/searchtype")
	public ResponseEntity<Page<IBZProWeeklyActionDTO>> searchIBZProWeeklyActionTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IBZProWeeklyActionSearchContext context) {
        context.setN_objectid_eq(ibzweekly_id);
        Page<IBZProWeeklyAction> domains = ibzproweeklyactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproweeklyactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

