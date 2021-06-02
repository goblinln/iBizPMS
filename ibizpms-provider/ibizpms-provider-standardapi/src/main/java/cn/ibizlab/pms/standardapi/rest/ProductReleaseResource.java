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
import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ReleaseRuntime;

@Slf4j
@Api(tags = {"发布" })
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


    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品状态变更（激活）", tags = {"发布" },  notes = "根据产品状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/activate")
    public ResponseEntity<ProductReleaseDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.activate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品关联需求", tags = {"发布" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/linkstory")
    public ResponseEntity<ProductReleaseDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkStory(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除发布", tags = {"发布" },  notes = "根据产品删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
		return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(productrelease_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除发布", tags = {"发布" },  notes = "根据产品批量删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productreleases/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        releaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新发布", tags = {"发布" },  notes = "根据产品更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
		releaseService.update(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取发布草稿", tags = {"发布" },  notes = "根据产品获取发布草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productreleases/getdraft")
    public ResponseEntity<ProductReleaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductReleaseDTO dto) {
        Release domain = productreleaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productreleaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取发布", tags = {"发布" },  notes = "根据产品获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
        Release domain = releaseService.get(productrelease_id);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据产品状态变更（停止维护）", tags = {"发布" },  notes = "根据产品状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/terminate")
    public ResponseEntity<ProductReleaseDTO> terminateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.terminate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
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
    @PreAuthorize("@ReleaseRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解除关联需求", tags = {"发布" },  notes = "根据产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/unlinkstory")
    public ResponseEntity<ProductReleaseDTO> unlinkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.unlinkStory(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立发布", tags = {"发布" },  notes = "根据产品建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases")
    public ResponseEntity<ProductReleaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
		releaseService.create(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"发布" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/linkbugbyleftbug")
    public ResponseEntity<ProductReleaseDTO> linkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkBugbyLeftBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"发布" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{productrelease_id}/unlinkbug")
    public ResponseEntity<ProductReleaseDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.unlinkBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }



    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品状态变更（激活）", tags = {"发布" },  notes = "根据系统用户产品状态变更（激活）")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/activate")
    public ResponseEntity<ProductReleaseDTO> activateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.activate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品关联需求", tags = {"发布" },  notes = "根据系统用户产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/linkstory")
    public ResponseEntity<ProductReleaseDTO> linkStoryBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkStory(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品删除发布", tags = {"发布" },  notes = "根据系统用户产品删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<Boolean> removeBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
		return ResponseEntity.status(HttpStatus.OK).body(releaseService.remove(productrelease_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据系统用户产品批量删除发布", tags = {"发布" },  notes = "根据系统用户产品批量删除发布")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/batch")
    public ResponseEntity<Boolean> removeBatchBySysUserProduct(@RequestBody List<Long> ids) {
        releaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据系统用户产品更新发布", tags = {"发布" },  notes = "根据系统用户产品更新发布")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> updateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
		releaseService.update(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品获取发布草稿", tags = {"发布" },  notes = "根据系统用户产品获取发布草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/getdraft")
    public ResponseEntity<ProductReleaseDTO> getDraftBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, ProductReleaseDTO dto) {
        Release domain = productreleaseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productreleaseMapping.toDto(releaseService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品获取发布", tags = {"发布" },  notes = "根据系统用户产品获取发布")
	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}")
    public ResponseEntity<ProductReleaseDTO> getBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id) {
        Release domain = releaseService.get(productrelease_id);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'MANAGE')")
    @ApiOperation(value = "根据系统用户产品状态变更（停止维护）", tags = {"发布" },  notes = "根据系统用户产品状态变更（停止维护）")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/terminate")
    public ResponseEntity<ProductReleaseDTO> terminateBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.terminate(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据系统用户产品获取DEFAULT", tags = {"发布" } ,notes = "根据系统用户产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/accounts/{sysuser_id}/products/{product_id}/productreleases/fetchdefault")
	public ResponseEntity<List<ProductReleaseDTO>> fetchDefaultBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id,@RequestBody ReleaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Release> domains = releaseService.searchDefault(context) ;
        List<ProductReleaseDTO> list = productreleaseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ReleaseRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据系统用户产品解除关联需求", tags = {"发布" },  notes = "根据系统用户产品解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/unlinkstory")
    public ResponseEntity<ProductReleaseDTO> unlinkStoryBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.unlinkStory(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据系统用户产品建立发布", tags = {"发布" },  notes = "根据系统用户产品建立发布")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases")
    public ResponseEntity<ProductReleaseDTO> createBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
		releaseService.create(domain);
        ProductReleaseDTO dto = productreleaseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品关联Bug（遗留Bug）", tags = {"发布" },  notes = "根据系统用户产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/linkbugbyleftbug")
    public ResponseEntity<ProductReleaseDTO> linkBugbyLeftBugBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.linkBugbyLeftBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据系统用户产品解除关联Bug", tags = {"发布" },  notes = "根据系统用户产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/accounts/{sysuser_id}/products/{product_id}/productreleases/{productrelease_id}/unlinkbug")
    public ResponseEntity<ProductReleaseDTO> unlinkBugBySysUserProduct(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("product_id") Long product_id, @PathVariable("productrelease_id") Long productrelease_id, @RequestBody ProductReleaseDTO productreleasedto) {
        Release domain = productreleaseMapping.toDomain(productreleasedto);
        domain.setProduct(product_id);
        domain.setId(productrelease_id);
        domain = releaseService.unlinkBug(domain) ;
        productreleasedto = productreleaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productreleasedto);
    }

}

