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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestReportActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestReportActionRuntime;

@Slf4j
@Api(tags = {"报告日志" })
@RestController("WebApi-ibztestreportaction")
@RequestMapping("")
public class IBZTestReportActionResource {

    @Autowired
    public IIBZTestReportActionService ibztestreportactionService;

    @Autowired
    public IBZTestReportActionRuntime ibztestreportactionRuntime;

    @Autowired
    @Lazy
    public IBZTestReportActionMapping ibztestreportactionMapping;

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建报告日志", tags = {"报告日志" },  notes = "新建报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions")
    @Transactional
    public ResponseEntity<IBZTestReportActionDTO> create(@Validated @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
		ibztestreportactionService.create(domain);
        if(!ibztestreportactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建报告日志", tags = {"报告日志" },  notes = "批量新建报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.createBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'UPDATE')")
    @ApiOperation(value = "更新报告日志", tags = {"报告日志" },  notes = "更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestreportactions/{ibztestreportaction_id}")
    @Transactional
    public ResponseEntity<IBZTestReportActionDTO> update(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
		IBZTestReportAction domain  = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setId(ibztestreportaction_id);
		ibztestreportactionService.update(domain );
        if(!ibztestreportactionRuntime.test(ibztestreportaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(ibztestreportaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新报告日志", tags = {"报告日志" },  notes = "批量更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.updateBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'DELETE')")
    @ApiOperation(value = "删除报告日志", tags = {"报告日志" },  notes = "删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除报告日志", tags = {"报告日志" },  notes = "批量删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreportactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibztestreportactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.test(#ibztestreportaction_id,'READ')")
    @ApiOperation(value = "获取报告日志", tags = {"报告日志" },  notes = "获取报告日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> get(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
        IBZTestReportAction domain = ibztestreportactionService.get(ibztestreportaction_id);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(ibztestreportaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取报告日志草稿", tags = {"报告日志" },  notes = "获取报告日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraft(IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查报告日志", tags = {"报告日志" },  notes = "检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
    }

    @ApiOperation(value = "保存报告日志", tags = {"报告日志" },  notes = "保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/save")
    public ResponseEntity<IBZTestReportActionDTO> save(@RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        ibztestreportactionService.save(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreportactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存报告日志", tags = {"报告日志" },  notes = "批量保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        ibztestreportactionService.saveBatch(ibztestreportactionMapping.toDomain(ibztestreportactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"报告日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchdefault(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"报告日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/searchdefault")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchDefault(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"报告日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/fetchtype")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchtype(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestReportActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"报告日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreportactions/searchtype")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchType(@RequestBody IBZTestReportActionSearchContext context) {
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestreportactions/{ibztestreportaction_id}/{action}")
    public ResponseEntity<IBZTestReportActionDTO> dynamicCall(@PathVariable("ibztestreportaction_id") Long ibztestreportaction_id , @PathVariable("action") String action , @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionService.dynamicCall(ibztestreportaction_id, action, ibztestreportactionMapping.toDomain(ibztestreportactiondto));
        ibztestreportactiondto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactiondto);
    }
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告建立报告日志", tags = {"报告日志" },  notes = "根据测试报告建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/ibztestreportactions")
    public ResponseEntity<IBZTestReportActionDTO> createByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
		ibztestreportactionService.create(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'CREATE')")
    @ApiOperation(value = "根据测试报告批量建立报告日志", tags = {"报告日志" },  notes = "根据测试报告批量建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> createBatchByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'UPDATE')")
    @ApiOperation(value = "根据测试报告更新报告日志", tags = {"报告日志" },  notes = "根据测试报告更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> updateByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        domain.setId(ibztestreportaction_id);
		ibztestreportactionService.update(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'UPDATE')")
    @ApiOperation(value = "根据测试报告批量更新报告日志", tags = {"报告日志" },  notes = "根据测试报告批量更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> updateBatchByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'DELETE')")
    @ApiOperation(value = "根据测试报告删除报告日志", tags = {"报告日志" },  notes = "根据测试报告删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> removeByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'DELETE')")
    @ApiOperation(value = "根据测试报告批量删除报告日志", tags = {"报告日志" },  notes = "根据测试报告批量删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> removeBatchByTestReport(@RequestBody List<Long> ids) {
        ibztestreportactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
    @ApiOperation(value = "根据测试报告获取报告日志", tags = {"报告日志" },  notes = "根据测试报告获取报告日志")
	@RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> getByTestReport(@PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
        IBZTestReportAction domain = ibztestreportactionService.get(ibztestreportaction_id);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据测试报告获取报告日志草稿", tags = {"报告日志" },  notes = "根据测试报告获取报告日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/testreports/{testreport_id}/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraftByTestReport(@PathVariable("testreport_id") Long testreport_id, IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据测试报告检查报告日志", tags = {"报告日志" },  notes = "根据测试报告检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
    }

    @ApiOperation(value = "根据测试报告保存报告日志", tags = {"报告日志" },  notes = "根据测试报告保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/ibztestreportactions/save")
    public ResponseEntity<IBZTestReportActionDTO> saveByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        ibztestreportactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据测试报告批量保存报告日志", tags = {"报告日志" },  notes = "根据测试报告批量保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/testreports/{testreport_id}/ibztestreportactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
             domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取数据集", tags = {"报告日志" } ,notes = "根据测试报告获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionDefaultByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告查询数据集", tags = {"报告日志" } ,notes = "根据测试报告查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/ibztestreportactions/searchdefault")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionDefaultByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告获取动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/ibztestreportactions/fetchtype")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionTypeByTestReport(@PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@TestReportRuntime.test(#testreport_id,'READ')")
	@ApiOperation(value = "根据测试报告查询动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据测试报告查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testreports/{testreport_id}/ibztestreportactions/searchtype")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionTypeByTestReport(@PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告建立报告日志", tags = {"报告日志" },  notes = "根据产品测试报告建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions")
    public ResponseEntity<IBZTestReportActionDTO> createByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
		ibztestreportactionService.create(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试报告批量建立报告日志", tags = {"报告日志" },  notes = "根据产品测试报告批量建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> createBatchByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试报告更新报告日志", tags = {"报告日志" },  notes = "根据产品测试报告更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> updateByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        domain.setId(ibztestreportaction_id);
		ibztestreportactionService.update(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试报告批量更新报告日志", tags = {"报告日志" },  notes = "根据产品测试报告批量更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试报告删除报告日志", tags = {"报告日志" },  notes = "根据产品测试报告删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> removeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试报告批量删除报告日志", tags = {"报告日志" },  notes = "根据产品测试报告批量删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestReport(@RequestBody List<Long> ids) {
        ibztestreportactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试报告获取报告日志", tags = {"报告日志" },  notes = "根据产品测试报告获取报告日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> getByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
        IBZTestReportAction domain = ibztestreportactionService.get(ibztestreportaction_id);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品测试报告获取报告日志草稿", tags = {"报告日志" },  notes = "根据产品测试报告获取报告日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraftByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品测试报告检查报告日志", tags = {"报告日志" },  notes = "根据产品测试报告检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
    }

    @ApiOperation(value = "根据产品测试报告保存报告日志", tags = {"报告日志" },  notes = "根据产品测试报告保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/save")
    public ResponseEntity<IBZTestReportActionDTO> saveByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        ibztestreportactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品测试报告批量保存报告日志", tags = {"报告日志" },  notes = "根据产品测试报告批量保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
             domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取数据集", tags = {"报告日志" } ,notes = "根据产品测试报告获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionDefaultByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告查询数据集", tags = {"报告日志" } ,notes = "根据产品测试报告查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/searchdefault")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionDefaultByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告获取动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据产品测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/fetchtype")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试报告查询动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据产品测试报告查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/testreports/{testreport_id}/ibztestreportactions/searchtype")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告建立报告日志", tags = {"报告日志" },  notes = "根据项目测试报告建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions")
    public ResponseEntity<IBZTestReportActionDTO> createByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
		ibztestreportactionService.create(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目测试报告批量建立报告日志", tags = {"报告日志" },  notes = "根据项目测试报告批量建立报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试报告更新报告日志", tags = {"报告日志" },  notes = "根据项目测试报告更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> updateByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        domain.setId(ibztestreportaction_id);
		ibztestreportactionService.update(domain);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目测试报告批量更新报告日志", tags = {"报告日志" },  notes = "根据项目测试报告批量更新报告日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
            domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试报告删除报告日志", tags = {"报告日志" },  notes = "根据项目测试报告删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<Boolean> removeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.remove(ibztestreportaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目测试报告批量删除报告日志", tags = {"报告日志" },  notes = "根据项目测试报告批量删除报告日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestReport(@RequestBody List<Long> ids) {
        ibztestreportactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目测试报告获取报告日志", tags = {"报告日志" },  notes = "根据项目测试报告获取报告日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/{ibztestreportaction_id}")
    public ResponseEntity<IBZTestReportActionDTO> getByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("ibztestreportaction_id") Long ibztestreportaction_id) {
        IBZTestReportAction domain = ibztestreportactionService.get(ibztestreportaction_id);
        IBZTestReportActionDTO dto = ibztestreportactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目测试报告获取报告日志草稿", tags = {"报告日志" },  notes = "根据项目测试报告获取报告日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/getdraft")
    public ResponseEntity<IBZTestReportActionDTO> getDraftByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(dto);
        domain.setObjectid(testreport_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(ibztestreportactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目测试报告检查报告日志", tags = {"报告日志" },  notes = "根据项目测试报告检查报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionService.checkKey(ibztestreportactionMapping.toDomain(ibztestreportactiondto)));
    }

    @ApiOperation(value = "根据项目测试报告保存报告日志", tags = {"报告日志" },  notes = "根据项目测试报告保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/save")
    public ResponseEntity<IBZTestReportActionDTO> saveByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionDTO ibztestreportactiondto) {
        IBZTestReportAction domain = ibztestreportactionMapping.toDomain(ibztestreportactiondto);
        domain.setObjectid(testreport_id);
        ibztestreportactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreportactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目测试报告批量保存报告日志", tags = {"报告日志" },  notes = "根据项目测试报告批量保存报告日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody List<IBZTestReportActionDTO> ibztestreportactiondtos) {
        List<IBZTestReportAction> domainlist=ibztestreportactionMapping.toDomain(ibztestreportactiondtos);
        for(IBZTestReportAction domain:domainlist){
             domain.setObjectid(testreport_id);
        }
        ibztestreportactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取数据集", tags = {"报告日志" } ,notes = "根据项目测试报告获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/fetchdefault")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionDefaultByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告查询数据集", tags = {"报告日志" } ,notes = "根据项目测试报告查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/searchdefault")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionDefaultByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告获取动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据项目测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/fetchtype")
	public ResponseEntity<List<IBZTestReportActionDTO>> fetchIBZTestReportActionTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
        List<IBZTestReportActionDTO> list = ibztestreportactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目测试报告查询动态(根据类型过滤)", tags = {"报告日志" } ,notes = "根据项目测试报告查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/ibztestreportactions/searchtype")
	public ResponseEntity<Page<IBZTestReportActionDTO>> searchIBZTestReportActionTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @RequestBody IBZTestReportActionSearchContext context) {
        context.setN_objectid_eq(testreport_id);
        Page<IBZTestReportAction> domains = ibztestreportactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreportactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

