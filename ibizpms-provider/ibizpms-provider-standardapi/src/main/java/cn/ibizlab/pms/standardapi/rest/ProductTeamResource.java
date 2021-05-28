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
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.PRODUCTTEAMRuntime;

@Slf4j
@Api(tags = {"产品团队" })
@RestController("StandardAPI-productteam")
@RequestMapping("")
public class ProductTeamResource {

    @Autowired
    public IPRODUCTTEAMService productteamService;

    @Autowired
    public PRODUCTTEAMRuntime productteamRuntime;

    @Autowired
    @Lazy
    public ProductTeamMapping productteamMapping;


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取指定团队部门", tags = {"产品团队" } ,notes = "根据产品获取指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchspecifyteam")
	public ResponseEntity<List<ProductTeamDTO>> fetchProductTeamSpecifyTeamByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
        List<ProductTeamDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品获取产品团队草稿", tags = {"产品团队" },  notes = "根据产品获取产品团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/getdraft")
    public ResponseEntity<ProductTeamDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductTeamDTO dto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(productteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品建立产品团队", tags = {"产品团队" },  notes = "根据产品建立产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams")
    public ResponseEntity<ProductTeamDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductTeamDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
		productteamService.create(domain);
        ProductTeamDTO dto = productteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
	@ApiOperation(value = "根据产品获取产品团队成员信息", tags = {"产品团队" } ,notes = "根据产品获取产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchproductteaminfo")
	public ResponseEntity<List<ProductTeamDTO>> fetchProductTeamProductTeamInfoByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
        List<ProductTeamDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.test(#product_id, 'READ')")
    @ApiOperation(value = "根据产品获取产品团队", tags = {"产品团队" },  notes = "根据产品获取产品团队")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<ProductTeamDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
        PRODUCTTEAM domain = productteamService.get(productteam_id);
        ProductTeamDTO dto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id, 'CREATE')")
    @ApiOperation(value = "根据产品批量保存产品团队", tags = {"产品团队" },  notes = "根据产品批量保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<ProductTeamDTO> productteamdtos) {
        List<PRODUCTTEAM> domainlist=productteamMapping.toDomain(productteamdtos);
        for(PRODUCTTEAM domain:domainlist){
             domain.setRoot(product_id);
        }
        productteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除产品团队", tags = {"产品团队" },  notes = "根据产品删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productteamService.remove(productteam_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'DELETE')")
    @ApiOperation(value = "根据产品批量删除产品团队", tags = {"产品团队" },  notes = "根据产品批量删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productteams/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        productteamService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新产品团队", tags = {"产品团队" },  notes = "根据产品更新产品团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<ProductTeamDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id, @RequestBody ProductTeamDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
        domain.setId(productteam_id);
		productteamService.update(domain);
        ProductTeamDTO dto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

