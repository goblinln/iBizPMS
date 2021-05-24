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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProductActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProProductActionRuntime;

@Slf4j
@Api(tags = {"产品日志" })
@RestController("WebApi-ibzproproductaction")
@RequestMapping("")
public class IBZProProductActionResource {

    @Autowired
    public IIBZProProductActionService ibzproproductactionService;

    @Autowired
    public IBZProProductActionRuntime ibzproproductactionRuntime;

    @Autowired
    @Lazy
    public IBZProProductActionMapping ibzproproductactionMapping;

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品日志", tags = {"产品日志" },  notes = "新建产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions")
    @Transactional
    public ResponseEntity<IBZProProductActionDTO> create(@Validated @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
		ibzproproductactionService.create(domain);
        if(!ibzproproductactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建产品日志", tags = {"产品日志" },  notes = "批量新建产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        ibzproproductactionService.createBatch(ibzproproductactionMapping.toDomain(ibzproproductactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'UPDATE')")
    @ApiOperation(value = "更新产品日志", tags = {"产品日志" },  notes = "更新产品日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductactions/{ibzproproductaction_id}")
    @Transactional
    public ResponseEntity<IBZProProductActionDTO> update(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
		IBZProProductAction domain  = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
		ibzproproductactionService.update(domain );
        if(!ibzproproductactionRuntime.test(ibzproproductaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新产品日志", tags = {"产品日志" },  notes = "批量更新产品日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        ibzproproductactionService.updateBatch(ibzproproductactionMapping.toDomain(ibzproproductactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'DELETE')")
    @ApiOperation(value = "删除产品日志", tags = {"产品日志" },  notes = "删除产品日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductactions/{ibzproproductaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionService.remove(ibzproproductaction_id));
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除产品日志", tags = {"产品日志" },  notes = "批量删除产品日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproproductactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'READ')")
    @ApiOperation(value = "获取产品日志", tags = {"产品日志" },  notes = "获取产品日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductactions/{ibzproproductaction_id}")
    public ResponseEntity<IBZProProductActionDTO> get(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id) {
        IBZProProductAction domain = ibzproproductactionService.get(ibzproproductaction_id);
        IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductactionRuntime.getOPPrivs(ibzproproductaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取产品日志草稿", tags = {"产品日志" },  notes = "获取产品日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductactions/getdraft")
    public ResponseEntity<IBZProProductActionDTO> getDraft(IBZProProductActionDTO dto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionMapping.toDto(ibzproproductactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查产品日志", tags = {"产品日志" },  notes = "检查产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionService.checkKey(ibzproproductactionMapping.toDomain(ibzproproductactiondto)));
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'MANAGE')")
    @ApiOperation(value = "添加备注", tags = {"产品日志" },  notes = "添加备注")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/comment")
    public ResponseEntity<IBZProProductActionDTO> comment(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.comment(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'CREATE')")
    @ApiOperation(value = "创建历史日志", tags = {"产品日志" },  notes = "创建历史日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/createhis")
    public ResponseEntity<IBZProProductActionDTO> createHis(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.createHis(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[创建历史日志]", tags = {"产品日志" },  notes = "批量处理[创建历史日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/createhisbatch")
    public ResponseEntity<Boolean> createHisBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZProProductActionRuntime.test(#ibzproproductaction_id,'MANAGE')")
    @ApiOperation(value = "编辑备注信息", tags = {"产品日志" },  notes = "编辑备注信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/editcomment")
    public ResponseEntity<IBZProProductActionDTO> editComment(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.editComment(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[编辑备注信息]", tags = {"产品日志" },  notes = "批量处理[编辑备注信息]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Pms企业专用", tags = {"产品日志" },  notes = "Pms企业专用")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/managepmsee")
    public ResponseEntity<IBZProProductActionDTO> managePmsEe(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.managePmsEe(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[Pms企业专用]", tags = {"产品日志" },  notes = "批量处理[Pms企业专用]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存产品日志", tags = {"产品日志" },  notes = "保存产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/save")
    public ResponseEntity<IBZProProductActionDTO> save(@RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        ibzproproductactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存产品日志", tags = {"产品日志" },  notes = "批量保存产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        ibzproproductactionService.saveBatch(ibzproproductactionMapping.toDomain(ibzproproductactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "已读", tags = {"产品日志" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/sendmarkdone")
    public ResponseEntity<IBZProProductActionDTO> sendMarkDone(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendMarkDone(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"产品日志" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待办", tags = {"产品日志" },  notes = "发送待办")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/sendtodo")
    public ResponseEntity<IBZProProductActionDTO> sendTodo(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendTodo(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[发送待办]", tags = {"产品日志" },  notes = "批量处理[发送待办]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "发送待阅", tags = {"产品日志" },  notes = "发送待阅")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/sendtoread")
    public ResponseEntity<IBZProProductActionDTO> sendToread(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendToread(domain);
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[发送待阅]", tags = {"产品日志" },  notes = "批量处理[发送待阅]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadBatch(@RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/fetchdefault")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchdefault(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchDefault(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/searchdefault")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchDefault(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"产品日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/fetchmobtype")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchmobtype(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchMobType(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"产品日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/searchmobtype")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchMobType(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品动态(产品相关所有)", tags = {"产品日志" } ,notes = "获取产品动态(产品相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/fetchproducttrends")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchproducttrends(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchProductTrends(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品动态(产品相关所有)", tags = {"产品日志" } ,notes = "查询产品动态(产品相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/searchproducttrends")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchProductTrends(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"产品日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/fetchtype")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchtype(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchType(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"产品日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductactions/searchtype")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchType(@RequestBody IBZProProductActionSearchContext context) {
        Page<IBZProProductAction> domains = ibzproproductactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproductactions/{ibzproproductaction_id}/{action}")
    public ResponseEntity<IBZProProductActionDTO> dynamicCall(@PathVariable("ibzproproductaction_id") Long ibzproproductaction_id , @PathVariable("action") String action , @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionService.dynamicCall(ibzproproductaction_id, action, ibzproproductactionMapping.toDomain(ibzproproductactiondto));
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立产品日志", tags = {"产品日志" },  notes = "根据产品建立产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions")
    public ResponseEntity<IBZProProductActionDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
		ibzproproductactionService.create(domain);
        IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量建立产品日志", tags = {"产品日志" },  notes = "根据产品批量建立产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domainlist=ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        for(IBZProProductAction domain:domainlist){
            domain.setObjectid(product_id);
        }
        ibzproproductactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新产品日志", tags = {"产品日志" },  notes = "根据产品更新产品日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}")
    public ResponseEntity<IBZProProductActionDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
		ibzproproductactionService.update(domain);
        IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品批量更新产品日志", tags = {"产品日志" },  notes = "根据产品批量更新产品日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/ibzproproductactions/batch")
    public ResponseEntity<Boolean> updateBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domainlist=ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        for(IBZProProductAction domain:domainlist){
            domain.setObjectid(product_id);
        }
        ibzproproductactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除产品日志", tags = {"产品日志" },  notes = "根据产品删除产品日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionService.remove(ibzproproductaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品日志", tags = {"产品日志" },  notes = "根据产品批量删除产品日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/ibzproproductactions/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        ibzproproductactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取产品日志", tags = {"产品日志" },  notes = "根据产品获取产品日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}")
    public ResponseEntity<IBZProProductActionDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id) {
        IBZProProductAction domain = ibzproproductactionService.get(ibzproproductaction_id);
        IBZProProductActionDTO dto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取产品日志草稿", tags = {"产品日志" },  notes = "根据产品获取产品日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/ibzproproductactions/getdraft")
    public ResponseEntity<IBZProProductActionDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, IBZProProductActionDTO dto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(dto);
        domain.setObjectid(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionMapping.toDto(ibzproproductactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品检查产品日志", tags = {"产品日志" },  notes = "根据产品检查产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionService.checkKey(ibzproproductactionMapping.toDomain(ibzproproductactiondto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/comment")
    public ResponseEntity<IBZProProductActionDTO> commentByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.comment(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/createhis")
    public ResponseEntity<IBZProProductActionDTO> createHisByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.createHis(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/createhisbatch")
    public ResponseEntity<Boolean> createHisByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.createHisBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/editcomment")
    public ResponseEntity<IBZProProductActionDTO> editCommentByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.editComment(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/editcommentbatch")
    public ResponseEntity<Boolean> editCommentByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.editCommentBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/managepmsee")
    public ResponseEntity<IBZProProductActionDTO> managePmsEeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.managePmsEe(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/managepmseebatch")
    public ResponseEntity<Boolean> managePmsEeByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.managePmsEeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品保存产品日志", tags = {"产品日志" },  notes = "根据产品保存产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/save")
    public ResponseEntity<IBZProProductActionDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        ibzproproductactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存产品日志", tags = {"产品日志" },  notes = "根据产品批量保存产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domainlist=ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        for(IBZProProductAction domain:domainlist){
             domain.setObjectid(product_id);
        }
        ibzproproductactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/sendmarkdone")
    public ResponseEntity<IBZProProductActionDTO> sendMarkDoneByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendMarkDone(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/sendmarkdonebatch")
    public ResponseEntity<Boolean> sendMarkDoneByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendMarkDoneBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/sendtodo")
    public ResponseEntity<IBZProProductActionDTO> sendTodoByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendTodo(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/sendtodobatch")
    public ResponseEntity<Boolean> sendTodoByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendTodoBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品产品日志", tags = {"产品日志" },  notes = "根据产品产品日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/{ibzproproductaction_id}/sendtoread")
    public ResponseEntity<IBZProProductActionDTO> sendToreadByProduct(@PathVariable("product_id") Long product_id, @PathVariable("ibzproproductaction_id") Long ibzproproductaction_id, @RequestBody IBZProProductActionDTO ibzproproductactiondto) {
        IBZProProductAction domain = ibzproproductactionMapping.toDomain(ibzproproductactiondto);
        domain.setObjectid(product_id);
        domain.setId(ibzproproductaction_id);
        domain = ibzproproductactionService.sendToread(domain) ;
        ibzproproductactiondto = ibzproproductactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductactiondto);
    }
    @ApiOperation(value = "批量处理[根据产品产品日志]", tags = {"产品日志" },  notes = "批量处理[根据产品产品日志]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/ibzproproductactions/sendtoreadbatch")
    public ResponseEntity<Boolean> sendToreadByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<IBZProProductActionDTO> ibzproproductactiondtos) {
        List<IBZProProductAction> domains = ibzproproductactionMapping.toDomain(ibzproproductactiondtos);
        boolean result = ibzproproductactionService.sendToreadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据集", tags = {"产品日志" } ,notes = "根据产品获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/fetchdefault")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchIBZProProductActionDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchDefault(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询数据集", tags = {"产品日志" } ,notes = "根据产品查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/searchdefault")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchIBZProProductActionDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取动态(根据类型过滤)", tags = {"产品日志" } ,notes = "根据产品获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/fetchmobtype")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchIBZProProductActionMobTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchMobType(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询动态(根据类型过滤)", tags = {"产品日志" } ,notes = "根据产品查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/searchmobtype")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchIBZProProductActionMobTypeByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchMobType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品动态(产品相关所有)", tags = {"产品日志" } ,notes = "根据产品获取产品动态(产品相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/fetchproducttrends")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchIBZProProductActionProductTrendsByProduct(@PathVariable("product_id") Long product_id,@RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchProductTrends(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品动态(产品相关所有)", tags = {"产品日志" } ,notes = "根据产品查询产品动态(产品相关所有)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/searchproducttrends")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchIBZProProductActionProductTrendsByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchProductTrends(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取动态(根据类型过滤)", tags = {"产品日志" } ,notes = "根据产品获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/fetchtype")
	public ResponseEntity<List<IBZProProductActionDTO>> fetchIBZProProductActionTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchType(context) ;
        List<IBZProProductActionDTO> list = ibzproproductactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询动态(根据类型过滤)", tags = {"产品日志" } ,notes = "根据产品查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/ibzproproductactions/searchtype")
	public ResponseEntity<Page<IBZProProductActionDTO>> searchIBZProProductActionTypeByProduct(@PathVariable("product_id") Long product_id, @RequestBody IBZProProductActionSearchContext context) {
        context.setN_objectid_eq(product_id);
        Page<IBZProProductAction> domains = ibzproproductactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

