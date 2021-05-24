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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuiteAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestSuiteActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuiteActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestSuiteActionRuntime;

@Slf4j
@Api(tags = {"套件日志" })
@RestController("WebApi-ibztestsuiteaction")
@RequestMapping("")
public class IBZTestSuiteActionResource {

    @Autowired
    public IIBZTestSuiteActionService ibztestsuiteactionService;

    @Autowired
    public IBZTestSuiteActionRuntime ibztestsuiteactionRuntime;

    @Autowired
    @Lazy
    public IBZTestSuiteActionMapping ibztestsuiteactionMapping;

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建套件日志", tags = {"套件日志" },  notes = "新建套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions")
    @Transactional
    public ResponseEntity<IBZTestSuiteActionDTO> create(@Validated @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
		ibztestsuiteactionService.create(domain);
        if(!ibztestsuiteactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建套件日志", tags = {"套件日志" },  notes = "批量新建套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        ibztestsuiteactionService.createBatch(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'UPDATE')")
    @ApiOperation(value = "更新套件日志", tags = {"套件日志" },  notes = "更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    @Transactional
    public ResponseEntity<IBZTestSuiteActionDTO> update(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
		IBZTestSuiteAction domain  = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setId(ibztestsuiteaction_id);
		ibztestsuiteactionService.update(domain );
        if(!ibztestsuiteactionRuntime.test(ibztestsuiteaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(ibztestsuiteaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新套件日志", tags = {"套件日志" },  notes = "批量更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        ibztestsuiteactionService.updateBatch(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'DELETE')")
    @ApiOperation(value = "删除套件日志", tags = {"套件日志" },  notes = "删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.remove(ibztestsuiteaction_id));
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除套件日志", tags = {"套件日志" },  notes = "批量删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztestsuiteactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.test(#ibztestsuiteaction_id,'READ')")
    @ApiOperation(value = "获取套件日志", tags = {"套件日志" },  notes = "获取套件日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> get(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.get(ibztestsuiteaction_id);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(ibztestsuiteaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取套件日志草稿", tags = {"套件日志" },  notes = "获取套件日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuiteactions/getdraft")
    public ResponseEntity<IBZTestSuiteActionDTO> getDraft(IBZTestSuiteActionDTO dto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(ibztestsuiteactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查套件日志", tags = {"套件日志" },  notes = "检查套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.checkKey(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto)));
    }

    @ApiOperation(value = "保存套件日志", tags = {"套件日志" },  notes = "保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/save")
    public ResponseEntity<IBZTestSuiteActionDTO> save(@RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        ibztestsuiteactionService.save(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuiteactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存套件日志", tags = {"套件日志" },  notes = "批量保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        ibztestsuiteactionService.saveBatch(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"套件日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/fetchdefault")
	public ResponseEntity<List<IBZTestSuiteActionDTO>> fetchdefault(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
        List<IBZTestSuiteActionDTO> list = ibztestsuiteactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuiteActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"套件日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuiteactions/searchdefault")
	public ResponseEntity<Page<IBZTestSuiteActionDTO>> searchDefault(@RequestBody IBZTestSuiteActionSearchContext context) {
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuiteactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestsuiteactions/{ibztestsuiteaction_id}/{action}")
    public ResponseEntity<IBZTestSuiteActionDTO> dynamicCall(@PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id , @PathVariable("action") String action , @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.dynamicCall(ibztestsuiteaction_id, action, ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto));
        ibztestsuiteactiondto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactiondto);
    }
    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件建立套件日志", tags = {"套件日志" },  notes = "根据测试套件建立套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions")
    public ResponseEntity<IBZTestSuiteActionDTO> createByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
		ibztestsuiteactionService.create(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'CREATE')")
    @ApiOperation(value = "根据测试套件批量建立套件日志", tags = {"套件日志" },  notes = "根据测试套件批量建立套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> createBatchByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
            domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件更新套件日志", tags = {"套件日志" },  notes = "根据测试套件更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> updateByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
        domain.setId(ibztestsuiteaction_id);
		ibztestsuiteactionService.update(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'UPDATE')")
    @ApiOperation(value = "根据测试套件批量更新套件日志", tags = {"套件日志" },  notes = "根据测试套件批量更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> updateBatchByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
            domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件删除套件日志", tags = {"套件日志" },  notes = "根据测试套件删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<Boolean> removeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.remove(ibztestsuiteaction_id));
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'DELETE')")
    @ApiOperation(value = "根据测试套件批量删除套件日志", tags = {"套件日志" },  notes = "根据测试套件批量删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> removeBatchByTestSuite(@RequestBody List<Long> ids) {
        ibztestsuiteactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
    @ApiOperation(value = "根据测试套件获取套件日志", tags = {"套件日志" },  notes = "根据测试套件获取套件日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> getByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.get(ibztestsuiteaction_id);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试套件获取套件日志草稿", tags = {"套件日志" },  notes = "根据测试套件获取套件日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/getdraft")
    public ResponseEntity<IBZTestSuiteActionDTO> getDraftByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, IBZTestSuiteActionDTO dto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(ibztestsuiteactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试套件检查套件日志", tags = {"套件日志" },  notes = "根据测试套件检查套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.checkKey(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto)));
    }

    @ApiOperation(value = "根据测试套件保存套件日志", tags = {"套件日志" },  notes = "根据测试套件保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/save")
    public ResponseEntity<IBZTestSuiteActionDTO> saveByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
        ibztestsuiteactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试套件批量保存套件日志", tags = {"套件日志" },  notes = "根据测试套件批量保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testsuites/{testsuite_id}/ibztestsuiteactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
             domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件获取数据集", tags = {"套件日志" } ,notes = "根据测试套件获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/ibztestsuiteactions/fetchdefault")
	public ResponseEntity<List<IBZTestSuiteActionDTO>> fetchIBZTestSuiteActionDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody IBZTestSuiteActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
        List<IBZTestSuiteActionDTO> list = ibztestsuiteactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestSuiteRuntime.test(#testsuite_id,'READ')")
	@ApiOperation(value = "根据测试套件查询数据集", tags = {"套件日志" } ,notes = "根据测试套件查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/ibztestsuiteactions/searchdefault")
	public ResponseEntity<Page<IBZTestSuiteActionDTO>> searchIBZTestSuiteActionDefaultByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuiteactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件建立套件日志", tags = {"套件日志" },  notes = "根据产品测试套件建立套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions")
    public ResponseEntity<IBZTestSuiteActionDTO> createByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
		ibztestsuiteactionService.create(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试套件批量建立套件日志", tags = {"套件日志" },  notes = "根据产品测试套件批量建立套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> createBatchByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
            domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件更新套件日志", tags = {"套件日志" },  notes = "根据产品测试套件更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> updateByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
        domain.setId(ibztestsuiteaction_id);
		ibztestsuiteactionService.update(domain);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试套件批量更新套件日志", tags = {"套件日志" },  notes = "根据产品测试套件批量更新套件日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
            domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件删除套件日志", tags = {"套件日志" },  notes = "根据产品测试套件删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<Boolean> removeByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.remove(ibztestsuiteaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试套件批量删除套件日志", tags = {"套件日志" },  notes = "根据产品测试套件批量删除套件日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestSuite(@RequestBody List<Long> ids) {
        ibztestsuiteactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试套件获取套件日志", tags = {"套件日志" },  notes = "根据产品测试套件获取套件日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/{ibztestsuiteaction_id}")
    public ResponseEntity<IBZTestSuiteActionDTO> getByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @PathVariable("ibztestsuiteaction_id") Long ibztestsuiteaction_id) {
        IBZTestSuiteAction domain = ibztestsuiteactionService.get(ibztestsuiteaction_id);
        IBZTestSuiteActionDTO dto = ibztestsuiteactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试套件获取套件日志草稿", tags = {"套件日志" },  notes = "根据产品测试套件获取套件日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/getdraft")
    public ResponseEntity<IBZTestSuiteActionDTO> getDraftByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, IBZTestSuiteActionDTO dto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(dto);
        domain.setObjectid(testsuite_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(ibztestsuiteactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试套件检查套件日志", tags = {"套件日志" },  notes = "根据产品测试套件检查套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionService.checkKey(ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto)));
    }

    @ApiOperation(value = "根据产品测试套件保存套件日志", tags = {"套件日志" },  notes = "根据产品测试套件保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/save")
    public ResponseEntity<IBZTestSuiteActionDTO> saveByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionDTO ibztestsuiteactiondto) {
        IBZTestSuiteAction domain = ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondto);
        domain.setObjectid(testsuite_id);
        ibztestsuiteactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuiteactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试套件批量保存套件日志", tags = {"套件日志" },  notes = "根据产品测试套件批量保存套件日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody List<IBZTestSuiteActionDTO> ibztestsuiteactiondtos) {
        List<IBZTestSuiteAction> domainlist=ibztestsuiteactionMapping.toDomain(ibztestsuiteactiondtos);
        for(IBZTestSuiteAction domain:domainlist){
             domain.setObjectid(testsuite_id);
        }
        ibztestsuiteactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件获取数据集", tags = {"套件日志" } ,notes = "根据产品测试套件获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/fetchdefault")
	public ResponseEntity<List<IBZTestSuiteActionDTO>> fetchIBZTestSuiteActionDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id,@RequestBody IBZTestSuiteActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
        List<IBZTestSuiteActionDTO> list = ibztestsuiteactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试套件查询数据集", tags = {"套件日志" } ,notes = "根据产品测试套件查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testsuites/{testsuite_id}/ibztestsuiteactions/searchdefault")
	public ResponseEntity<Page<IBZTestSuiteActionDTO>> searchIBZTestSuiteActionDefaultByProductTestSuite(@PathVariable("product_id") Long product_id, @PathVariable("testsuite_id") Long testsuite_id, @RequestBody IBZTestSuiteActionSearchContext context) {
        context.setN_objectid_eq(testsuite_id);
        Page<IBZTestSuiteAction> domains = ibztestsuiteactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuiteactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

