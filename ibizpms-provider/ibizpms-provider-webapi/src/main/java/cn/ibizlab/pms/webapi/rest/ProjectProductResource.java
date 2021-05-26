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
import cn.ibizlab.pms.core.zentao.domain.ProjectProduct;
import cn.ibizlab.pms.core.zentao.service.IProjectProductService;
import cn.ibizlab.pms.core.zentao.filter.ProjectProductSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectProductRuntime;

@Slf4j
@Api(tags = {"项目产品" })
@RestController("WebApi-projectproduct")
@RequestMapping("")
public class ProjectProductResource {

    @Autowired
    public IProjectProductService projectproductService;

    @Autowired
    public ProjectProductRuntime projectproductRuntime;

    @Autowired
    @Lazy
    public ProjectProductMapping projectproductMapping;

    @PreAuthorize("@ProjectProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目产品", tags = {"项目产品" },  notes = "新建项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projectproducts")
    @Transactional
    public ResponseEntity<ProjectProductDTO> create(@Validated @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
		projectproductService.create(domain);
        if(!projectproductRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        Map<String,Integer> opprivs = projectproductRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @VersionCheck(entity = "projectproduct" , versionfield = "updatedate")
    @PreAuthorize("@ProjectProductRuntime.test(#projectproduct_id,'UPDATE')")
    @ApiOperation(value = "更新项目产品", tags = {"项目产品" },  notes = "更新项目产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectproducts/{projectproduct_id}")
    @Transactional
    public ResponseEntity<ProjectProductDTO> update(@PathVariable("projectproduct_id") String projectproduct_id, @RequestBody ProjectProductDTO projectproductdto) {
		ProjectProduct domain  = projectproductMapping.toDomain(projectproductdto);
        domain.setId(projectproduct_id);
		projectproductService.update(domain );
        if(!projectproductRuntime.test(projectproduct_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectProductDTO dto = projectproductMapping.toDto(domain);
        Map<String,Integer> opprivs = projectproductRuntime.getOPPrivs(projectproduct_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectProductRuntime.test(#projectproduct_id,'DELETE')")
    @ApiOperation(value = "删除项目产品", tags = {"项目产品" },  notes = "删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projectproducts/{projectproduct_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projectproduct_id") String projectproduct_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectproductService.remove(projectproduct_id));
    }


    @PreAuthorize("@ProjectProductRuntime.test(#projectproduct_id,'READ')")
    @ApiOperation(value = "获取项目产品", tags = {"项目产品" },  notes = "获取项目产品")
	@RequestMapping(method = RequestMethod.GET, value = "/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> get(@PathVariable("projectproduct_id") String projectproduct_id) {
        ProjectProduct domain = projectproductService.get(projectproduct_id);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        Map<String,Integer> opprivs = projectproductRuntime.getOPPrivs(projectproduct_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectProductRuntime.test(#projectproduct_id,'CREATE')")
    @ApiOperation(value = "获取项目产品草稿", tags = {"项目产品" },  notes = "获取项目产品草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectproducts/getdraft")
    public ResponseEntity<ProjectProductDTO> getDraft(ProjectProductDTO dto) {
        ProjectProduct domain = projectproductMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(projectproductService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目产品", tags = {"项目产品" },  notes = "检查项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projectproducts/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectProductDTO projectproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectproductService.checkKey(projectproductMapping.toDomain(projectproductdto)));
    }

    @ApiOperation(value = "保存项目产品", tags = {"项目产品" },  notes = "保存项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projectproducts/save")
    public ResponseEntity<ProjectProductDTO> save(@RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        projectproductService.save(domain);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        Map<String,Integer> opprivs = projectproductRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目产品" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectproducts/fetchdefault")
	public ResponseEntity<List<ProjectProductDTO>> fetchdefault(@RequestBody ProjectProductSearchContext context) {
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectProductRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"项目产品" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projectproducts/searchdefault")
	public ResponseEntity<Page<ProjectProductDTO>> searchDefault(@RequestBody ProjectProductSearchContext context) {
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectProductRuntime.quickTest('READ')")
	@ApiOperation(value = "获取关联计划", tags = {"项目产品" } ,notes = "获取关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/projectproducts/fetchrelationplan")
	public ResponseEntity<List<ProjectProductDTO>> fetchrelationplan(@RequestBody ProjectProductSearchContext context) {
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectProductRuntime.quickTest('READ')")
	@ApiOperation(value = "查询关联计划", tags = {"项目产品" } ,notes = "查询关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/projectproducts/searchrelationplan")
	public ResponseEntity<Page<ProjectProductDTO>> searchRelationPlan(@RequestBody ProjectProductSearchContext context) {
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectproducts/{projectproduct_id}/{action}")
    public ResponseEntity<ProjectProductDTO> dynamicCall(@PathVariable("projectproduct_id") String projectproduct_id , @PathVariable("action") String action , @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductService.dynamicCall(projectproduct_id, action, projectproductMapping.toDomain(projectproductdto));
        projectproductdto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立项目产品", tags = {"项目产品" },  notes = "根据产品建立项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projectproducts")
    public ResponseEntity<ProjectProductDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProduct(product_id);
		projectproductService.create(domain);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "projectproduct" , versionfield = "updatedate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新项目产品", tags = {"项目产品" },  notes = "根据产品更新项目产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("projectproduct_id") String projectproduct_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProduct(product_id);
        domain.setId(projectproduct_id);
		projectproductService.update(domain);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除项目产品", tags = {"项目产品" },  notes = "根据产品删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("projectproduct_id") String projectproduct_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectproductService.remove(projectproduct_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取项目产品", tags = {"项目产品" },  notes = "根据产品获取项目产品")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("projectproduct_id") String projectproduct_id) {
        ProjectProduct domain = projectproductService.get(projectproduct_id);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取项目产品草稿", tags = {"项目产品" },  notes = "根据产品获取项目产品草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projectproducts/getdraft")
    public ResponseEntity<ProjectProductDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProjectProductDTO dto) {
        ProjectProduct domain = projectproductMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(projectproductService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品检查项目产品", tags = {"项目产品" },  notes = "根据产品检查项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projectproducts/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductDTO projectproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectproductService.checkKey(projectproductMapping.toDomain(projectproductdto)));
    }

    @ApiOperation(value = "根据产品保存项目产品", tags = {"项目产品" },  notes = "根据产品保存项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projectproducts/save")
    public ResponseEntity<ProjectProductDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProduct(product_id);
        projectproductService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"项目产品" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projectproducts/fetchdefault")
	public ResponseEntity<List<ProjectProductDTO>> fetchProjectProductDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProjectProductSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"项目产品" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projectproducts/searchdefault")
	public ResponseEntity<Page<ProjectProductDTO>> searchProjectProductDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取关联计划", tags = {"项目产品" } ,notes = "根据产品获取关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projectproducts/fetchrelationplan")
	public ResponseEntity<List<ProjectProductDTO>> fetchProjectProductRelationPlanByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProjectProductSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询关联计划", tags = {"项目产品" } ,notes = "根据产品查询关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projectproducts/searchrelationplan")
	public ResponseEntity<Page<ProjectProductDTO>> searchProjectProductRelationPlanByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductSearchContext context) {
        context.setN_product_eq(product_id);
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目建立项目产品", tags = {"项目产品" },  notes = "根据项目建立项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectproducts")
    public ResponseEntity<ProjectProductDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProject(project_id);
		projectproductService.create(domain);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "projectproduct" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新项目产品", tags = {"项目产品" },  notes = "根据项目更新项目产品")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectproduct_id") String projectproduct_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProject(project_id);
        domain.setId(projectproduct_id);
		projectproductService.update(domain);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目删除项目产品", tags = {"项目产品" },  notes = "根据项目删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectproduct_id") String projectproduct_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectproductService.remove(projectproduct_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取项目产品", tags = {"项目产品" },  notes = "根据项目获取项目产品")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectproduct_id") String projectproduct_id) {
        ProjectProduct domain = projectproductService.get(projectproduct_id);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取项目产品草稿", tags = {"项目产品" },  notes = "根据项目获取项目产品草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectproducts/getdraft")
    public ResponseEntity<ProjectProductDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectProductDTO dto) {
        ProjectProduct domain = projectproductMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(projectproductService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目检查项目产品", tags = {"项目产品" },  notes = "根据项目检查项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectproducts/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductDTO projectproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectproductService.checkKey(projectproductMapping.toDomain(projectproductdto)));
    }

    @ApiOperation(value = "根据项目保存项目产品", tags = {"项目产品" },  notes = "根据项目保存项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectproducts/save")
    public ResponseEntity<ProjectProductDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProject(project_id);
        projectproductService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"项目产品" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectproducts/fetchdefault")
	public ResponseEntity<List<ProjectProductDTO>> fetchProjectProductDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectProductSearchContext context) {
        context.setN_project_eq(project_id);
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"项目产品" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectproducts/searchdefault")
	public ResponseEntity<Page<ProjectProductDTO>> searchProjectProductDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductSearchContext context) {
        context.setN_project_eq(project_id);
        Page<ProjectProduct> domains = projectproductService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取关联计划", tags = {"项目产品" } ,notes = "根据项目获取关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectproducts/fetchrelationplan")
	public ResponseEntity<List<ProjectProductDTO>> fetchProjectProductRelationPlanByProject(@PathVariable("project_id") Long project_id,@RequestBody ProjectProductSearchContext context) {
        context.setN_project_eq(project_id);
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
        List<ProjectProductDTO> list = projectproductMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询关联计划", tags = {"项目产品" } ,notes = "根据项目查询关联计划")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectproducts/searchrelationplan")
	public ResponseEntity<Page<ProjectProductDTO>> searchProjectProductRelationPlanByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductSearchContext context) {
        context.setN_project_eq(project_id);
        Page<ProjectProduct> domains = projectproductService.searchRelationPlan(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectproductMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

