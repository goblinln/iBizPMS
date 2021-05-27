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
@RestController("StandardAPI-productreleasestory")
@RequestMapping("")
public class ProductReleaseStoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    public ProductReleaseStoryMapping productreleasestoryMapping;

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布建立需求", tags = {"需求" },  notes = "根据产品发布建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{release_id}/productreleasestories")
    public ResponseEntity<ProductReleaseStoryDTO> createByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody ProductReleaseStoryDTO productreleasestorydto) {
        Story domain = productreleasestoryMapping.toDomain(productreleasestorydto);
        
		storyService.create(domain);
        ProductReleaseStoryDTO dto = productreleasestoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品发布批量建立需求", tags = {"需求" },  notes = "根据产品发布批量建立需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productreleases/{release_id}/productreleasestories/batch")
    public ResponseEntity<Boolean> createBatchByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody List<ProductReleaseStoryDTO> productreleasestorydtos) {
        List<Story> domainlist=productreleasestoryMapping.toDomain(productreleasestorydtos);
        for(Story domain:domainlist){
            
        }
        storyService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布获取DEFAULT", tags = {"需求" } ,notes = "根据产品发布获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/productreleasestories/fetchdefault")
	public ResponseEntity<List<ProductReleaseStoryDTO>> fetchProductReleaseStoryDefaultByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
        List<ProductReleaseStoryDTO> list = productreleasestoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品发布查询DEFAULT", tags = {"需求" } ,notes = "根据产品发布查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productreleases/{release_id}/productreleasestories/searchdefault")
	public ResponseEntity<Page<ProductReleaseStoryDTO>> searchProductReleaseStoryDefaultByProductRelease(@PathVariable("product_id") Long product_id, @PathVariable("release_id") Long release_id, @RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productreleasestoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

