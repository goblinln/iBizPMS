package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocLibRuntime;

@Slf4j
@Api(tags = {"文档库"})
@RestController("StandardAPI-doclib")
@RequestMapping("")
public class DocLibResource {

    @Autowired
    public IDocLibService doclibService;

    @Autowired
    public DocLibRuntime doclibRuntime;

    @Autowired
    @Lazy
    public DocLibMapping doclibMapping;

    @PreAuthorize("quickTest('ZT_DOCLIB', 'CREATE')")
    @ApiOperation(value = "新建文档库", tags = {"文档库" },  notes = "新建文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs")
    @Transactional
    public ResponseEntity<DocLibDTO> create(@Validated @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
		doclibService.create(domain);
        if(!doclibRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOCLIB', #doclib_id, 'READ')")
    @ApiOperation(value = "获取文档库", tags = {"文档库" },  notes = "获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> get(@PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs(doclib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOCLIB', #doclib_id, 'DELETE')")
    @ApiOperation(value = "删除文档库", tags = {"文档库" },  notes = "删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doclib_id") Long doclib_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("quickTest('ZT_DOCLIB', 'DELETE')")
    @ApiOperation(value = "批量删除文档库", tags = {"文档库" },  notes = "批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOCLIB', #doclib_id, 'UPDATE')")
    @ApiOperation(value = "更新文档库", tags = {"文档库" },  notes = "更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}")
    @Transactional
    public ResponseEntity<DocLibDTO> update(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
		DocLib domain  = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
		doclibService.update(domain );
        if(!doclibRuntime.test(doclib_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs(doclib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', #doclib_id, 'COLLECT')")
    @ApiOperation(value = "收藏", tags = {"文档库" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain);
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }


    @PreAuthorize("quickTest('ZT_DOCLIB', 'CREATE')")
    @ApiOperation(value = "获取文档库草稿", tags = {"文档库" },  notes = "获取文档库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraft(DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOCLIB', #doclib_id, 'UNCOLLECT')")
    @ApiOperation(value = "取消收藏", tags = {"文档库" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain);
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }


    @PreAuthorize("quickTest('ZT_DOCLIB', 'READ')")
	@ApiOperation(value = "获取自定义文档库", tags = {"文档库" } ,notes = "获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchbycustom(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCLIB', 'READ')")
	@ApiOperation(value = "获取产品文档库", tags = {"文档库" } ,notes = "获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchbyproduct(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCLIB', 'READ')")
	@ApiOperation(value = "获取项目文件库", tags = {"文档库" } ,notes = "获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchbyproject(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCLIB', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档库" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchdefault(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCLIB', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档库" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchmyfavourites(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成文档库报表", tags = {"文档库"}, notes = "生成文档库报表")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DocLibSearchContext context, HttpServletResponse response) {
        try {
            doclibRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", doclibRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, doclibRuntime);
        }
    }

    @ApiOperation(value = "打印文档库", tags = {"文档库"}, notes = "打印文档库")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("doclib_ids") Set<Long> doclib_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = doclibRuntime.getDEPrintRuntime(print_id);
        try {
            List<DocLib> domains = new ArrayList<>();
            for (Long doclib_id : doclib_ids) {
                domains.add(doclibService.get( doclib_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new DocLib[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", doclibRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", doclib_ids, e.getMessage()), Errors.INTERNALERROR, doclibRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/{action}")
    public ResponseEntity<DocLibDTO> dynamicCall(@PathVariable("doclib_id") Long doclib_id , @PathVariable("action") String action , @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibService.dynamicCall(doclib_id, action, doclibMapping.toDomain(doclibdto));
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立文档库", tags = {"文档库" },  notes = "根据产品建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs")
    public ResponseEntity<DocLibDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
		doclibService.create(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', #doclib_id, 'READ')")
    @ApiOperation(value = "根据产品获取文档库", tags = {"文档库" },  notes = "根据产品获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'DELETE', #doclib_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除文档库", tags = {"文档库" },  notes = "根据产品删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除文档库", tags = {"文档库" },  notes = "根据产品批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'UPDATE', #doclib_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新文档库", tags = {"文档库" },  notes = "根据产品更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
		doclibService.update(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', #doclib_id, 'COLLECT')")
    @ApiOperation(value = "根据产品收藏", tags = {"文档库" },  notes = "根据产品收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取文档库草稿", tags = {"文档库" },  notes = "根据产品获取文档库草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', #doclib_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"文档库" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"文档库" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"文档库" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"文档库" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"文档库" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"文档库" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立文档库", tags = {"文档库" },  notes = "根据项目建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs")
    public ResponseEntity<DocLibDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
		doclibService.create(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'READ')")
    @ApiOperation(value = "根据项目获取文档库", tags = {"文档库" },  notes = "根据项目获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除文档库", tags = {"文档库" },  notes = "根据项目删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除文档库", tags = {"文档库" },  notes = "根据项目批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新文档库", tags = {"文档库" },  notes = "根据项目更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
		doclibService.update(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'READ', #doclib_id, 'COLLECT')")
    @ApiOperation(value = "根据项目收藏", tags = {"文档库" },  notes = "根据项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取文档库草稿", tags = {"文档库" },  notes = "根据项目获取文档库草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraftByProject(@PathVariable("project_id") Long project_id, DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'READ', #doclib_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"文档库" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"文档库" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"文档库" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"文档库" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"文档库" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"文档库" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目建立文档库", tags = {"文档库" },  notes = "根据产品项目建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs")
    public ResponseEntity<DocLibDTO> createByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
		doclibService.create(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'READ')")
    @ApiOperation(value = "根据产品项目获取文档库", tags = {"文档库" },  notes = "根据产品项目获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> getByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目删除文档库", tags = {"文档库" },  notes = "根据产品项目删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> removeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品项目批量删除文档库", tags = {"文档库" },  notes = "根据产品项目批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/batch")
    public ResponseEntity<Boolean> removeBatchByProductProject(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclib_id, 'UPDATE')")
    @ApiOperation(value = "根据产品项目更新文档库", tags = {"文档库" },  notes = "根据产品项目更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> updateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
		doclibService.update(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'READ', #doclib_id, 'COLLECT')")
    @ApiOperation(value = "根据产品项目收藏", tags = {"文档库" },  notes = "根据产品项目收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collectByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目获取文档库草稿", tags = {"文档库" },  notes = "根据产品项目获取文档库草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraftByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'READ', #doclib_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据产品项目取消收藏", tags = {"文档库" },  notes = "根据产品项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollectByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }

    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据产品项目获取自定义文档库", tags = {"文档库" } ,notes = "根据产品项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchByCustomByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据产品项目获取产品文档库", tags = {"文档库" } ,notes = "根据产品项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchByProductByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据产品项目获取项目文件库", tags = {"文档库" } ,notes = "根据产品项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchByProjectByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据产品项目获取DEFAULT", tags = {"文档库" } ,notes = "根据产品项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchDefaultByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOCLIB', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据产品项目获取我的收藏", tags = {"文档库" } ,notes = "根据产品项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchMyFavouritesByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

