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
import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ReleaseRuntime;

@Slf4j
@Api(tags = {"发布"})
@RestController("StandardAPI-productrelease")
@RequestMapping("")
public class ProductReleaseResource {

    @Autowired
    public IReleaseService releaseService;

    @Autowired
    public ReleaseRuntime releaseRuntime;

    @Autowired
    @Lazy
    public ProductReleaseMapping productreleaseMapping;


    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品建立发布", tags = {"发布" },  notes = "根据产品建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases")
    public ResponseEntity<ProductReleaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
		releaseService.create(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'READ', #productrelease_id, 'READ')")
    @ApiOperation(value = "根据产品获取发布", tags = {"发布" },  notes = "根据产品获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
        Release domain = releaseService.get(productrelease_id);
        if (domain == null || !(product_id.equals(domain.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'DELETE', #productrelease_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除发布", tags = {"发布" },  notes = "根据产品删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
        Release testget = releaseService.get(productrelease_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(productrelease_id));
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品批量删除发布", tags = {"发布" },  notes = "根据产品批量删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        releaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'UPDATE', #productrelease_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新发布", tags = {"发布" },  notes = "根据产品更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release testget = releaseService.get(productrelease_id);
        if (testget == null || !(product_id.equals(testget.getProduct())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
		releaseService.update(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'MANAGE', #productrelease_id, 'ACTIVATE')")
    @ApiOperation(value = "根据产品状态变更（激活）", tags = {"发布" },  notes = "根据产品状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/activate")
    public ResponseEntity<ProductReleaseDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.activate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productreleasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品获取发布草稿", tags = {"发布" },  notes = "根据产品获取发布草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productreleases/getdraft")
    public ResponseEntity<ProductReleaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductReleaseDTO dto) {
        Release domain = productreleaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productreleaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'MANAGE', #productrelease_id, 'LINKBUG')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"发布" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/linkbug")
    public ResponseEntity<ProductReleaseDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productreleasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'READ', #productrelease_id, 'READ')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"发布" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/linkbugbyleftbug")
    public ResponseEntity<ProductReleaseDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkBugbyLeftBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productreleasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'MANAGE', #productrelease_id, 'LINKSTORY')")
    @ApiOperation(value = "根据产品关联需求", tags = {"发布" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/linkstory")
    public ResponseEntity<ProductReleaseDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkStory(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productreleasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'MANAGE', #productrelease_id, 'TERMINATE')")
    @ApiOperation(value = "根据产品状态变更（停止维护）", tags = {"发布" },  notes = "根据产品状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/terminate")
    public ResponseEntity<ProductReleaseDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.terminate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        Map<String, Integer> opprivs = releaseRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        productreleasedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("test('ZT_RELEASE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"发布" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/fetchdefault")
	public ResponseEntity<List<ProductReleaseDTO>> fetchDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Release> domains = releaseService.searchDefault(context) ;
        List<ProductReleaseDTO> list = productreleaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

