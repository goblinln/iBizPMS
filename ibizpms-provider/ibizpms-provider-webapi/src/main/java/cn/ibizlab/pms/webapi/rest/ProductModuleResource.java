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
import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.service.IProductModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductModuleRuntime;

@Slf4j
@Api(tags = {"需求模块"})
@RestController("WebApi-productmodule")
@RequestMapping("")
public class ProductModuleResource {

    @Autowired
    public IProductModuleService productmoduleService;

    @Autowired
    public ProductModuleRuntime productmoduleRuntime;

    @Autowired
    @Lazy
    public ProductModuleMapping productmoduleMapping;

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'CREATE')")
    @ApiOperation(value = "新建需求模块", tags = {"需求模块" },  notes = "新建需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules")
    @Transactional
    public ResponseEntity<ProductModuleDTO> create(@Validated @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
		productmoduleService.create(domain);
        if(!productmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', #productmodule_id, 'READ')")
    @ApiOperation(value = "获取需求模块", tags = {"需求模块" },  notes = "获取需求模块")
	@RequestMapping(method = RequestMethod.GET, value = "/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> get(@PathVariable("productmodule_id") Long productmodule_id) {
        ProductModule domain = productmoduleService.get(productmodule_id);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(productmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', #productmodule_id, 'DELETE')")
    @ApiOperation(value = "删除需求模块", tags = {"需求模块" },  notes = "删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productmodules/{productmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productmodule_id") Long productmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productmoduleService.remove(productmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除需求模块", tags = {"需求模块" },  notes = "批量删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        productmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', #productmodule_id, 'UPDATE')")
    @ApiOperation(value = "更新需求模块", tags = {"需求模块" },  notes = "更新需求模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/productmodules/{productmodule_id}")
    @Transactional
    public ResponseEntity<ProductModuleDTO> update(@PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
		ProductModule domain  = productmoduleMapping.toDomain(productmoduledto);
        domain.setId(productmodule_id);
		productmoduleService.update(domain );
        if(!productmoduleRuntime.test(productmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(productmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'CREATE')")
    @ApiOperation(value = "检查需求模块", tags = {"需求模块" },  notes = "检查需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductModuleDTO productmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productmoduleService.checkKey(productmoduleMapping.toDomain(productmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"需求模块" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/fix")
    public ResponseEntity<ProductModuleDTO> fix(@PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setId(productmodule_id);
        domain = productmoduleService.fix(domain);
        productmoduledto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());
        productmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }



    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "批量保存需求模块", tags = {"需求模块" },  notes = "批量保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProductModuleDTO> productmoduledtos) {
        productmoduleService.saveBatch(productmoduleMapping.toDomain(productmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "同步Ibz平台模块", tags = {"需求模块" },  notes = "同步Ibz平台模块")
	@RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/syncfromibiz")
    public ResponseEntity<ProductModuleDTO> syncFromIBIZ(@PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setId(productmodule_id);
        domain = productmoduleService.syncFromIBIZ(domain);
        productmoduledto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());
        productmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'READ')")
	@ApiOperation(value = "获取BYPATH", tags = {"需求模块" } ,notes = "获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchbypath")
	public ResponseEntity<List<ProductModuleDTO>> fetchbypath(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchByPath(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"需求模块" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchdefault")
	public ResponseEntity<List<ProductModuleDTO>> fetchdefault(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'READ')")
	@ApiOperation(value = "获取父模块", tags = {"需求模块" } ,notes = "获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchparentmodule")
	public ResponseEntity<List<ProductModuleDTO>> fetchparentmodule(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchParentModule(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'READ')")
	@ApiOperation(value = "获取根模块", tags = {"需求模块" } ,notes = "获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchroot")
	public ResponseEntity<List<ProductModuleDTO>> fetchroot(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchRoot(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'READ')")
	@ApiOperation(value = "获取根模块_无分支", tags = {"需求模块" } ,notes = "获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProductModuleDTO>> fetchroot_nobranch(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchRoot_NoBranch(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'NONE')")
	@ApiOperation(value = "获取StoryModule", tags = {"需求模块" } ,notes = "获取StoryModule")
    @RequestMapping(method= RequestMethod.POST , value="/productmodules/fetchstorymodule")
	public ResponseEntity<List<ProductModuleDTO>> fetchstorymodule(@RequestBody ProductModuleSearchContext context) {
        Page<ProductModule> domains = productmoduleService.searchStoryModule(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成需求模块报表", tags = {"需求模块"}, notes = "生成需求模块报表")
    @RequestMapping(method = RequestMethod.GET, value = "/productmodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProductModuleSearchContext context, HttpServletResponse response) {
        try {
            productmoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productmoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, productmoduleRuntime);
        }
    }

    @ApiOperation(value = "打印需求模块", tags = {"需求模块"}, notes = "打印需求模块")
    @RequestMapping(method = RequestMethod.GET, value = "/productmodules/{productmodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("productmodule_ids") Set<Long> productmodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = productmoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProductModule> domains = new ArrayList<>();
            for (Long productmodule_id : productmodule_ids) {
                domains.add(productmoduleService.get( productmodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProductModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productmoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", productmodule_ids, e.getMessage()), Errors.INTERNALERROR, productmoduleRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/{action}")
    public ResponseEntity<ProductModuleDTO> dynamicCall(@PathVariable("productmodule_id") Long productmodule_id , @PathVariable("action") String action , @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleService.dynamicCall(productmodule_id, action, productmoduleMapping.toDomain(productmoduledto));
        productmoduledto = productmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立需求模块", tags = {"需求模块" },  notes = "根据产品建立需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules")
    public ResponseEntity<ProductModuleDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
		productmoduleService.create(domain);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', #productmodule_id, 'READ')")
    @ApiOperation(value = "根据产品获取需求模块", tags = {"需求模块" },  notes = "根据产品获取需求模块")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id) {
        ProductModule domain = productmoduleService.get(productmodule_id);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', #productmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除需求模块", tags = {"需求模块" },  notes = "根据产品删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productmoduleService.remove(productmodule_id));
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除需求模块", tags = {"需求模块" },  notes = "根据产品批量删除需求模块")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'UPDATE', #productmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新需求模块", tags = {"需求模块" },  notes = "根据产品更新需求模块")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productmodules/{productmodule_id}")
    public ResponseEntity<ProductModuleDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule testget = productmoduleService.get(productmodule_id);
        if (testget.getRoot() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
        domain.setId(productmodule_id);
		productmoduleService.update(domain);
        ProductModuleDTO dto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品检查需求模块", tags = {"需求模块" },  notes = "根据产品检查需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductModuleDTO productmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productmoduleService.checkKey(productmoduleMapping.toDomain(productmoduledto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "根据产品重建模块路径", tags = {"需求模块" },  notes = "根据产品重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/fix")
    public ResponseEntity<ProductModuleDTO> fixByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
        domain.setId(productmodule_id);
        domain = productmoduleService.fix(domain) ;
        productmoduledto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());    
        productmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "根据产品批量保存需求模块", tags = {"需求模块" },  notes = "根据产品批量保存需求模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductModuleDTO> productmoduledtos) {
        List<ProductModule> domainlist=productmoduleMapping.toDomain(productmoduledtos);
        for(ProductModule domain:domainlist){
             domain.setRoot(product_id);
        }
        productmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'DENY')")
    @ApiOperation(value = "根据产品同步Ibz平台模块", tags = {"需求模块" },  notes = "根据产品同步Ibz平台模块")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productmodules/{productmodule_id}/syncfromibiz")
    public ResponseEntity<ProductModuleDTO> syncFromIBIZByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productmodule_id") Long productmodule_id, @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleMapping.toDomain(productmoduledto);
        domain.setRoot(product_id);
        domain.setId(productmodule_id);
        domain = productmoduleService.syncFromIBIZ(domain) ;
        productmoduledto = productmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = productmoduleRuntime.getOPPrivs(domain.getId());    
        productmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }

    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取BYPATH", tags = {"需求模块" } ,notes = "根据产品获取BYPATH")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchbypath")
	public ResponseEntity<List<ProductModuleDTO>> fetchByPathByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchByPath(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"需求模块" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchdefault")
	public ResponseEntity<List<ProductModuleDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchDefault(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取父模块", tags = {"需求模块" } ,notes = "根据产品获取父模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchparentmodule")
	public ResponseEntity<List<ProductModuleDTO>> fetchParentModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchParentModule(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取根模块", tags = {"需求模块" } ,notes = "根据产品获取根模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchroot")
	public ResponseEntity<List<ProductModuleDTO>> fetchRootByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchRoot(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_PRODUCTMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取根模块_无分支", tags = {"需求模块" } ,notes = "根据产品获取根模块_无分支")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchroot_nobranch")
	public ResponseEntity<List<ProductModuleDTO>> fetchRoot_NoBranchByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchRoot_NoBranch(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTMODULE', 'NONE')")
	@ApiOperation(value = "根据产品获取StoryModule", tags = {"需求模块" } ,notes = "根据产品获取StoryModule")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productmodules/fetchstorymodule")
	public ResponseEntity<List<ProductModuleDTO>> fetchStoryModuleByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProductModuleSearchContext context) {
        context.setN_root_eq(product_id);
        Page<ProductModule> domains = productmoduleService.searchStoryModule(context) ;
        List<ProductModuleDTO> list = productmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

