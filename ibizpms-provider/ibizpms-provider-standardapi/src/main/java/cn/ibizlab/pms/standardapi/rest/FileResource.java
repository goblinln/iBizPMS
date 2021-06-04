package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.FileRuntime;

@Slf4j
@Api(tags = {"附件" })
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
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchproject(@RequestBody FileSearchContext context) {
        fileRuntime.addAuthorityConditions(context,"READ");
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
    @RequestMapping(method= RequestMethod.POST , value="/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchproduct(@RequestBody FileSearchContext context) {
        fileRuntime.addAuthorityConditions(context,"READ");
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
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/files/{file_id}/{action}")
    public ResponseEntity<FileDTO> dynamicCall(@PathVariable("file_id") Long file_id , @PathVariable("action") String action , @RequestBody FileDTO filedto) {
        File domain = fileService.dynamicCall(file_id, action, fileMapping.toDomain(filedto));
        filedto = fileMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(filedto);
    }

    @PreAuthorize("test('ZT_FILE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除附件", tags = {"附件" },  notes = "根据产品删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品批量删除附件", tags = {"附件" },  notes = "根据产品批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品获取文件库查询", tags = {"附件" } ,notes = "根据产品获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品获取文件库查询", tags = {"附件" } ,notes = "根据产品获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProduct(@PathVariable("product_id") Long product_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_FILE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据产品需求删除附件", tags = {"附件" },  notes = "根据产品需求删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品需求批量删除附件", tags = {"附件" },  notes = "根据产品需求批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/stories/{story_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductStory(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品需求获取文件库查询", tags = {"附件" } ,notes = "根据产品需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品需求获取文件库查询", tags = {"附件" } ,notes = "根据产品需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品需求获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品需求获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/stories/{story_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductStory(@PathVariable("product_id") Long product_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_FILE', #file_id, 'DELETE')")
    @ApiOperation(value = "根据项目需求删除附件", tags = {"附件" },  notes = "根据项目需求删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目需求批量删除附件", tags = {"附件" },  notes = "根据项目需求批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/stories/{story_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectStory(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目需求获取文件库查询", tags = {"附件" } ,notes = "根据项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目需求获取文件库查询", tags = {"附件" } ,notes = "根据项目需求获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchProductDocLibFile(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目需求获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目需求获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/stories/{story_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectStory(@PathVariable("project_id") Long project_id, @PathVariable("story_id") Long story_id,@RequestBody FileSearchContext context) {
        
        fileRuntime.addAuthorityConditions(context,"READ");
        Page<File> domains = fileService.searchType(context) ;
        List<FileDTO> list = fileMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

