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
import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.service.IProductSumService;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductSumRuntime;

@Slf4j
@Api(tags = {"产品汇总表"})
@RestController("WebApi-productsum")
@RequestMapping("")
public class ProductSumResource {

    @Autowired
    public IProductSumService productsumService;

    @Autowired
    public ProductSumRuntime productsumRuntime;

    @Autowired
    @Lazy
    public ProductSumMapping productsumMapping;

    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'CREATE')")
    @ApiOperation(value = "新建产品汇总表", tags = {"产品汇总表" },  notes = "新建产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums")
    @Transactional
    public ResponseEntity<ProductSumDTO> create(@Validated @RequestBody ProductSumDTO productsumdto) {
        ProductSum domain = productsumMapping.toDomain(productsumdto);
		productsumService.create(domain);
        if(!productsumRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductSumDTO dto = productsumMapping.toDto(domain);
        Map<String, Integer> opprivs = productsumRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTSUM', #productsum_id, 'READ')")
    @ApiOperation(value = "获取产品汇总表", tags = {"产品汇总表" },  notes = "获取产品汇总表")
	@RequestMapping(method = RequestMethod.GET, value = "/productsums/{productsum_id}")
    public ResponseEntity<ProductSumDTO> get(@PathVariable("productsum_id") Long productsum_id) {
        ProductSum domain = productsumService.get(productsum_id);
        ProductSumDTO dto = productsumMapping.toDto(domain);
        Map<String, Integer> opprivs = productsumRuntime.getOPPrivs(productsum_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTSUM', #productsum_id, 'DELETE')")
    @ApiOperation(value = "删除产品汇总表", tags = {"产品汇总表" },  notes = "删除产品汇总表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productsums/{productsum_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productsum_id") Long productsum_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productsumService.remove(productsum_id));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'DELETE')")
    @ApiOperation(value = "批量删除产品汇总表", tags = {"产品汇总表" },  notes = "批量删除产品汇总表")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productsums/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productsumService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTSUM', #productsum_id, 'UPDATE')")
    @ApiOperation(value = "更新产品汇总表", tags = {"产品汇总表" },  notes = "更新产品汇总表")
	@RequestMapping(method = RequestMethod.PUT, value = "/productsums/{productsum_id}")
    @Transactional
    public ResponseEntity<ProductSumDTO> update(@PathVariable("productsum_id") Long productsum_id, @RequestBody ProductSumDTO productsumdto) {
		ProductSum domain  = productsumMapping.toDomain(productsumdto);
        domain.setId(productsum_id);
		productsumService.update(domain );
        if(!productsumRuntime.test(productsum_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductSumDTO dto = productsumMapping.toDto(domain);
        Map<String, Integer> opprivs = productsumRuntime.getOPPrivs(productsum_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'CREATE')")
    @ApiOperation(value = "检查产品汇总表", tags = {"产品汇总表" },  notes = "检查产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductSumDTO productsumdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productsumService.checkKey(productsumMapping.toDomain(productsumdto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'CREATE')")
    @ApiOperation(value = "获取产品汇总表草稿", tags = {"产品汇总表" },  notes = "获取产品汇总表草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productsums/getdraft")
    public ResponseEntity<ProductSumDTO> getDraft(ProductSumDTO dto) {
        ProductSum domain = productsumMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productsumMapping.toDto(productsumService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'DENY')")
    @ApiOperation(value = "保存产品汇总表", tags = {"产品汇总表" },  notes = "保存产品汇总表")
	@RequestMapping(method = RequestMethod.POST, value = "/productsums/save")
    public ResponseEntity<ProductSumDTO> save(@RequestBody ProductSumDTO productsumdto) {
        ProductSum domain = productsumMapping.toDomain(productsumdto);
        productsumService.save(domain);
        ProductSumDTO dto = productsumMapping.toDto(domain);
        Map<String, Integer> opprivs = productsumRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品汇总表" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchdefault")
	public ResponseEntity<List<ProductSumDTO>> fetchdefault(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchDefault(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品创建bug数及占比", tags = {"产品汇总表" } ,notes = "获取产品创建bug数及占比")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductbugcnt_qa")
	public ResponseEntity<List<ProductSumDTO>> fetchproductbugcnt_qa(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductBugcnt_QA(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品创建需求占比", tags = {"产品汇总表" } ,notes = "获取产品创建需求占比")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductcreatestory")
	public ResponseEntity<List<ProductSumDTO>> fetchproductcreatestory(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductCreateStory(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品需求工时汇总", tags = {"产品汇总表" } ,notes = "获取产品需求工时汇总")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductstoryhourssum")
	public ResponseEntity<List<ProductSumDTO>> fetchproductstoryhourssum(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStoryHoursSum(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品需求汇总查询", tags = {"产品汇总表" } ,notes = "获取产品需求汇总查询")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductstorysum")
	public ResponseEntity<List<ProductSumDTO>> fetchproductstorysum(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorySum(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品计划数和需求数", tags = {"产品汇总表" } ,notes = "获取产品计划数和需求数")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductstorycntandplancnt")
	public ResponseEntity<List<ProductSumDTO>> fetchproductstorycntandplancnt(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductStorycntAndPlancnt(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTSUM', 'NONE')")
	@ApiOperation(value = "获取产品Bug类型统计", tags = {"产品汇总表" } ,notes = "获取产品Bug类型统计")
    @RequestMapping(method= RequestMethod.POST , value="/productsums/fetchproductsumbugtype")
	public ResponseEntity<List<ProductSumDTO>> fetchproductsumbugtype(@RequestBody ProductSumSearchContext context) {
        Page<ProductSum> domains = productsumService.searchProductSumBugType(context) ;
        List<ProductSumDTO> list = productsumMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品汇总表报表", tags = {"产品汇总表"}, notes = "生成产品汇总表报表")
    @RequestMapping(method = RequestMethod.GET, value = "/productsums/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProductSumSearchContext context, HttpServletResponse response) {
        try {
            productsumRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productsumRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, productsumRuntime);
        }
    }

    @ApiOperation(value = "打印产品汇总表", tags = {"产品汇总表"}, notes = "打印产品汇总表")
    @RequestMapping(method = RequestMethod.GET, value = "/productsums/{productsum_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("productsum_ids") Set<Long> productsum_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = productsumRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProductSum> domains = new ArrayList<>();
            for (Long productsum_id : productsum_ids) {
                domains.add(productsumService.get( productsum_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProductSum[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productsumRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", productsum_ids, e.getMessage()), Errors.INTERNALERROR, productsumRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/productsums/{productsum_id}/{action}")
    public ResponseEntity<ProductSumDTO> dynamicCall(@PathVariable("productsum_id") Long productsum_id , @PathVariable("action") String action , @RequestBody ProductSumDTO productsumdto) {
        ProductSum domain = productsumService.dynamicCall(productsum_id, action, productsumMapping.toDomain(productsumdto));
        productsumdto = productsumMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productsumdto);
    }
}

