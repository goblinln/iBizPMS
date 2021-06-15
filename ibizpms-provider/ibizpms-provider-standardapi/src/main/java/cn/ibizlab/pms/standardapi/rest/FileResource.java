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
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.FileRuntime;

@Slf4j
@Api(tags = {"附件"})
@RestController("StandardAPI-file")
@RequestMapping("")
public class FileResource {

    @Autowired
    public IFileService fileService;

    @Autowired
    public FileRuntime fileRuntime;

    @Autowired
    @Lazy
    public FileMapping fileMapping;

    @PreAuthorize("test('ZT_FILE', #file_id, 'DELETE')")
    @ApiOperation(value = "删除附件", tags = {"附件" },  notes = "删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/files/{file_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("file_id") Long file_id) {
         return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "批量删除附件", tags = {"附件" },  notes = "批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/files/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取文件库查询", tags = {"附件" } ,notes = "获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchproduct(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取文件库查询", tags = {"附件" } ,notes = "获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchproject(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"附件" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchtype(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成附件报表", tags = {"附件"}, notes = "生成附件报表")
    @RequestMapping(method = RequestMethod.GET, value = "/files/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, FileSearchContext context, HttpServletResponse response) {
        try {
            fileRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", fileRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, fileRuntime);
        }
    }

    @ApiOperation(value = "打印附件", tags = {"附件"}, notes = "打印附件")
    @RequestMapping(method = RequestMethod.GET, value = "/files/{file_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("file_ids") Set<Long> file_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = fileRuntime.getDEPrintRuntime(print_id);
        try {
            List<File> domains = new ArrayList<>();
            for (Long file_id : file_ids) {
                domains.add(fileService.get( file_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new File[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", fileRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", file_ids, e.getMessage()), Errors.INTERNALERROR, fileRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/files/{file_id}/{action}")
    public ResponseEntity<FileDTO> dynamicCall(@PathVariable("file_id") Long file_id , @PathVariable("action") String action , @RequestBody FileDTO filedto) {
        File domain = fileService.dynamicCall(file_id, action, fileMapping.toDomain(filedto));
        filedto = fileMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(filedto);
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据日报删除附件", tags = {"附件" },  notes = "根据日报删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/dailies/{ibzdaily_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据日报批量删除附件", tags = {"附件" },  notes = "根据日报批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/dailies/{ibzdaily_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByIbzDaily(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取文件库查询", tags = {"附件" } ,notes = "根据日报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取文件库查询", tags = {"附件" } ,notes = "根据日报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_DAILY', #ibzdaily_id, 'READ', 'READ')")
	@ApiOperation(value = "根据日报获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据日报获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/dailies/{ibzdaily_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByIbzDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档库删除附件", tags = {"附件" },  notes = "根据文档库删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档库批量删除附件", tags = {"附件" },  notes = "根据文档库批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByDocLib(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取文件库查询", tags = {"附件" } ,notes = "根据文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取文件库查询", tags = {"附件" } ,notes = "根据文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据文档库获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档删除附件", tags = {"附件" },  notes = "根据文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档批量删除附件", tags = {"附件" },  notes = "根据文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取文件库查询", tags = {"附件" } ,notes = "根据文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取文件库查询", tags = {"附件" } ,notes = "根据文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOC', #doc_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据月报删除附件", tags = {"附件" },  notes = "根据月报删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/monthlies/{ibzmonthly_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据月报批量删除附件", tags = {"附件" },  notes = "根据月报批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/monthlies/{ibzmonthly_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByIbzMonthly(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取文件库查询", tags = {"附件" } ,notes = "根据月报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取文件库查询", tags = {"附件" } ,notes = "根据月报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_MONTHLY', #ibzmonthly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据月报获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据月报获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/{ibzmonthly_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByIbzMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'DELETE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除附件", tags = {"附件" },  notes = "根据产品删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除附件", tags = {"附件" },  notes = "根据产品批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取文件库查询", tags = {"附件" } ,notes = "根据产品获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取文件库查询", tags = {"附件" } ,notes = "根据产品获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'DELETE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除附件", tags = {"附件" },  notes = "根据项目删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除附件", tags = {"附件" },  notes = "根据项目批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取文件库查询", tags = {"附件" } ,notes = "根据项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取文件库查询", tags = {"附件" } ,notes = "根据项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据汇报删除附件", tags = {"附件" },  notes = "根据汇报删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/reportlies/{ibzreportly_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据汇报批量删除附件", tags = {"附件" },  notes = "根据汇报批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/reportlies/{ibzreportly_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByIbzReportly(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取文件库查询", tags = {"附件" } ,notes = "根据汇报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取文件库查询", tags = {"附件" } ,notes = "根据汇报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_REPORTLY', #ibzreportly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据汇报获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据汇报获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/reportlies/{ibzreportly_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByIbzReportly(@PathVariable("ibzreportly_id") Long ibzreportly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据用例库删除附件", tags = {"附件" },  notes = "根据用例库删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{ibzlib_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据用例库批量删除附件", tags = {"附件" },  notes = "根据用例库批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{ibzlib_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByIbzLib(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'IBZ_LIB', #ibzlib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据用例库获取文件库查询", tags = {"附件" } ,notes = "根据用例库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/{ibzlib_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_LIB', #ibzlib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据用例库获取文件库查询", tags = {"附件" } ,notes = "根据用例库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/{ibzlib_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_LIB', #ibzlib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据用例库获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据用例库获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/{ibzlib_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByIbzLib(@PathVariable("ibzlib_id") Long ibzlib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据测试套件删除附件", tags = {"附件" },  notes = "根据测试套件删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据测试套件批量删除附件", tags = {"附件" },  notes = "根据测试套件批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testsuites/{testsuite_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByTestSuite(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取文件库查询", tags = {"附件" } ,notes = "根据测试套件获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取文件库查询", tags = {"附件" } ,notes = "根据测试套件获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TESTSUITE', #testsuite_id, 'READ', 'READ')")
	@ApiOperation(value = "根据测试套件获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据测试套件获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/testsuites/{testsuite_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByTestSuite(@PathVariable("testsuite_id") Long testsuite_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据待办删除附件", tags = {"附件" },  notes = "根据待办删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByTodo(@PathVariable("todo_id") Long todo_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据待办批量删除附件", tags = {"附件" },  notes = "根据待办批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{todo_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByTodo(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取文件库查询", tags = {"附件" } ,notes = "根据待办获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取文件库查询", tags = {"附件" } ,notes = "根据待办获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据待办获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据待办获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/todos/{todo_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByTodo(@PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据周报删除附件", tags = {"附件" },  notes = "根据周报删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/weeklies/{ibzweekly_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据周报批量删除附件", tags = {"附件" },  notes = "根据周报批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/weeklies/{ibzweekly_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByIbzWeekly(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取文件库查询", tags = {"附件" } ,notes = "根据周报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取文件库查询", tags = {"附件" } ,notes = "根据周报获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'IBZ_WEEKLY', #ibzweekly_id, 'READ', 'READ')")
	@ApiOperation(value = "根据周报获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据周报获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/{ibzweekly_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByIbzWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据系统用户文档删除附件", tags = {"附件" },  notes = "根据系统用户文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据系统用户文档批量删除附件", tags = {"附件" },  notes = "根据系统用户文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取文件库查询", tags = {"附件" } ,notes = "根据系统用户文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取文件库查询", tags = {"附件" } ,notes = "根据系统用户文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据系统用户文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据系统用户文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeBySysUserDoc(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据系统用户待办删除附件", tags = {"附件" },  notes = "根据系统用户待办删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据系统用户待办批量删除附件", tags = {"附件" },  notes = "根据系统用户待办批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/todos/{todo_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserTodo(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取文件库查询", tags = {"附件" } ,notes = "根据系统用户待办获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取文件库查询", tags = {"附件" } ,notes = "根据系统用户待办获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_TODO', #todo_id, 'READ', 'READ')")
	@ApiOperation(value = "根据系统用户待办获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据系统用户待办获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/todos/{todo_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeBySysUserTodo(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("todo_id") Long todo_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档库文档删除附件", tags = {"附件" },  notes = "根据文档库文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据文档库文档批量删除附件", tags = {"附件" },  notes = "根据文档库文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByDocLibDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据文档库文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试版本删除附件", tags = {"附件" },  notes = "根据产品测试版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testtasks/{testtask_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试版本批量删除附件", tags = {"附件" },  notes = "根据产品测试版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testtasks/{testtask_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestTask(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试版本获取文件库查询", tags = {"附件" } ,notes = "根据产品测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testtasks/{testtask_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试版本获取文件库查询", tags = {"附件" } ,notes = "根据产品测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testtasks/{testtask_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testtasks/{testtask_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductTestTask(@PathVariable("product_id") Long product_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库删除附件", tags = {"附件" },  notes = "根据产品文档库删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库批量删除附件", tags = {"附件" },  notes = "根据产品文档库批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLib(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取文件库查询", tags = {"附件" } ,notes = "根据产品文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取文件库查询", tags = {"附件" } ,notes = "根据产品文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品文档库获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品版本删除附件", tags = {"附件" },  notes = "根据产品版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品版本批量删除附件", tags = {"附件" },  notes = "根据产品版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductBuild(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取文件库查询", tags = {"附件" } ,notes = "根据产品版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取文件库查询", tags = {"附件" } ,notes = "根据产品版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品产品计划删除附件", tags = {"附件" },  notes = "根据产品产品计划删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品产品计划批量删除附件", tags = {"附件" },  notes = "根据产品产品计划批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlan(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品产品计划获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品产品计划获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品发布删除附件", tags = {"附件" },  notes = "根据产品发布删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{release_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品发布批量删除附件", tags = {"附件" },  notes = "根据产品发布批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{release_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductRelease(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品发布获取文件库查询", tags = {"附件" } ,notes = "根据产品发布获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品发布获取文件库查询", tags = {"附件" } ,notes = "根据产品发布获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品发布获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品发布获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品需求删除附件", tags = {"附件" },  notes = "根据产品需求删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品需求批量删除附件", tags = {"附件" },  notes = "根据产品需求批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取文件库查询", tags = {"附件" } ,notes = "根据产品需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取文件库查询", tags = {"附件" } ,notes = "根据产品需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品需求获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品需求获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'DELETE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目删除附件", tags = {"附件" },  notes = "根据产品项目删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品项目批量删除附件", tags = {"附件" },  notes = "根据产品项目批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProject(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取文件库查询", tags = {"附件" } ,notes = "根据产品项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取文件库查询", tags = {"附件" } ,notes = "根据产品项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库删除附件", tags = {"附件" },  notes = "根据项目文档库删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库批量删除附件", tags = {"附件" },  notes = "根据项目文档库批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLib(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取文件库查询", tags = {"附件" } ,notes = "根据项目文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取文件库查询", tags = {"附件" } ,notes = "根据项目文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目文档库获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目产品计划删除附件", tags = {"附件" },  notes = "根据项目产品计划删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/{productplan_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目产品计划批量删除附件", tags = {"附件" },  notes = "根据项目产品计划批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/{productplan_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProductPlan(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目产品计划获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目产品计划获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目Bug删除附件", tags = {"附件" },  notes = "根据项目Bug删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目Bug批量删除附件", tags = {"附件" },  notes = "根据项目Bug批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBug(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目Bug获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目版本删除附件", tags = {"附件" },  notes = "根据项目版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目版本批量删除附件", tags = {"附件" },  notes = "根据项目版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBuild(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取文件库查询", tags = {"附件" } ,notes = "根据项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取文件库查询", tags = {"附件" } ,notes = "根据项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目任务删除附件", tags = {"附件" },  notes = "根据项目任务删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目任务批量删除附件", tags = {"附件" },  notes = "根据项目任务批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取文件库查询", tags = {"附件" } ,notes = "根据项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取文件库查询", tags = {"附件" } ,notes = "根据项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目任务获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目需求删除附件", tags = {"附件" },  notes = "根据项目需求删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目需求批量删除附件", tags = {"附件" },  notes = "根据项目需求批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectStory(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取文件库查询", tags = {"附件" } ,notes = "根据项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取文件库查询", tags = {"附件" } ,notes = "根据项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目需求获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目需求获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目测试报告删除附件", tags = {"附件" },  notes = "根据项目测试报告删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目测试报告批量删除附件", tags = {"附件" },  notes = "根据项目测试报告批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testreports/{testreport_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestReport(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取文件库查询", tags = {"附件" } ,notes = "根据项目测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取文件库查询", tags = {"附件" } ,notes = "根据项目测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试报告获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testreports/{testreport_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectTestReport(@PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目测试版本删除附件", tags = {"附件" },  notes = "根据项目测试版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目测试版本批量删除附件", tags = {"附件" },  notes = "根据项目测试版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/testtasks/{testtask_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTestTask(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取文件库查询", tags = {"附件" } ,notes = "根据项目测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取文件库查询", tags = {"附件" } ,notes = "根据项目测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目测试版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/testtasks/{testtask_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectTestTask(@PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品Bug删除附件", tags = {"附件" },  notes = "根据产品Bug删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品Bug批量删除附件", tags = {"附件" },  notes = "根据产品Bug批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductBug(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品Bug获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除附件", tags = {"附件" },  notes = "根据产品测试用例删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例批量删除附件", tags = {"附件" },  notes = "根据产品测试用例批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductCase(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试报告删除附件", tags = {"附件" },  notes = "根据产品测试报告删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testreports/{testreport_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试报告批量删除附件", tags = {"附件" },  notes = "根据产品测试报告批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testreports/{testreport_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductTestReport(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取文件库查询", tags = {"附件" } ,notes = "根据产品测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取文件库查询", tags = {"附件" } ,notes = "根据产品测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试报告获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testreports/{testreport_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductTestReport(@PathVariable("product_id") Long product_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库文档删除附件", tags = {"附件" },  notes = "根据产品文档库文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库文档批量删除附件", tags = {"附件" },  notes = "根据产品文档库文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLibDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据产品文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据产品文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品文档库文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库删除附件", tags = {"附件" },  notes = "根据产品项目文档库删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库批量删除附件", tags = {"附件" },  notes = "根据产品项目文档库批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectDocLib(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取文件库查询", tags = {"附件" } ,notes = "根据产品项目文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取文件库查询", tags = {"附件" } ,notes = "根据产品项目文档库获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目文档库获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目产品计划删除附件", tags = {"附件" },  notes = "根据产品项目产品计划删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/productplans/{productplan_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目产品计划批量删除附件", tags = {"附件" },  notes = "根据产品项目产品计划批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/productplans/{productplan_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectProductPlan(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/productplans/{productplan_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/productplans/{productplan_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目产品计划获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目产品计划获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/productplans/{productplan_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目Bug删除附件", tags = {"附件" },  notes = "根据产品项目Bug删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectBug(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目Bug批量删除附件", tags = {"附件" },  notes = "根据产品项目Bug批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/bugs/{bug_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectBug(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/{bug_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectBug(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/{bug_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectBug(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目Bug获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/bugs/{bug_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectBug(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目版本删除附件", tags = {"附件" },  notes = "根据产品项目版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectBuild(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目版本批量删除附件", tags = {"附件" },  notes = "根据产品项目版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectBuild(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目版本获取文件库查询", tags = {"附件" } ,notes = "根据产品项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/builds/{build_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectBuild(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目版本获取文件库查询", tags = {"附件" } ,notes = "根据产品项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/builds/{build_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectBuild(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/builds/{build_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectBuild(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目任务删除附件", tags = {"附件" },  notes = "根据产品项目任务删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/tasks/{task_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目任务批量删除附件", tags = {"附件" },  notes = "根据产品项目任务批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/tasks/{task_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectTask(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目任务获取文件库查询", tags = {"附件" } ,notes = "根据产品项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/tasks/{task_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目任务获取文件库查询", tags = {"附件" } ,notes = "根据产品项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/tasks/{task_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目任务获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/tasks/{task_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目需求删除附件", tags = {"附件" },  notes = "根据产品项目需求删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectStory(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目需求批量删除附件", tags = {"附件" },  notes = "根据产品项目需求批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/stories/{story_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectStory(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目需求获取文件库查询", tags = {"附件" } ,notes = "根据产品项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/{story_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectStory(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目需求获取文件库查询", tags = {"附件" } ,notes = "根据产品项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/{story_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectStory(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目需求获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目需求获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/stories/{story_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectStory(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目测试报告删除附件", tags = {"附件" },  notes = "根据产品项目测试报告删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/testreports/{testreport_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectTestReport(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目测试报告批量删除附件", tags = {"附件" },  notes = "根据产品项目测试报告批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/testreports/{testreport_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectTestReport(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试报告获取文件库查询", tags = {"附件" } ,notes = "根据产品项目测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testreports/{testreport_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectTestReport(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试报告获取文件库查询", tags = {"附件" } ,notes = "根据产品项目测试报告获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testreports/{testreport_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectTestReport(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试报告获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目测试报告获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testreports/{testreport_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectTestReport(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testreport_id") Long testreport_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目测试版本删除附件", tags = {"附件" },  notes = "根据产品项目测试版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/testtasks/{testtask_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectTestTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目测试版本批量删除附件", tags = {"附件" },  notes = "根据产品项目测试版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/testtasks/{testtask_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectTestTask(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试版本获取文件库查询", tags = {"附件" } ,notes = "根据产品项目测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testtasks/{testtask_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectTestTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试版本获取文件库查询", tags = {"附件" } ,notes = "根据产品项目测试版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testtasks/{testtask_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectTestTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目测试版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目测试版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/testtasks/{testtask_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectTestTask(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("testtask_id") Long testtask_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库文档删除附件", tags = {"附件" },  notes = "根据项目文档库文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库文档批量删除附件", tags = {"附件" },  notes = "根据项目文档库文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLibDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据项目文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据项目文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目文档库文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例用例步骤删除附件", tags = {"附件" },  notes = "根据产品测试用例用例步骤删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testcasesteps/{casestep_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductCaseCaseStep(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例用例步骤批量删除附件", tags = {"附件" },  notes = "根据产品测试用例用例步骤批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testcasesteps/{casestep_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductCaseCaseStep(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例用例步骤获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例用例步骤获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testcasesteps/{casestep_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductCaseCaseStep(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例用例步骤获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例用例步骤获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testcasesteps/{casestep_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductCaseCaseStep(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品测试用例用例步骤获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试用例用例步骤获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testcasesteps/{casestep_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductCaseCaseStep(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例测试结果删除附件", tags = {"附件" },  notes = "根据产品测试用例测试结果删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品测试用例测试结果批量删除附件", tags = {"附件" },  notes = "根据产品测试用例测试结果批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductCaseTestResult(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据产品测试用例测试结果获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据产品测试用例测试结果获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "根据产品测试用例测试结果获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}




    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库文档删除附件", tags = {"附件" },  notes = "根据产品项目文档库文档删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProjectDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库文档批量删除附件", tags = {"附件" },  notes = "根据产品项目文档库文档批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectDocLibDoc(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据产品项目文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProjectDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库文档获取文件库查询", tags = {"附件" } ,notes = "根据产品项目文档库文档获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProjectDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_FILE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库文档获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品项目文档库文档获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProjectDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody FileSearchContext context) {
        
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

