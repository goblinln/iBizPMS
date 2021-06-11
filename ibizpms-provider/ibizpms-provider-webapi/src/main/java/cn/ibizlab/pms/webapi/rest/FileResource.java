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
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.FileRuntime;

@Slf4j
@Api(tags = {"附件"})
@RestController("WebApi-file")
@RequestMapping("")
public class FileResource {

    @Autowired
    public IFileService fileService;

    @Autowired
    public FileRuntime fileRuntime;

    @Autowired
    @Lazy
    public FileMapping fileMapping;

    @PreAuthorize("quickTest('ZT_FILE', 'CREATE')")
    @ApiOperation(value = "新建附件", tags = {"附件" },  notes = "新建附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files")
    @Transactional
    public ResponseEntity<FileDTO> create(@Validated @RequestBody FileDTO filedto) {
        File domain = fileMapping.toDomain(filedto);
		fileService.create(domain);
        if(!fileRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        FileDTO dto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_FILE', #file_id, 'READ')")
    @ApiOperation(value = "获取附件", tags = {"附件" },  notes = "获取附件")
	@RequestMapping(method = RequestMethod.GET, value = "/files/{file_id}")
    public ResponseEntity<FileDTO> get(@PathVariable("file_id") Long file_id) {
        File domain = fileService.get(file_id);
        FileDTO dto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(file_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

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

    @PreAuthorize("test('ZT_FILE', #file_id, 'UPDATE')")
    @ApiOperation(value = "更新附件", tags = {"附件" },  notes = "更新附件")
	@RequestMapping(method = RequestMethod.PUT, value = "/files/{file_id}")
    @Transactional
    public ResponseEntity<FileDTO> update(@PathVariable("file_id") Long file_id, @RequestBody FileDTO filedto) {
		File domain  = fileMapping.toDomain(filedto);
        domain.setId(file_id);
		fileService.update(domain );
        if(!fileRuntime.test(file_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		FileDTO dto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(file_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_FILE', 'CREATE')")
    @ApiOperation(value = "检查附件", tags = {"附件" },  notes = "检查附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody FileDTO filedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(fileService.checkKey(fileMapping.toDomain(filedto)));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'CREATE')")
    @ApiOperation(value = "获取附件草稿", tags = {"附件" },  notes = "获取附件草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/files/getdraft")
    public ResponseEntity<FileDTO> getDraft(FileDTO dto) {
        File domain = fileMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(fileMapping.toDto(fileService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_FILE', 'DENY')")
    @ApiOperation(value = "保存附件", tags = {"附件" },  notes = "保存附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/save")
    public ResponseEntity<FileDTO> save(@RequestBody FileDTO filedto) {
        File domain = fileMapping.toDomain(filedto);
        fileService.save(domain);
        FileDTO dto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_FILE', #file_id, 'UPDATE')")
    @ApiOperation(value = "更新文件", tags = {"附件" },  notes = "更新文件")
	@RequestMapping(method = RequestMethod.PUT, value = "/files/{file_id}/updateobjectid")
    public ResponseEntity<FileDTO> updateObjectID(@PathVariable("file_id") Long file_id, @RequestBody FileDTO filedto) {
        File domain = fileMapping.toDomain(filedto);
        domain.setId(file_id);
        domain = fileService.updateObjectID(domain);
        filedto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(domain.getId());
        filedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(filedto);
    }


    @PreAuthorize("test('ZT_FILE', #file_id, 'UPDATE')")
    @ApiOperation(value = "保存附件", tags = {"附件" },  notes = "保存附件")
	@RequestMapping(method = RequestMethod.PUT, value = "/files/{file_id}/updateobjectidforpmsee")
    public ResponseEntity<FileDTO> updateObjectIDForPmsEe(@PathVariable("file_id") Long file_id, @RequestBody FileDTO filedto) {
        File domain = fileMapping.toDomain(filedto);
        domain.setId(file_id);
        domain = fileService.updateObjectIDForPmsEe(domain);
        filedto = fileMapping.toDto(domain);
        Map<String, Integer> opprivs = fileRuntime.getOPPrivs(domain.getId());
        filedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(filedto);
    }


    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"附件" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchdefault")
	public ResponseEntity<List<FileDTO>> fetchdefault(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchDefault(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取文件库查询", tags = {"附件" } ,notes = "获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchdoclibfile")
	public ResponseEntity<List<FileDTO>> fetchdoclibfile(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取文件库查询", tags = {"附件" } ,notes = "获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchproductdoclibfile")
	public ResponseEntity<List<FileDTO>> fetchproductdoclibfile(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
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
    @PreAuthorize("quickTest('ZT_FILE', 'READ')")
	@ApiOperation(value = "获取查询附件", tags = {"附件" } ,notes = "获取查询附件")
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchtypenotbysrfparentkey")
	public ResponseEntity<List<FileDTO>> fetchtypenotbysrfparentkey(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchTypeNotBySrfparentkey(context) ;
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
}

