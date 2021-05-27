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
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;

@Slf4j
@Api(tags = {"需求" })
@RestController("StandardAPI-productstory")
@RequestMapping("")
public class ProductStoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    public ProductStoryMapping productstoryMapping;

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品变更需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/change")
    public ResponseEntity<ProductStoryDTO> changeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.change(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立需求", tags = {"需求" },  notes = "根据产品建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories")
    public ResponseEntity<ProductStoryDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
		storyService.create(domain);
        ProductStoryDTO dto = productstoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "story" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新需求", tags = {"需求" },  notes = "根据产品更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productstories/{productstory_id}")
    public ResponseEntity<ProductStoryDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
		storyService.update(domain);
        ProductStoryDTO dto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取需求", tags = {"需求" },  notes = "根据产品获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productstories/{productstory_id}")
    public ResponseEntity<ProductStoryDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id) {
        Story domain = storyService.get(productstory_id);
        ProductStoryDTO dto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品需求收藏需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/storyfavorites")
    public ResponseEntity<ProductStoryDTO> storyFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.storyFavorites(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除需求", tags = {"需求" },  notes = "根据产品删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productstories/{productstory_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id) {
		return ResponseEntity.status(HttpStatus.OK).body(storyService.remove(productstory_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品指派需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/assignto")
    public ResponseEntity<ProductStoryDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.assignTo(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品取消收藏需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/storynfavorites")
    public ResponseEntity<ProductStoryDTO> storyNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.storyNFavorites(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"需求" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productstories/fetchdefault")
	public ResponseEntity<List<ProductStoryDTO>> fetchProductStoryDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
        List<ProductStoryDTO> list = productstoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"需求" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productstories/searchdefault")
	public ResponseEntity<Page<ProductStoryDTO>> searchProductStoryDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品评审需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/review")
    public ResponseEntity<ProductStoryDTO> reviewByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.review(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取数据查询", tags = {"需求" } ,notes = "根据产品获取数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productstories/fetchparentdefault")
	public ResponseEntity<List<ProductStoryDTO>> fetchProductStoryParentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
        List<ProductStoryDTO> list = productstoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询数据查询", tags = {"需求" } ,notes = "根据产品查询数据查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productstories/searchparentdefault")
	public ResponseEntity<Page<ProductStoryDTO>> searchProductStoryParentDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody StorySearchContext context) {
        context.setN_product_eq(product_id);
        Page<Story> domains = storyService.searchParentDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productstoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品激活需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/activate")
    public ResponseEntity<ProductStoryDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.activate(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取需求草稿", tags = {"需求" },  notes = "根据产品获取需求草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productstories/getdraft")
    public ResponseEntity<ProductStoryDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductStoryDTO dto) {
        Story domain = productstoryMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productstoryMapping.toDto(storyService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品关闭需求", tags = {"需求" },  notes = "根据产品需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productstories/{productstory_id}/close")
    public ResponseEntity<ProductStoryDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productstory_id") Long productstory_id, @RequestBody ProductStoryDTO productstorydto) {
        Story domain = productstoryMapping.toDomain(productstorydto);
        domain.setProduct(product_id);
        domain.setId(productstory_id);
        domain = storyService.close(domain) ;
        productstorydto = productstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productstorydto);
    }

}

