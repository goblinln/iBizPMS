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
import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.service.IProductModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProductModuleRuntime;

@Slf4j
@Api(tags = {"需求模块" })
@RestController("WebApi-productmodule")
@RequestMapping("")
public class ProductModuleResource {

    @Autowired
    public IProductModuleService productmoduleService;

    @Autowired
    public ProductModuleRuntime productmoduleRuntime;

    @Autowired
    @Lazy
    public ProductModuleMapping productmoduleMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productmodules/{productmodule_id}/{action}")
    public ResponseEntity<ProductModuleDTO> dynamicCall(@PathVariable("productmodule_id") Long productmodule_id , @PathVariable("action") String action , @RequestBody ProductModuleDTO productmoduledto) {
        ProductModule domain = productmoduleService.dynamicCall(productmodule_id, action, productmoduleMapping.toDomain(productmoduledto));
        productmoduledto = productmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productmoduledto);
    }
}

