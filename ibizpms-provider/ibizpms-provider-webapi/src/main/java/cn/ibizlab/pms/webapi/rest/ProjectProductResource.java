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


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
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


    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除项目产品", tags = {"项目产品" },  notes = "根据产品删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("projectproduct_id") String projectproduct_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectproductService.remove(projectproduct_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除项目产品", tags = {"项目产品" },  notes = "根据产品批量删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projectproducts/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<String> ids) {
        projectproductService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取项目产品", tags = {"项目产品" },  notes = "根据产品获取项目产品")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("projectproduct_id") String projectproduct_id) {
        ProjectProduct domain = projectproductService.get(projectproduct_id);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取项目产品草稿", tags = {"项目产品" },  notes = "根据产品获取项目产品草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projectproducts/getdraft")
    public ResponseEntity<ProjectProductDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProjectProductDTO dto) {
        ProjectProduct domain = projectproductMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(projectproductService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品检查项目产品", tags = {"项目产品" },  notes = "根据产品检查项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projectproducts/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductDTO projectproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectproductService.checkKey(projectproductMapping.toDomain(projectproductdto)));
    }

    @PreAuthorize("@ProjectProductRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品保存项目产品", tags = {"项目产品" },  notes = "根据产品保存项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projectproducts/save")
    public ResponseEntity<ProjectProductDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProduct(product_id);
        projectproductService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
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


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目删除项目产品", tags = {"项目产品" },  notes = "根据项目删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectproduct_id") String projectproduct_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectproductService.remove(projectproduct_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目批量删除项目产品", tags = {"项目产品" },  notes = "根据项目批量删除项目产品")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectproducts/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<String> ids) {
        projectproductService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取项目产品", tags = {"项目产品" },  notes = "根据项目获取项目产品")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectproducts/{projectproduct_id}")
    public ResponseEntity<ProjectProductDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectproduct_id") String projectproduct_id) {
        ProjectProduct domain = projectproductService.get(projectproduct_id);
        ProjectProductDTO dto = projectproductMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目获取项目产品草稿", tags = {"项目产品" },  notes = "根据项目获取项目产品草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectproducts/getdraft")
    public ResponseEntity<ProjectProductDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectProductDTO dto) {
        ProjectProduct domain = projectproductMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(projectproductService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'UPDATE')")
    @ApiOperation(value = "根据项目检查项目产品", tags = {"项目产品" },  notes = "根据项目检查项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectproducts/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductDTO projectproductdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectproductService.checkKey(projectproductMapping.toDomain(projectproductdto)));
    }

    @PreAuthorize("@ProjectProductRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存项目产品", tags = {"项目产品" },  notes = "根据项目保存项目产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectproducts/save")
    public ResponseEntity<ProjectProductDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectProductDTO projectproductdto) {
        ProjectProduct domain = projectproductMapping.toDomain(projectproductdto);
        domain.setProject(project_id);
        projectproductService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectproductMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
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
}

