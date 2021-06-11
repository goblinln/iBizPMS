package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
@Api(tags = {"产品的分支和平台信息"})
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

    @PreAuthorize("quickTest('ZT_BRANCH', 'CREATE')")
    @ApiOperation(value = "新建产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "新建产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/branches")
    @Transactional
    public ResponseEntity<BranchDTO> create(@Validated @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
		branchService.create(domain);
        if(!branchRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BRANCH', #branch_id, 'READ')")
    @ApiOperation(value = "获取产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "获取产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.GET, value = "/branches/{branch_id}")
    public ResponseEntity<BranchDTO> get(@PathVariable("branch_id") Long branch_id) {
        Branch domain = branchService.get(branch_id);
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs(branch_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BRANCH', #branch_id, 'DELETE')")
    @ApiOperation(value = "删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/branches/{branch_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("branch_id") Long branch_id) {
         return ResponseEntity.status(HttpStatus.OK).body(branchService.remove(branch_id));
    }

    @PreAuthorize("quickTest('ZT_BRANCH', 'DELETE')")
    @ApiOperation(value = "批量删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "批量删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/branches/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        branchService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_BRANCH', #branch_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs(branch_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_BRANCH', 'CREATE')")
    @ApiOperation(value = "检查产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "检查产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/branches/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BranchDTO branchdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(branchService.checkKey(branchMapping.toDomain(branchdto)));
    }

    @PreAuthorize("quickTest('ZT_BRANCH', 'CREATE')")
    @ApiOperation(value = "获取产品的分支和平台信息草稿", tags = {"产品的分支和平台信息" },  notes = "获取产品的分支和平台信息草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/branches/getdraft")
    public ResponseEntity<BranchDTO> getDraft(BranchDTO dto) {
        Branch domain = branchMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(branchMapping.toDto(branchService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('ZT_BRANCH', 'DENY')")
    @ApiOperation(value = "批量保存产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "批量保存产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/branches/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<BranchDTO> branchdtos) {
        branchService.saveBatch(branchMapping.toDomain(branchdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_BRANCH', 'DENY')")
    @ApiOperation(value = "排序", tags = {"产品的分支和平台信息" },  notes = "排序")
	@RequestMapping(method = RequestMethod.POST, value = "/branches/{branch_id}/sort")
    public ResponseEntity<BranchDTO> sort(@PathVariable("branch_id") Long branch_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setId(branch_id);
        domain = branchService.sort(domain);
        branchdto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs(domain.getId());
        branchdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(branchdto);
    }


    @PreAuthorize("quickTest('ZT_BRANCH', 'READ')")
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
    @PreAuthorize("quickTest('ZT_BRANCH', 'READ')")
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

    @ApiOperation(value = "生成产品的分支和平台信息报表", tags = {"产品的分支和平台信息"}, notes = "生成产品的分支和平台信息报表")
    @RequestMapping(method = RequestMethod.GET, value = "/branches/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, BranchSearchContext context, HttpServletResponse response) {
        try {
            branchRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", branchRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, branchRuntime);
        }
    }

    @ApiOperation(value = "打印产品的分支和平台信息", tags = {"产品的分支和平台信息"}, notes = "打印产品的分支和平台信息")
    @RequestMapping(method = RequestMethod.GET, value = "/branches/{branch_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("branch_ids") Set<Long> branch_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = branchRuntime.getDEPrintRuntime(print_id);
        try {
            List<Branch> domains = new ArrayList<>();
            for (Long branch_id : branch_ids) {
                domains.add(branchService.get( branch_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Branch[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", branchRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", branch_ids, e.getMessage()), Errors.INTERNALERROR, branchRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/branches/{branch_id}/{action}")
    public ResponseEntity<BranchDTO> dynamicCall(@PathVariable("branch_id") Long branch_id , @PathVariable("action") String action , @RequestBody BranchDTO branchdto) {
        Branch domain = branchService.dynamicCall(branch_id, action, branchMapping.toDomain(branchdto));
        branchdto = branchMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(branchdto);
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品建立产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches")
    public ResponseEntity<BranchDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
		branchService.create(domain);
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'READ', #branch_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<BranchDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id) {
        Branch domain = branchService.get(branch_id);
        if (domain == null || domain.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'DELETE', #branch_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id) {
        Branch testget = branchService.get(branch_id);
        if (testget == null || testget.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(branchService.remove(branch_id));
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品批量删除产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/branches/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        branchService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'UPDATE', #branch_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品更新产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/branches/{branch_id}")
    public ResponseEntity<BranchDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id, @RequestBody BranchDTO branchdto) {
        Branch testget = branchService.get(branch_id);
        if (testget == null || testget.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
        domain.setId(branch_id);
		branchService.update(domain);
        BranchDTO dto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品检查产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody BranchDTO branchdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(branchService.checkKey(branchMapping.toDomain(branchdto)));
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取产品的分支和平台信息草稿", tags = {"产品的分支和平台信息" },  notes = "根据产品获取产品的分支和平台信息草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/branches/getdraft")
    public ResponseEntity<BranchDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BranchDTO dto) {
        Branch domain = branchMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(branchMapping.toDto(branchService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('ZT_BRANCH', 'DENY')")
    @ApiOperation(value = "根据产品批量保存产品的分支和平台信息", tags = {"产品的分支和平台信息" },  notes = "根据产品批量保存产品的分支和平台信息")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<BranchDTO> branchdtos) {
        List<Branch> domainlist=branchMapping.toDomain(branchdtos);
        for(Branch domain:domainlist){
             domain.setProduct(product_id);
        }
        branchService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_BRANCH', 'DENY')")
    @ApiOperation(value = "根据产品排序", tags = {"产品的分支和平台信息" },  notes = "根据产品排序")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/branches/{branch_id}/sort")
    public ResponseEntity<BranchDTO> sortByProduct(@PathVariable("product_id") Long product_id, @PathVariable("branch_id") Long branch_id, @RequestBody BranchDTO branchdto) {
        Branch domain = branchMapping.toDomain(branchdto);
        domain.setProduct(product_id);
        domain.setId(branch_id);
        domain = branchService.sort(domain) ;
        branchdto = branchMapping.toDto(domain);
        Map<String, Integer> opprivs = branchRuntime.getOPPrivs(domain.getId());    
        branchdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(branchdto);
    }

    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取CurProduct", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取CurProduct")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/fetchcurproduct")
	public ResponseEntity<List<BranchDTO>> fetchCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchCurProduct(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BRANCH', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"产品的分支和平台信息" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/branches/fetchdefault")
	public ResponseEntity<List<BranchDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BranchSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Branch> domains = branchService.searchDefault(context) ;
        List<BranchDTO> list = branchMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

