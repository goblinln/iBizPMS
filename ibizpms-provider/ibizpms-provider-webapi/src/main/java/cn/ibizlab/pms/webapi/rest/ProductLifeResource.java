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
import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.service.IProductLifeService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductLifeRuntime;

@Slf4j
@Api(tags = {"产品生命周期"})
@RestController("WebApi-productlife")
@RequestMapping("")
public class ProductLifeResource {

    @Autowired
    public IProductLifeService productlifeService;

    @Autowired
    public ProductLifeRuntime productlifeRuntime;

    @Autowired
    @Lazy
    public ProductLifeMapping productlifeMapping;

    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'CREATE')")
    @ApiOperation(value = "新建产品生命周期", tags = {"产品生命周期" },  notes = "新建产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives")
    @Transactional
    public ResponseEntity<ProductLifeDTO> create(@Validated @RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeMapping.toDomain(productlifedto);
		productlifeService.create(domain);
        if(!productlifeRuntime.test(domain.getProductlifeid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String, Integer> opprivs = productlifeRuntime.getOPPrivs(domain.getProductlifeid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTLIFE', #productlife_id, 'READ')")
    @ApiOperation(value = "获取产品生命周期", tags = {"产品生命周期" },  notes = "获取产品生命周期")
	@RequestMapping(method = RequestMethod.GET, value = "/productlives/{productlife_id}")
    public ResponseEntity<ProductLifeDTO> get(@PathVariable("productlife_id") String productlife_id) {
        ProductLife domain = productlifeService.get(productlife_id);
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String, Integer> opprivs = productlifeRuntime.getOPPrivs(productlife_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PRODUCTLIFE', #productlife_id, 'DELETE')")
    @ApiOperation(value = "删除产品生命周期", tags = {"产品生命周期" },  notes = "删除产品生命周期")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlives/{productlife_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productlife_id") String productlife_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productlifeService.remove(productlife_id));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'DELETE')")
    @ApiOperation(value = "批量删除产品生命周期", tags = {"产品生命周期" },  notes = "批量删除产品生命周期")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productlives/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        productlifeService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "productlife" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_PRODUCTLIFE', #productlife_id, 'UPDATE')")
    @ApiOperation(value = "更新产品生命周期", tags = {"产品生命周期" },  notes = "更新产品生命周期")
	@RequestMapping(method = RequestMethod.PUT, value = "/productlives/{productlife_id}")
    @Transactional
    public ResponseEntity<ProductLifeDTO> update(@PathVariable("productlife_id") String productlife_id, @RequestBody ProductLifeDTO productlifedto) {
		ProductLife domain  = productlifeMapping.toDomain(productlifedto);
        domain.setProductlifeid(productlife_id);
		productlifeService.update(domain );
        if(!productlifeRuntime.test(productlife_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String, Integer> opprivs = productlifeRuntime.getOPPrivs(productlife_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'CREATE')")
    @ApiOperation(value = "检查产品生命周期", tags = {"产品生命周期" },  notes = "检查产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProductLifeDTO productlifedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(productlifeService.checkKey(productlifeMapping.toDomain(productlifedto)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'CREATE')")
    @ApiOperation(value = "获取产品生命周期草稿", tags = {"产品生命周期" },  notes = "获取产品生命周期草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productlives/getdraft")
    public ResponseEntity<ProductLifeDTO> getDraft(ProductLifeDTO dto) {
        ProductLife domain = productlifeMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productlifeMapping.toDto(productlifeService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'DENY')")
    @ApiOperation(value = "保存产品生命周期", tags = {"产品生命周期" },  notes = "保存产品生命周期")
	@RequestMapping(method = RequestMethod.POST, value = "/productlives/save")
    public ResponseEntity<ProductLifeDTO> save(@RequestBody ProductLifeDTO productlifedto) {
        ProductLife domain = productlifeMapping.toDomain(productlifedto);
        productlifeService.save(domain);
        ProductLifeDTO dto = productlifeMapping.toDto(domain);
        Map<String, Integer> opprivs = productlifeRuntime.getOPPrivs(domain.getProductlifeid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"产品生命周期" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchdefault")
	public ResponseEntity<List<ProductLifeDTO>> fetchdefault(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchDefault(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'READ')")
	@ApiOperation(value = "获取GetRoadmap", tags = {"产品生命周期" } ,notes = "获取GetRoadmap")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchgetroadmap")
	public ResponseEntity<List<ProductLifeDTO>> fetchgetroadmap(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmap(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'READ')")
	@ApiOperation(value = "获取获取产品路线", tags = {"产品生命周期" } ,notes = "获取获取产品路线")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchgetroadmaps")
	public ResponseEntity<List<ProductLifeDTO>> fetchgetroadmaps(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchGetRoadmapS(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PRODUCTLIFE', 'READ')")
	@ApiOperation(value = "获取RoadMapYear", tags = {"产品生命周期" } ,notes = "获取RoadMapYear")
    @RequestMapping(method= RequestMethod.POST , value="/productlives/fetchroadmapyear")
	public ResponseEntity<List<ProductLifeDTO>> fetchroadmapyear(@RequestBody ProductLifeSearchContext context) {
        Page<ProductLife> domains = productlifeService.searchRoadMapYear(context) ;
        List<ProductLifeDTO> list = productlifeMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品生命周期报表", tags = {"产品生命周期"}, notes = "生成产品生命周期报表")
    @RequestMapping(method = RequestMethod.GET, value = "/productlives/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProductLifeSearchContext context, HttpServletResponse response) {
        try {
            productlifeRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productlifeRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, productlifeRuntime);
        }
    }

    @ApiOperation(value = "打印产品生命周期", tags = {"产品生命周期"}, notes = "打印产品生命周期")
    @RequestMapping(method = RequestMethod.GET, value = "/productlives/{productlife_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("productlife_ids") Set<String> productlife_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = productlifeRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProductLife> domains = new ArrayList<>();
            for (String productlife_id : productlife_ids) {
                domains.add(productlifeService.get( productlife_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProductLife[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", productlifeRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", productlife_ids, e.getMessage()), Errors.INTERNALERROR, productlifeRuntime);
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

}

