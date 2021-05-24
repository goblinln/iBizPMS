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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildAction;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBuildActionService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildActionSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProBuildActionRuntime;

@Slf4j
@Api(tags = {"版本日志" })
@RestController("WebApi-ibzprobuildaction")
@RequestMapping("")
public class IbzProBuildActionResource {

    @Autowired
    public IIbzProBuildActionService ibzprobuildactionService;

    @Autowired
    public IbzProBuildActionRuntime ibzprobuildactionRuntime;

    @Autowired
    @Lazy
    public IbzProBuildActionMapping ibzprobuildactionMapping;

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建版本日志", tags = {"版本日志" },  notes = "新建版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions")
    @Transactional
    public ResponseEntity<IbzProBuildActionDTO> create(@Validated @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
		ibzprobuildactionService.create(domain);
        if(!ibzprobuildactionRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建版本日志", tags = {"版本日志" },  notes = "批量新建版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.createBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'UPDATE')")
    @ApiOperation(value = "更新版本日志", tags = {"版本日志" },  notes = "更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    @Transactional
    public ResponseEntity<IbzProBuildActionDTO> update(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
		IbzProBuildAction domain  = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setId(ibzprobuildaction_id);
		ibzprobuildactionService.update(domain );
        if(!ibzprobuildactionRuntime.test(ibzprobuildaction_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(ibzprobuildaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新版本日志", tags = {"版本日志" },  notes = "批量更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.updateBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'DELETE')")
    @ApiOperation(value = "删除版本日志", tags = {"版本日志" },  notes = "删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.remove(ibzprobuildaction_id));
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除版本日志", tags = {"版本日志" },  notes = "批量删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobuildactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.test(#ibzprobuildaction_id,'READ')")
    @ApiOperation(value = "获取版本日志", tags = {"版本日志" },  notes = "获取版本日志")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> get(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
        IbzProBuildAction domain = ibzprobuildactionService.get(ibzprobuildaction_id);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(ibzprobuildaction_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取版本日志草稿", tags = {"版本日志" },  notes = "获取版本日志草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildactions/getdraft")
    public ResponseEntity<IbzProBuildActionDTO> getDraft(IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(ibzprobuildactionService.getDraft(domain)));
    }

    @ApiOperation(value = "检查版本日志", tags = {"版本日志" },  notes = "检查版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.checkKey(ibzprobuildactionMapping.toDomain(ibzprobuildactiondto)));
    }

    @ApiOperation(value = "保存版本日志", tags = {"版本日志" },  notes = "保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/save")
    public ResponseEntity<IbzProBuildActionDTO> save(@RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        ibzprobuildactionService.save(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildactionRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存版本日志", tags = {"版本日志" },  notes = "批量保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        ibzprobuildactionService.saveBatch(ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"版本日志" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/fetchdefault")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchdefault(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"版本日志" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/searchdefault")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchDefault(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"版本日志" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/fetchtype")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchtype(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProBuildActionRuntime.quickTest('READ')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"版本日志" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildactions/searchtype")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchType(@RequestBody IbzProBuildActionSearchContext context) {
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildactions/{ibzprobuildaction_id}/{action}")
    public ResponseEntity<IbzProBuildActionDTO> dynamicCall(@PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id , @PathVariable("action") String action , @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionService.dynamicCall(ibzprobuildaction_id, action, ibzprobuildactionMapping.toDomain(ibzprobuildactiondto));
        ibzprobuildactiondto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactiondto);
    }
    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本建立版本日志", tags = {"版本日志" },  notes = "根据版本建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions")
    public ResponseEntity<IbzProBuildActionDTO> createByBuild(@PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
		ibzprobuildactionService.create(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'CREATE')")
    @ApiOperation(value = "根据版本批量建立版本日志", tags = {"版本日志" },  notes = "根据版本批量建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> createBatchByBuild(@PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本更新版本日志", tags = {"版本日志" },  notes = "根据版本更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> updateByBuild(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        domain.setId(ibzprobuildaction_id);
		ibzprobuildactionService.update(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'UPDATE')")
    @ApiOperation(value = "根据版本批量更新版本日志", tags = {"版本日志" },  notes = "根据版本批量更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> updateBatchByBuild(@PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本删除版本日志", tags = {"版本日志" },  notes = "根据版本删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<Boolean> removeByBuild(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.remove(ibzprobuildaction_id));
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'DELETE')")
    @ApiOperation(value = "根据版本批量删除版本日志", tags = {"版本日志" },  notes = "根据版本批量删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> removeBatchByBuild(@RequestBody List<Long> ids) {
        ibzprobuildactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
    @ApiOperation(value = "根据版本获取版本日志", tags = {"版本日志" },  notes = "根据版本获取版本日志")
	@RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> getByBuild(@PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
        IbzProBuildAction domain = ibzprobuildactionService.get(ibzprobuildaction_id);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据版本获取版本日志草稿", tags = {"版本日志" },  notes = "根据版本获取版本日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/builds/{build_id}/ibzprobuildactions/getdraft")
    public ResponseEntity<IbzProBuildActionDTO> getDraftByBuild(@PathVariable("build_id") Long build_id, IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(dto);
        domain.setObjectid(build_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(ibzprobuildactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据版本检查版本日志", tags = {"版本日志" },  notes = "根据版本检查版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByBuild(@PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.checkKey(ibzprobuildactionMapping.toDomain(ibzprobuildactiondto)));
    }

    @ApiOperation(value = "根据版本保存版本日志", tags = {"版本日志" },  notes = "根据版本保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/save")
    public ResponseEntity<IbzProBuildActionDTO> saveByBuild(@PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        ibzprobuildactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据版本批量保存版本日志", tags = {"版本日志" },  notes = "根据版本批量保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/builds/{build_id}/ibzprobuildactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByBuild(@PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
             domain.setObjectid(build_id);
        }
        ibzprobuildactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取数据集", tags = {"版本日志" } ,notes = "根据版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/fetchdefault")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionDefaultByBuild(@PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本查询数据集", tags = {"版本日志" } ,notes = "根据版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/searchdefault")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionDefaultByBuild(@PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本获取动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/fetchtype")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionTypeByBuild(@PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BuildRuntime.test(#build_id,'READ')")
	@ApiOperation(value = "根据版本查询动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/builds/{build_id}/ibzprobuildactions/searchtype")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionTypeByBuild(@PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本建立版本日志", tags = {"版本日志" },  notes = "根据产品版本建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions")
    public ResponseEntity<IbzProBuildActionDTO> createByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
		ibzprobuildactionService.create(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品版本批量建立版本日志", tags = {"版本日志" },  notes = "根据产品版本批量建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> createBatchByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本更新版本日志", tags = {"版本日志" },  notes = "根据产品版本更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> updateByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        domain.setId(ibzprobuildaction_id);
		ibzprobuildactionService.update(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品版本批量更新版本日志", tags = {"版本日志" },  notes = "根据产品版本批量更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> updateBatchByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本删除版本日志", tags = {"版本日志" },  notes = "根据产品版本删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<Boolean> removeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.remove(ibzprobuildaction_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品版本批量删除版本日志", tags = {"版本日志" },  notes = "根据产品版本批量删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> removeBatchByProductBuild(@RequestBody List<Long> ids) {
        ibzprobuildactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品版本获取版本日志", tags = {"版本日志" },  notes = "根据产品版本获取版本日志")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> getByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
        IbzProBuildAction domain = ibzprobuildactionService.get(ibzprobuildaction_id);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品版本获取版本日志草稿", tags = {"版本日志" },  notes = "根据产品版本获取版本日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/getdraft")
    public ResponseEntity<IbzProBuildActionDTO> getDraftByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(dto);
        domain.setObjectid(build_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(ibzprobuildactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品版本检查版本日志", tags = {"版本日志" },  notes = "根据产品版本检查版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.checkKey(ibzprobuildactionMapping.toDomain(ibzprobuildactiondto)));
    }

    @ApiOperation(value = "根据产品版本保存版本日志", tags = {"版本日志" },  notes = "根据产品版本保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/save")
    public ResponseEntity<IbzProBuildActionDTO> saveByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        ibzprobuildactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品版本批量保存版本日志", tags = {"版本日志" },  notes = "根据产品版本批量保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/ibzprobuildactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
             domain.setObjectid(build_id);
        }
        ibzprobuildactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取数据集", tags = {"版本日志" } ,notes = "根据产品版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/fetchdefault")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionDefaultByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本查询数据集", tags = {"版本日志" } ,notes = "根据产品版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/searchdefault")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionDefaultByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本获取动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据产品版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/fetchtype")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品版本查询动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据产品版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/ibzprobuildactions/searchtype")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本建立版本日志", tags = {"版本日志" },  notes = "根据项目版本建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions")
    public ResponseEntity<IbzProBuildActionDTO> createByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
		ibzprobuildactionService.create(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目版本批量建立版本日志", tags = {"版本日志" },  notes = "根据项目版本批量建立版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> createBatchByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本更新版本日志", tags = {"版本日志" },  notes = "根据项目版本更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> updateByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        domain.setId(ibzprobuildaction_id);
		ibzprobuildactionService.update(domain);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目版本批量更新版本日志", tags = {"版本日志" },  notes = "根据项目版本批量更新版本日志")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> updateBatchByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
            domain.setObjectid(build_id);
        }
        ibzprobuildactionService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本删除版本日志", tags = {"版本日志" },  notes = "根据项目版本删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<Boolean> removeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
		return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.remove(ibzprobuildaction_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目版本批量删除版本日志", tags = {"版本日志" },  notes = "根据项目版本批量删除版本日志")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBuild(@RequestBody List<Long> ids) {
        ibzprobuildactionService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目版本获取版本日志", tags = {"版本日志" },  notes = "根据项目版本获取版本日志")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/{ibzprobuildaction_id}")
    public ResponseEntity<IbzProBuildActionDTO> getByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("ibzprobuildaction_id") Long ibzprobuildaction_id) {
        IbzProBuildAction domain = ibzprobuildactionService.get(ibzprobuildaction_id);
        IbzProBuildActionDTO dto = ibzprobuildactionMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目版本获取版本日志草稿", tags = {"版本日志" },  notes = "根据项目版本获取版本日志草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/getdraft")
    public ResponseEntity<IbzProBuildActionDTO> getDraftByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(dto);
        domain.setObjectid(build_id);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(ibzprobuildactionService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目版本检查版本日志", tags = {"版本日志" },  notes = "根据项目版本检查版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionService.checkKey(ibzprobuildactionMapping.toDomain(ibzprobuildactiondto)));
    }

    @ApiOperation(value = "根据项目版本保存版本日志", tags = {"版本日志" },  notes = "根据项目版本保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/save")
    public ResponseEntity<IbzProBuildActionDTO> saveByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionDTO ibzprobuildactiondto) {
        IbzProBuildAction domain = ibzprobuildactionMapping.toDomain(ibzprobuildactiondto);
        domain.setObjectid(build_id);
        ibzprobuildactionService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildactionMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目版本批量保存版本日志", tags = {"版本日志" },  notes = "根据项目版本批量保存版本日志")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/ibzprobuildactions/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody List<IbzProBuildActionDTO> ibzprobuildactiondtos) {
        List<IbzProBuildAction> domainlist=ibzprobuildactionMapping.toDomain(ibzprobuildactiondtos);
        for(IbzProBuildAction domain:domainlist){
             domain.setObjectid(build_id);
        }
        ibzprobuildactionService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取数据集", tags = {"版本日志" } ,notes = "根据项目版本获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/fetchdefault")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionDefaultByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本查询数据集", tags = {"版本日志" } ,notes = "根据项目版本查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/searchdefault")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionDefaultByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本获取动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据项目版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/fetchtype")
	public ResponseEntity<List<IbzProBuildActionDTO>> fetchIbzProBuildActionTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
        List<IbzProBuildActionDTO> list = ibzprobuildactionMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目版本查询动态(根据类型过滤)", tags = {"版本日志" } ,notes = "根据项目版本查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/ibzprobuildactions/searchtype")
	public ResponseEntity<Page<IbzProBuildActionDTO>> searchIbzProBuildActionTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody IbzProBuildActionSearchContext context) {
        context.setN_objectid_eq(build_id);
        Page<IbzProBuildAction> domains = ibzprobuildactionService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprobuildactionMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

