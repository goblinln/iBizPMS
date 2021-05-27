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
import cn.ibizlab.pms.core.zentao.domain.Branch;
import cn.ibizlab.pms.core.zentao.service.IBranchService;
import cn.ibizlab.pms.core.zentao.filter.BranchSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BranchRuntime;

@Slf4j
@Api(tags = {"产品的分支和平台信息" })
@RestController("WebApi-branch")
@RequestMapping("")
public class BranchResource {

    @Autowired
    public IBranchService branchService;

    @Autowired
    public BranchRuntime branchRuntime;

    @Autowired
    @Lazy
    public BranchMapping branchMapping;

    @PreAuthorize("@BranchRuntime.quickTest('READ')")
	@ApiOperation(value = "获取CurProduct", tags = {"产品的分支和平台信息" } ,notes = "获取CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/branches/fetchcurproduct")
	public ResponseEntity<List<BranchDTO>> fetchcurproduct(@RequestBody BranchSearchContext context) {
        Page<Branch> domains = branchService.searchCurProduct(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BranchRuntime.quickTest('READ')")
	@ApiOperation(value = "查询CurProduct", tags = {"产品的分支和平台信息" } ,notes = "查询CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/branches/searchcurproduct")
	public ResponseEntity<Page<BranchDTO>> searchCurProduct(@RequestBody BranchSearchContext context) {
        Page<Branch> domains = branchService.searchCurProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(branchMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @ApiOperation(value = "保存产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "保存产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/branches/save")
    public ResponseEntity<BranchDTO> save(@RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        branchService.save(domain);
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String,Integer> opprivs = branchRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BranchRuntime.test(#branch_id,'CREATE')")
    @ApiOperation(value = "获取产品的分支和平台信息草稿", tags = {"产品的分支和平台信息" },  notes = "获取产品的分支和平台信息草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/branches/getdraft")
    public ResponseEntity<BranchDTO> getDraft(BranchDTO dto) {
        Branch domain = branchMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(branchMapping.toDto(branchService.getDraft(domain)));
    }

    @PreAuthorize("@BranchRuntime.test(#branch_id,'READ')")
    @ApiOperation(value = "获取产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "获取产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.GET, value = "/branches/{branch_id}")
    public ResponseEntity<BranchDTO> get(@PathVariable("branch_id") Long branch_id) {
        Branch domain = branchService.get(branch_id);
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String,Integer> opprivs = branchRuntime.getOPPrivs(branch_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BranchRuntime.test(#branch_id,'UPDATE')")
    @ApiOperation(value = "更新产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "更新产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/branches/{branch_id}")
    @Transactional
    public ResponseEntity<BranchDTO> update(@PathVariable("branch_id") Long branch_id, @RequestBody BranchDTO branchdto) {
		Branch domain  = branchMapping.toDomain(branchdto);
        domain.setId(branch_id);
		branchService.update(domain );
        if(!branchRuntime.test(branch_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		BranchDTO dto = branchMapping.toDto(domain);
        Map<String,Integer> opprivs = branchRuntime.getOPPrivs(branch_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BranchRuntime.test(#branch_id,'DELETE')")
    @ApiOperation(value = "删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/branches/{branch_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("branch_id") Long branch_id) {
         return ResponseEntity.status(HttpStatus.OK).body(branchService.remove(branch_id));
    }


    @PreAuthorize("@BranchRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/branches/fetchdefault")
	public ResponseEntity<List<BranchDTO>> fetchdefault(@RequestBody BranchSearchContext context) {
        Page<Branch> domains = branchService.searchDefault(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@BranchRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/branches/searchdefault")
	public ResponseEntity<Page<BranchDTO>> searchDefault(@RequestBody BranchSearchContext context) {
        Page<Branch> domains = branchService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(branchMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@BranchRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "新建产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/branches")
    @Transactional
    public ResponseEntity<BranchDTO> create(@Validated @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
		branchService.create(domain);
        if(!branchRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String,Integer> opprivs = branchRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/branches/{branch_id}/{action}")
    public ResponseEntity<BranchDTO> dynamicCall(@PathVariable("branch_id") Long branch_id , @PathVariable("action") String action , @RequestBody BranchDTO branchdto) {
        Branch domain = branchService.dynamicCall(branch_id, action, branchMapping.toDomain(branchdto));
        branchdto = branchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(branchdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取CurProduct", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/fetchcurproduct")
	public ResponseEntity<List<BranchDTO>> fetchBranchCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchCurProduct(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询CurProduct", tags = {"产品的分支和平台信息" } ,notes = "根据产品查询CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/searchcurproduct")
	public ResponseEntity<Page<BranchDTO>> searchBranchCurProductByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchCurProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(branchMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @ApiOperation(value = "根据产品保存产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品保存产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches/save")
    public ResponseEntity<BranchDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
        branchService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(branchMapping.toDto(domain));
    }


    @ApiOperation(value = "根据产品获取产品的分支和平台信息草稿", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/branches/getdraft")
    public ResponseEntity<BranchDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BranchDTO dto) {
        Branch domain = branchMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(branchMapping.toDto(branchService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<BranchDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id) {
        Branch domain = branchService.get(branch_id);
        BranchDTO dto = branchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品更新产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<BranchDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
        domain.setId(branch_id);
		branchService.update(domain);
        BranchDTO dto = branchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id) {
		return ResponseEntity.status(HttpStatus.OK).body(branchService.remove(branch_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/fetchdefault")
	public ResponseEntity<List<BranchDTO>> fetchBranchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchDefault(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/searchdefault")
	public ResponseEntity<Page<BranchDTO>> searchBranchDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(branchMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品建立产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches")
    public ResponseEntity<BranchDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
		branchService.create(domain);
        BranchDTO dto = branchMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

