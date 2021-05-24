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
import cn.ibizlab.pms.core.ibiz.domain.IbzProTestTaskAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProTestTaskActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProTestTaskActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProTestTaskActionRuntime;

@Slf4j
@Api(tags = {"测试单日志" })
@RestController("WebApi-ibzprotesttaskaction")
@RequestMapping("")
public class IbzProTestTaskActionResource {

    @Autowired
    public IIbzProTestTaskActionService ibzprotesttaskactionService;

    @Autowired
    public IbzProTestTaskActionRuntime ibzprotesttaskactionRuntime;

    @Autowired
    @Lazy
    public IbzProTestTaskActionMapping ibzprotesttaskactionMapping;

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试单日志", tags = {"测试单日志" },  notes = "新建测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions")
    @Transactional
    public ResponseEntity<IbzProTestTaskActionDTO> create(@Validated @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
		ibzprotesttaskactionService.create(domain);
        if(!ibzprotesttaskactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建测试单日志", tags = {"测试单日志" },  notes = "批量新建测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.createBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'UPDATE')")
    @ApiOperation(value = "更新测试单日志", tags = {"测试单日志" },  notes = "更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    @Transactional
    public ResponseEntity<IbzProTestTaskActionDTO> update(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
		IbzProTestTaskAction domain  = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain );
        if(!ibzprotesttaskactionRuntime.test(ibzprotesttaskaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(ibzprotesttaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新测试单日志", tags = {"测试单日志" },  notes = "批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.updateBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'DELETE')")
    @ApiOperation(value = "删除测试单日志", tags = {"测试单日志" },  notes = "删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除测试单日志", tags = {"测试单日志" },  notes = "批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.test(#ibzprotesttaskaction_id,'READ')")
    @ApiOperation(value = "获取测试单日志", tags = {"测试单日志" },  notes = "获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> get(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(ibzprotesttaskaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试单日志草稿", tags = {"测试单日志" },  notes = "获取测试单日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraft(IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试单日志", tags = {"测试单日志" },  notes = "检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "保存测试单日志", tags = {"测试单日志" },  notes = "保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> save(@RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        ibzprotesttaskactionService.save(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存测试单日志", tags = {"测试单日志" },  notes = "批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        ibzprotesttaskactionService.saveBatch(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试单日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchdefault(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProTestTaskActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试单日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchDefault(@RequestBody IbzProTestTaskActionSearchContext context) {
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskactions/{ibzprotesttaskaction_id}/{action}")
    public ResponseEntity<IbzProTestTaskActionDTO> dynamicCall(@PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id , @PathVariable("action") String action , @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.dynamicCall(ibzprotesttaskaction_id, action, ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto));
        ibzprotesttaskactiondto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactiondto);
    }
    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'CREATE')")
    @ApiOperation(value = "根据测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'UPDATE')")
    @ApiOperation(value = "根据测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'DELETE')")
    @ApiOperation(value = "根据测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
    @ApiOperation(value = "根据测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByTestTask(@PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByTestTask(@PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestTaskRuntime.test(#testtask_id,'READ')")
	@ApiOperation(value = "根据测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByTestTask(@PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据产品测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据产品测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据产品测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据产品测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据产品测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本建立测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions")
    public ResponseEntity<IbzProTestTaskActionDTO> createByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
		ibzprotesttaskactionService.create(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试版本批量建立测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量建立测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本更新测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> updateByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        domain.setId(ibzprotesttaskaction_id);
		ibzprotesttaskactionService.update(domain);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试版本批量更新测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量更新测试单日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
            domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.remove(ibzprotesttaskaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试版本批量删除测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量删除测试单日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestTask(@RequestBody List<Long> ids) {
        ibzprotesttaskactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试版本获取测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本获取测试单日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/{ibzprotesttaskaction_id}")
    public ResponseEntity<IbzProTestTaskActionDTO> getByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("ibzprotesttaskaction_id") Long ibzprotesttaskaction_id) {
        IbzProTestTaskAction domain = ibzprotesttaskactionService.get(ibzprotesttaskaction_id);
        IbzProTestTaskActionDTO dto = ibzprotesttaskactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目测试版本获取测试单日志草稿", tags = {"测试单日志" },  notes = "根据项目测试版本获取测试单日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/getdraft")
    public ResponseEntity<IbzProTestTaskActionDTO> getDraftByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(dto);
        domain.setObjectid(testtask_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(ibzprotesttaskactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目测试版本检查测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本检查测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionService.checkKey(ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto)));
    }

    @ApiOperation(value = "根据项目测试版本保存测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/save")
    public ResponseEntity<IbzProTestTaskActionDTO> saveByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionDTO ibzprotesttaskactiondto) {
        IbzProTestTaskAction domain = ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondto);
        domain.setObjectid(testtask_id);
        ibzprotesttaskactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目测试版本批量保存测试单日志", tags = {"测试单日志" },  notes = "根据项目测试版本批量保存测试单日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody List<IbzProTestTaskActionDTO> ibzprotesttaskactiondtos) {
        List<IbzProTestTaskAction> domainlist=ibzprotesttaskactionMapping.toDomain(ibzprotesttaskactiondtos);
        for(IbzProTestTaskAction domain:domainlist){
             domain.setObjectid(testtask_id);
        }
        ibzprotesttaskactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本获取数据集", tags = {"测试单日志" } ,notes = "根据项目测试版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/fetchdefault")
	public ResponseEntity<List<IbzProTestTaskActionDTO>> fetchIbzProTestTaskActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
        List<IbzProTestTaskActionDTO> list = ibzprotesttaskactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试版本查询数据集", tags = {"测试单日志" } ,notes = "根据项目测试版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/ibzprotesttaskactions/searchdefault")
	public ResponseEntity<Page<IbzProTestTaskActionDTO>> searchIbzProTestTaskActionDefaultByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @RequestBody IbzProTestTaskActionSearchContext context) {
        context.setN_objectid_eq(testtask_id);
        Page<IbzProTestTaskAction> domains = ibzprotesttaskactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprotesttaskactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

