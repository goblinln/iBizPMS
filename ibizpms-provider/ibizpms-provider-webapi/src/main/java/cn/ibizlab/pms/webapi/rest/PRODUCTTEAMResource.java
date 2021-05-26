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
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.PRODUCTTEAMRuntime;

@Slf4j
@Api(tags = {"产品团队" })
@RestController("WebApi-productteam")
@RequestMapping("")
public class PRODUCTTEAMResource {

    @Autowired
    public IPRODUCTTEAMService productteamService;

    @Autowired
    public PRODUCTTEAMRuntime productteamRuntime;

    @Autowired
    @Lazy
    public PRODUCTTEAMMapping productteamMapping;

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('CREATE')")
    @ApiOperation(value = "保存产品团队", tags = {"产品团队" },  notes = "保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams/save")
    public ResponseEntity<PRODUCTTEAMDTO> save(@RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        productteamService.save(domain);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量保存产品团队", tags = {"产品团队" },  notes = "批量保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<PRODUCTTEAMDTO> productteamdtos) {
        productteamService.saveBatch(productteamMapping.toDomain(productteamdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@PRODUCTTEAMRuntime.test(#productteam_id,'CREATE')")
    @ApiOperation(value = "获取产品团队草稿", tags = {"产品团队" },  notes = "获取产品团队草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/productteams/getdraft")
    public ResponseEntity<PRODUCTTEAMDTO> getDraft(PRODUCTTEAMDTO dto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(productteamService.getDraft(domain)));
    }

    @PreAuthorize("@PRODUCTTEAMRuntime.test(#productteam_id,'DELETE')")
    @ApiOperation(value = "删除产品团队", tags = {"产品团队" },  notes = "删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/productteams/{productteam_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("productteam_id") Long productteam_id) {
         return ResponseEntity.status(HttpStatus.OK).body(productteamService.remove(productteam_id));
    }


    @PreAuthorize("@PRODUCTTEAMRuntime.test(#productteam_id,'READ')")
    @ApiOperation(value = "获取产品团队", tags = {"产品团队" },  notes = "获取产品团队")
	@RequestMapping(method = RequestMethod.GET, value = "/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> get(@PathVariable("productteam_id") Long productteam_id) {
        PRODUCTTEAM domain = productteamService.get(productteam_id);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(productteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品团队", tags = {"产品团队" },  notes = "新建产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/productteams")
    @Transactional
    public ResponseEntity<PRODUCTTEAMDTO> create(@Validated @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
		productteamService.create(domain);
        if(!productteamRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('READ')")
	@ApiOperation(value = "获取指定团队部门", tags = {"产品团队" } ,notes = "获取指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchspecifyteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchspecifyteam(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('READ')")
	@ApiOperation(value = "查询指定团队部门", tags = {"产品团队" } ,notes = "查询指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/searchspecifyteam")
	public ResponseEntity<Page<PRODUCTTEAMDTO>> searchSpecifyTeam(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品团队成员信息", tags = {"产品团队" } ,notes = "获取产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/fetchproductteaminfo")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchproductteaminfo(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@PRODUCTTEAMRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品团队成员信息", tags = {"产品团队" } ,notes = "查询产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/productteams/searchproductteaminfo")
	public ResponseEntity<Page<PRODUCTTEAMDTO>> searchProductTeamInfo(@RequestBody PRODUCTTEAMSearchContext context) {
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@PRODUCTTEAMRuntime.test(#productteam_id,'UPDATE')")
    @ApiOperation(value = "更新产品团队", tags = {"产品团队" },  notes = "更新产品团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/productteams/{productteam_id}")
    @Transactional
    public ResponseEntity<PRODUCTTEAMDTO> update(@PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
		PRODUCTTEAM domain  = productteamMapping.toDomain(productteamdto);
        domain.setId(productteam_id);
		productteamService.update(domain );
        if(!productteamRuntime.test(productteam_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        Map<String,Integer> opprivs = productteamRuntime.getOPPrivs(productteam_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productteams/{productteam_id}/{action}")
    public ResponseEntity<PRODUCTTEAMDTO> dynamicCall(@PathVariable("productteam_id") Long productteam_id , @PathVariable("action") String action , @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamService.dynamicCall(productteam_id, action, productteamMapping.toDomain(productteamdto));
        productteamdto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productteamdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存产品团队", tags = {"产品团队" },  notes = "根据产品保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/save")
    public ResponseEntity<PRODUCTTEAMDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
        productteamService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存产品团队", tags = {"产品团队" },  notes = "根据产品批量保存产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<PRODUCTTEAMDTO> productteamdtos) {
        List<PRODUCTTEAM> domainlist=productteamMapping.toDomain(productteamdtos);
        for(PRODUCTTEAM domain:domainlist){
             domain.setRoot(product_id);
        }
        productteamService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "根据产品获取产品团队草稿", tags = {"产品团队" },  notes = "根据产品获取产品团队草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/getdraft")
    public ResponseEntity<PRODUCTTEAMDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, PRODUCTTEAMDTO dto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(dto);
        domain.setRoot(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productteamMapping.toDto(productteamService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除产品团队", tags = {"产品团队" },  notes = "根据产品删除产品团队")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
		return ResponseEntity.status(HttpStatus.OK).body(productteamService.remove(productteam_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取产品团队", tags = {"产品团队" },  notes = "根据产品获取产品团队")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id) {
        PRODUCTTEAM domain = productteamService.get(productteam_id);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立产品团队", tags = {"产品团队" },  notes = "根据产品建立产品团队")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productteams")
    public ResponseEntity<PRODUCTTEAMDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
		productteamService.create(domain);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取指定团队部门", tags = {"产品团队" } ,notes = "根据产品获取指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchspecifyteam")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchPRODUCTTEAMSpecifyTeamByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询指定团队部门", tags = {"产品团队" } ,notes = "根据产品查询指定团队部门")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/searchspecifyteam")
	public ResponseEntity<Page<PRODUCTTEAMDTO>> searchPRODUCTTEAMSpecifyTeamByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchSpecifyTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品团队成员信息", tags = {"产品团队" } ,notes = "根据产品获取产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/fetchproductteaminfo")
	public ResponseEntity<List<PRODUCTTEAMDTO>> fetchPRODUCTTEAMProductTeamInfoByProduct(@PathVariable("product_id") Long product_id,@RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
        List<PRODUCTTEAMDTO> list = productteamMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品团队成员信息", tags = {"产品团队" } ,notes = "根据产品查询产品团队成员信息")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productteams/searchproductteaminfo")
	public ResponseEntity<Page<PRODUCTTEAMDTO>> searchPRODUCTTEAMProductTeamInfoByProduct(@PathVariable("product_id") Long product_id, @RequestBody PRODUCTTEAMSearchContext context) {
        context.setN_root_eq(product_id);
        Page<PRODUCTTEAM> domains = productteamService.searchProductTeamInfo(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(productteamMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新产品团队", tags = {"产品团队" },  notes = "根据产品更新产品团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productteams/{productteam_id}")
    public ResponseEntity<PRODUCTTEAMDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productteam_id") Long productteam_id, @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamMapping.toDomain(productteamdto);
        domain.setRoot(product_id);
        domain.setId(productteam_id);
		productteamService.update(domain);
        PRODUCTTEAMDTO dto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}

