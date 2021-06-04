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


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/files/{file_id}/{action}")
    public ResponseEntity<FileDTO> dynamicCall(@PathVariable("file_id") Long file_id , @PathVariable("action") String action , @RequestBody FileDTO filedto) {
        File domain = fileService.dynamicCall(file_id, action, fileMapping.toDomain(filedto));
        filedto = fileMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(filedto);
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
	@ApiOperation(value = "根据项目获取文件库查询", tags = {"附件" } ,notes = "根据项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目获取文件库查询", tags = {"附件" } ,notes = "根据项目获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProject(@PathVariable("project_id") Long project_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据项目删除附件", tags = {"附件" },  notes = "根据项目删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目批量删除附件", tags = {"附件" },  notes = "根据项目批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品版本获取文件库查询", tags = {"附件" } ,notes = "根据产品版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品版本获取文件库查询", tags = {"附件" } ,notes = "根据产品版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/{build_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品版本删除附件", tags = {"附件" },  notes = "根据产品版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductBuild(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品版本批量删除附件", tags = {"附件" },  notes = "根据产品版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductBuild(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品产品计划获取文件库查询", tags = {"附件" } ,notes = "根据产品产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品产品计划获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品产品计划获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productplans/{productplan_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品产品计划删除附件", tags = {"附件" },  notes = "根据产品产品计划删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductProductPlan(@PathVariable("product_id") Long product_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品产品计划批量删除附件", tags = {"附件" },  notes = "根据产品产品计划批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productplans/{productplan_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductProductPlan(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品发布获取文件库查询", tags = {"附件" } ,notes = "根据产品发布获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品发布获取文件库查询", tags = {"附件" } ,notes = "根据产品发布获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品发布获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品发布获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品发布删除附件", tags = {"附件" },  notes = "根据产品发布删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{release_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品发布批量删除附件", tags = {"附件" },  notes = "根据产品发布批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{release_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductRelease(@RequestBody List<Long> ids) {
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
	@ApiOperation(value = "根据项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目产品计划获取文件库查询", tags = {"附件" } ,notes = "根据项目产品计划获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目产品计划获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目产品计划获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/productplans/{productplan_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据项目产品计划删除附件", tags = {"附件" },  notes = "根据项目产品计划删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/{productplan_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectProductPlan(@PathVariable("project_id") Long project_id, @PathVariable("productplan_id") Long productplan_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目产品计划批量删除附件", tags = {"附件" },  notes = "根据项目产品计划批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/productplans/{productplan_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectProductPlan(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目Bug获取文件库查询", tags = {"附件" } ,notes = "根据项目Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目Bug获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/bugs/{bug_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据项目Bug删除附件", tags = {"附件" },  notes = "根据项目Bug删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectBug(@PathVariable("project_id") Long project_id, @PathVariable("bug_id") Long bug_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目Bug批量删除附件", tags = {"附件" },  notes = "根据项目Bug批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/bugs/{bug_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBug(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目版本获取文件库查询", tags = {"附件" } ,notes = "根据项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目版本获取文件库查询", tags = {"附件" } ,notes = "根据项目版本获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目版本获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目版本获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/{build_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据项目版本删除附件", tags = {"附件" },  notes = "根据项目版本删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectBuild(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目版本批量删除附件", tags = {"附件" },  notes = "根据项目版本批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectBuild(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据项目任务获取文件库查询", tags = {"附件" } ,notes = "根据项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目任务获取文件库查询", tags = {"附件" } ,notes = "根据项目任务获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据项目任务获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据项目任务获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/tasks/{task_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据项目任务删除附件", tags = {"附件" },  notes = "根据项目任务删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProjectTask(@PathVariable("project_id") Long project_id, @PathVariable("task_id") Long task_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据项目任务批量删除附件", tags = {"附件" },  notes = "根据项目任务批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/tasks/{task_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProjectTask(@RequestBody List<Long> ids) {
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
	@ApiOperation(value = "根据产品Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品Bug获取文件库查询", tags = {"附件" } ,notes = "根据产品Bug获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品Bug获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品Bug获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/bugs/{bug_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品Bug删除附件", tags = {"附件" },  notes = "根据产品Bug删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductBug(@PathVariable("product_id") Long product_id, @PathVariable("bug_id") Long bug_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品Bug批量删除附件", tags = {"附件" },  notes = "根据产品Bug批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/bugs/{bug_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductBug(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }



    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品测试用例获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品测试用例获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品测试用例获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试用例获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品测试用例删除附件", tags = {"附件" },  notes = "根据产品测试用例删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品测试用例批量删除附件", tags = {"附件" },  notes = "根据产品测试用例批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductCase(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }




    @PreAuthorize("quickTest('ZT_FILE','READ')")
	@ApiOperation(value = "根据产品测试用例测试结果获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchproject")
	public ResponseEntity<List<FileDTO>> fetchProjectByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品测试用例测试结果获取文件库查询", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取文件库查询")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchproduct")
	public ResponseEntity<List<FileDTO>> fetchProductByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
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
	@ApiOperation(value = "根据产品测试用例测试结果获取动态(根据类型过滤)", tags = {"附件" } ,notes = "根据产品测试用例测试结果获取动态(根据类型过滤)")
    @RequestMapping(method= RequestMethod.POST , value="/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/fetchtype")
	public ResponseEntity<List<FileDTO>> fetchTypeByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id,@RequestBody FileSearchContext context) {
        
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
    @ApiOperation(value = "根据产品测试用例测试结果删除附件", tags = {"附件" },  notes = "根据产品测试用例测试结果删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/{file_id}")
    public ResponseEntity<Boolean> removeByProductCaseTestResult(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("testresult_id") Long testresult_id, @PathVariable("file_id") Long file_id) {
		return ResponseEntity.status(HttpStatus.OK).body(fileService.remove(file_id));
    }

    @PreAuthorize("quickTest('ZT_FILE','DELETE')")
    @ApiOperation(value = "根据产品测试用例测试结果批量删除附件", tags = {"附件" },  notes = "根据产品测试用例测试结果批量删除附件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/tests/{product_id}/testcases/{case_id}/testreults/{testresult_id}/files/batch")
    public ResponseEntity<Boolean> removeBatchByProductCaseTestResult(@RequestBody List<Long> ids) {
        fileService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

}

