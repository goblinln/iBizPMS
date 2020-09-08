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

@Slf4j
@Api(tags = {"附件" })
@RestController("WebApi-file")
@RequestMapping("")
public class FileResource {

    @Autowired
    public IFileService fileService;

    @Autowired
    @Lazy
    public FileMapping fileMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Create-all')")
    @ApiOperation(value = "新建附件", tags = {"附件" },  notes = "新建附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files")
    public ResponseEntity<FileDTO> create(@RequestBody FileDTO filedto) {
        File domain = fileMapping.toDomain(filedto);
		fileService.create(domain);
        FileDTO dto = fileMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Create-all')")
    @ApiOperation(value = "批量新建附件", tags = {"附件" },  notes = "批量新建附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<FileDTO> filedtos) {
        fileService.createBatch(fileMapping.toDomain(filedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Update-all')")
    @ApiOperation(value = "更新附件", tags = {"附件" },  notes = "更新附件")
	@RequestMapping(method = RequestMethod.PUT, value = "/files/{file_id}")
    public ResponseEntity<FileDTO> update(@PathVariable("file_id") Long file_id, @RequestBody FileDTO filedto) {
		File domain  = fileMapping.toDomain(filedto);
        domain .setId(file_id);
		fileService.update(domain );
		FileDTO dto = fileMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Update-all')")
    @ApiOperation(value = "批量更新附件", tags = {"附件" },  notes = "批量更新附件")
	@RequestMapping(method = RequestMethod.PUT, value = "/files/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<FileDTO> filedtos) {
        fileService.updateBatch(fileMapping.toDomain(filedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Remove-all')")
    @ApiOperation(value = "删除附件", tags = {"附件" },  notes = "删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/files/{file_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("file_id") Long file_id) {
         return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Remove-all')")
    @ApiOperation(value = "批量删除附件", tags = {"附件" },  notes = "批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/files/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Get-all')")
    @ApiOperation(value = "获取附件", tags = {"附件" },  notes = "获取附件")
	@RequestMapping(method = RequestMethod.GET, value = "/files/{file_id}")
    public ResponseEntity<FileDTO> get(@PathVariable("file_id") Long file_id) {
        File domain = fileService.get(file_id);
        FileDTO dto = fileMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取附件草稿", tags = {"附件" },  notes = "获取附件草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/files/getdraft")
    public ResponseEntity<FileDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(fileMapping.toDto(fileService.getDraft(new File())));
    }

    @ApiOperation(value = "检查附件", tags = {"附件" },  notes = "检查附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody FileDTO filedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(fileService.checkKey(fileMapping.toDomain(filedto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Save-all')")
    @ApiOperation(value = "保存附件", tags = {"附件" },  notes = "保存附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/save")
    public ResponseEntity<Boolean> save(@RequestBody FileDTO filedto) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.save(fileMapping.toDomain(filedto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-Save-all')")
    @ApiOperation(value = "批量保存附件", tags = {"附件" },  notes = "批量保存附件")
	@RequestMapping(method = RequestMethod.POST, value = "/files/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<FileDTO> filedtos) {
        fileService.saveBatch(fileMapping.toDomain(filedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"附件" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/files/fetchdefault")
	public ResponseEntity<List<FileDTO>> fetchDefault(FileSearchContext context) {
        Page<File> domains = fileService.searchDefault(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"附件" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/files/searchdefault")
	public ResponseEntity<Page<FileDTO>> searchDefault(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(fileMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-searchType-all')")
	@ApiOperation(value = "获取动态(根据类型过滤)", tags = {"附件" } ,notes = "获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.GET , value="/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchType(FileSearchContext context) {
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-File-searchType-all')")
	@ApiOperation(value = "查询动态(根据类型过滤)", tags = {"附件" } ,notes = "查询动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/files/searchtype")
	public ResponseEntity<Page<FileDTO>> searchType(@RequestBody FileSearchContext context) {
        Page<File> domains = fileService.searchType(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(fileMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

