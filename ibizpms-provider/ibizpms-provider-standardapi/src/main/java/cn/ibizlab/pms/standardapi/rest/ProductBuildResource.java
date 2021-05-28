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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BuildRuntime;

@Slf4j
@Api(tags = {"版本" })
@RestController("StandardAPI-productbuild")
@RequestMapping("")
public class ProductBuildResource {

    @Autowired
    public IBuildService buildService;

    @Autowired
    public BuildRuntime buildRuntime;

    @Autowired
    @Lazy
    public ProductBuildMapping productbuildMapping;


    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"版本" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productbuilds/fetchdefault")
	public ResponseEntity<List<ProductBuildDTO>> fetchProductBuildDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BuildSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<ProductBuildDTO> list = productbuildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

