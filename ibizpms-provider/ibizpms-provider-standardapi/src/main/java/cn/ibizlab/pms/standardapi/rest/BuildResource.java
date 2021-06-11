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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BuildRuntime;

@Slf4j
@Api(tags = {"版本"})
@RestController("StandardAPI-build")
@RequestMapping("")
public class BuildResource {

    @Autowired
    public IBuildService buildService;

    @Autowired
    public BuildRuntime buildRuntime;

    @Autowired
    @Lazy
    public BuildMapping buildMapping;


    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品建立版本", tags = {"版本" },  notes = "根据产品建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds")
    public ResponseEntity<BuildDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', #build_id, 'READ')")
    @ApiOperation(value = "根据产品获取版本", tags = {"版本" },  notes = "根据产品获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        if (domain == null || domain.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #build_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除版本", tags = {"版本" },  notes = "根据产品删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除版本", tags = {"版本" },  notes = "根据产品批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/builds/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', #build_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新版本", tags = {"版本" },  notes = "根据产品更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProduct() != product_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品获取版本草稿", tags = {"版本" },  notes = "根据产品获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"版本" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"版本" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProduct(product_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取产品版本", tags = {"版本" } ,notes = "根据产品获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/builds/fetchproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchProductBuildByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目建立版本", tags = {"版本" },  notes = "根据项目建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds")
    public ResponseEntity<BuildDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', #build_id, 'READ')")
    @ApiOperation(value = "根据项目获取版本", tags = {"版本" },  notes = "根据项目获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        if (domain == null || domain.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除版本", tags = {"版本" },  notes = "根据项目删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除版本", tags = {"版本" },  notes = "根据项目批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/builds/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新版本", tags = {"版本" },  notes = "根据项目更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目获取版本草稿", tags = {"版本" },  notes = "根据项目获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"版本" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据项目关联需求", tags = {"版本" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取产品版本", tags = {"版本" } ,notes = "根据项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/builds/fetchproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchProductBuildByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目建立版本", tags = {"版本" },  notes = "根据产品项目建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/builds")
    public ResponseEntity<BuildDTO> createByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
		buildService.create(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', #build_id, 'READ')")
    @ApiOperation(value = "根据产品项目获取版本", tags = {"版本" },  notes = "根据产品项目获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> getByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build domain = buildService.get(build_id);
        if (domain == null || domain.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目删除版本", tags = {"版本" },  notes = "根据产品项目删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<Boolean> removeByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(build_id));
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品项目批量删除版本", tags = {"版本" },  notes = "根据产品项目批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/builds/batch")
    public ResponseEntity<Boolean> removeBatchByProductProject(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', #build_id, 'UPDATE')")
    @ApiOperation(value = "根据产品项目更新版本", tags = {"版本" },  notes = "根据产品项目更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}")
    public ResponseEntity<BuildDTO> updateByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build testget = buildService.get(build_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
		buildService.update(domain);
        BuildDTO dto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'BUILDMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目获取版本草稿", tags = {"版本" },  notes = "根据产品项目获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/builds/getdraft")
    public ResponseEntity<BuildDTO> getDraftByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, BuildDTO dto) {
        Build domain = buildMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(buildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品项目关联Bug", tags = {"版本" },  notes = "根据产品项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}/linkbug")
    public ResponseEntity<BuildDTO> linkBugByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkBug(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("quickTest('ZT_BUILD', 'DENY')")
    @ApiOperation(value = "根据产品项目关联需求", tags = {"版本" },  notes = "根据产品项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/builds/{build_id}/linkstory")
    public ResponseEntity<BuildDTO> linkStoryByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("build_id") Long build_id, @RequestBody BuildDTO builddto) {
        Build domain = buildMapping.toDomain(builddto);
        domain.setProject(project_id);
        domain.setId(build_id);
        domain = buildService.linkStory(domain) ;
        builddto = buildMapping.toDto(domain);
        Map<String, Integer> opprivs = buildRuntime.getOPPrivs(domain.getId());    
        builddto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(builddto);
    }

    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取DEFAULT", tags = {"版本" } ,notes = "根据产品项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/builds/fetchdefault")
	public ResponseEntity<List<BuildDTO>> fetchDefaultByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BUILD', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目获取产品版本", tags = {"版本" } ,notes = "根据产品项目获取产品版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/builds/fetchproductbuild")
	public ResponseEntity<List<BuildDTO>> fetchProductBuildByProductProject(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchProductBuildDS(context) ;
        List<BuildDTO> list = buildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

