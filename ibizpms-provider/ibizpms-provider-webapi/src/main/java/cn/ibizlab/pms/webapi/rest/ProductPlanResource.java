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
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProductPlanRuntime;

@Slf4j
@Api(tags = {"产品计划" })
@RestController("WebApi-productplan")
@RequestMapping("")
public class ProductPlanResource {

    @Autowired
    public IProductPlanService productplanService;

    @Autowired
    public ProductPlanRuntime productplanRuntime;

    @Autowired
    @Lazy
    public ProductPlanMapping productplanMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productplans/{productplan_id}/{action}")
    public ResponseEntity<ProductPlanDTO> dynamicCall(@PathVariable("productplan_id") Long productplan_id , @PathVariable("action") String action , @RequestBody ProductPlanDTO productplandto) {
        ProductPlan domain = productplanService.dynamicCall(productplan_id, action, productplanMapping.toDomain(productplandto));
        productplandto = productplanMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productplandto);
    }
}

